package spring.service_layer.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@PropertySource("classpath:amazon.properties")
public class ImagesService {

    @Value("${endpointUrl}")
    private String endpointUrl;
    @Value("${bucketName}")
    private String bucketName;
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;

    private AmazonS3 s3client;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    public String getFileUrl(MultipartFile multipartFile){
        return endpointUrl + "/" + bucketName + "/" + new Date().getTime() + "-" + multipartFile
                .getOriginalFilename()
                .replace(" ", "_");
    }

    public String uploadFile(MultipartFile multipartFile, String fileUrl) {
        try {
            File file = convertMultiPartToFile(multipartFile);
            s3client.putObject(new PutObjectRequest(bucketName, fileUrl, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileUrl;
    }

    public boolean deleteFileFromS3Bucket(String fileUrl) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileUrl.substring(fileUrl.lastIndexOf("/") + 1)));
        return true;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
