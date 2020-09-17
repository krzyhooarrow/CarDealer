package spring.service_layer.services.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spring.repository_layer.repositories.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.parser;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource("classpath:application.properties")
public class JwtComponent {

    @Autowired
    private UserRepository userRepository;

    @Value("${JWTTokenKey}")
    private String secretKey;

    @Value("${expirationTime}")
    private Long jwtExpirationInMillis;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",userRepository.findByUsername(userDetails.getUsername()).get().getId());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();

    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public boolean validateToken(String jwt, UserDetails userDetailsServiceProvider) {
        return (extractUsername(jwt).equals(userDetailsServiceProvider.getUsername()) && !isTokenExpired(jwt));
    }

    public boolean isTokenExpired(String jwt) {
        return extractExpirationTime(jwt).before(new Date());
    }

    private Date extractExpirationTime(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimer) {
        return claimer.apply(extractClaims(jwt));
    }

    private Claims extractClaims(String jwt) {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    public Object extractId(String jwt) {  return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().get("id");  }
}
