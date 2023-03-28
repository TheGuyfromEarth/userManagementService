package org.ac.cst8277.islam.moin.config;

import org.ac.cst8277.islam.moin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        http.authorizeRequests()
       //         .requestMatchers("users/login","/users","/users/home","/login").permitAll()
                .requestMatchers("/users/login","/users","/users/id/**","/users/get-token","/users/check-login","/roles","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.gitHubClientRegistration());
    }
    private ClientRegistration gitHubClientRegistration() {
        return ClientRegistration.withRegistrationId("github")
                .clientId("0424359bf44863872a46")
                .clientSecret("821ef87b1bb4236b79f72cf5436f18d55c08d661")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientId("0424359bf44863872a46")
                .scope("read","write","trust")
                .redirectUri("http://localhost:9000/login1")
                .authorizationUri("https://github.com/login")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                // .userNameAttributeName(IdTokenClaimNames.SUB)
                //.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("GitHub")
                .build();
    }





//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.securityMatcher("/**")
//                .csrf().disable()
//                .authorizeHttpRequests((requests) -> {
//                    try {
//                        requests.requestMatchers("/users/login","/users","/users/id/**","/users/get-token","/users/check-login","/roles")
//                                .permitAll().and().authorizeHttpRequests()
//                                .anyRequest().authenticated()
//                                .and().exceptionHandling()
//                                .and()
//                                .sessionManagement()
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                                .and().httpBasic();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//        return http.build();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userService);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }



    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
