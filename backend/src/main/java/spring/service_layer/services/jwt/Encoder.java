package spring.service_layer.services.jwt;


import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Encoder implements PasswordEncoder {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence charSequence) {  return encoder.encode(charSequence);  }

    @Override
    public boolean matches(CharSequence charSequence, String s) {  return encoder.matches(charSequence,s);  }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {  return encoder.upgradeEncoding(encodedPassword);  }

}
