//package spring.web_layer.controllers;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import spring.service_layer.dto.OfferPopularityDTO;
//import spring.service_layer.services.OffersPopularityService;
//import spring.web_layer.exceptions.JWTAuthException;
//
//import static org.springframework.http.HttpStatus.OK;
//
//@RestController
//@RequestMapping("/popularity")
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
//public class PopularityController {
//
//    private OffersPopularityService offersPopularityService;
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<String> onlineVerification(@RequestBody OfferPopularityDTO offer) {
//        offersPopularityService.sendMessage(offer.getId());
//        return new ResponseEntity<>("OK", HttpStatus.OK);
//    }
//
//
//}
