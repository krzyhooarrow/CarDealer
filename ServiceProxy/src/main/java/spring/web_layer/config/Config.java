package spring.web_layer.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.service_layer.services.jwt.Encoder;
import spring.service_layer.services.jwt.JwtRequestFilter;
import spring.service_layer.services.jwt.UserDetailsServiceProvider;

import java.util.Arrays;

@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class Config extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceProvider userDetailsService;
    private Encoder BCryptEncoder;
    private JwtRequestFilter filter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable().authorizeRequests()

                .antMatchers(
                        "/auth",
                        "/register",
                        "/subscriptionservice/popularity/**",
                        "/balancer/transactionservice/filters/*",
                        "/balancer/transactionservice/offers/**",
                        "/priceservice/getPrices/*",
                        "/scrapperservice/vin/**",
                        "/scrapperservice/comparator/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors().configurationSource(corsConfigurationSource());

        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(BCryptEncoder);
    }
}
