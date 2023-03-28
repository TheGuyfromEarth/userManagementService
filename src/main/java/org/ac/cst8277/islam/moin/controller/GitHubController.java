package org.ac.cst8277.islam.moin.controller;

import org.ac.cst8277.islam.moin.entity.GitHubAccessToken;
import org.ac.cst8277.islam.moin.repository.GithubAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


@RestController
@RequestMapping("/login1")
public class GitHubController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private GithubAccessTokenRepository accessTokenRepository;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping
    public String handleGithubOAuth2Callback(@RequestParam("code") String code) {


        ClientRegistration registration = clientRegistrationRepository.findByRegistrationId("github");
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(registration.getRegistrationId(), code);
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        // Store the access token in the database
        GitHubAccessToken token = new GitHubAccessToken(accessToken);
        accessTokenRepository.save(token);

       // // Store the access token in the database
      //  GithubAccessToken token = new GithubAccessToken(accessToken);
      //  accessTokenRepository.save(token);
        System.out.println(accessToken);
        return "Hello";
    }





}
