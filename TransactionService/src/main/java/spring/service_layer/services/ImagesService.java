package spring.service_layer.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import spring.repository_layer.models.Offer;
import spring.web_layer.config.Constants;
import spring.web_layer.exceptions.OffersNotFoundException;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImagesService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final RepositoryService repositoryService;
    private final AmazonS3 s3client;

    private final String endpointUrl = Constants.AMAZON_ENDPOINT_URL;
    private final String bucketName = Constants.AMAZON_BUCKET_NAME;
    private final String accessKey = Constants.AMAZON_ID;
    private final String secretKey = Constants.AMAZON_KEY;
    private final String directory = Constants.AMAZON_DIR_NAME;

    public ImagesService(@Autowired RepositoryService repositoryService, @Autowired AmazonS3 s3client) {
        this.repositoryService = repositoryService;
        this.s3client = s3client;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    }

    private String getFileUrl(MultipartFile multipartFile) {
        return directory + '/' + new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
    }

    private String getFullPathOfUrl(String url) {
        return endpointUrl.replace("//", "//" + bucketName + '.') + "/" + url;
    }

    private boolean uploadFile(MultipartFile multipartFile, String fileUrl) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        s3client.putObject(new PutObjectRequest(bucketName, fileUrl, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        file.delete();
        return true;
    }

    private boolean deleteFileFromS3Bucket(String fileUrl) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, directory + "/" + fileUrl.substring(fileUrl.lastIndexOf("/") + 1)));
        return true;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public boolean uploadImage(Long offerId, MultipartFile image, Long userId) {
        try {
            Offer offer = repositoryService.offerRepository.
                    findByUserIdAndOfferId(userId, offerId)
                    .orElseThrow(OffersNotFoundException::new);

            String url = getFileUrl(image);

            List<String> urls = offer.getImage();

            if (uploadFile(image, url)) urls = urls == null ? new LinkedList<>() : offer.getImage();

            if (urls.size() <= Constants.MAX_IMAGES_PER_OFFER) {

                urls.add(getFullPathOfUrl(url));

                repositoryService.offerRepository.save(offer);
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Offer removeImages(Offer offer) {
        offer.getImage().forEach(this::deleteFileFromS3Bucket);
        return offer;
    }
}