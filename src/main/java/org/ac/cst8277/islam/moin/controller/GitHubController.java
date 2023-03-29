package org.ac.cst8277.islam.moin.controller;

import org.ac.cst8277.islam.moin.entity.GitHubAccessToken;
import org.ac.cst8277.islam.moin.entity.UserToken;
import org.ac.cst8277.islam.moin.repository.GithubAccessTokenRepository;
import org.ac.cst8277.islam.moin.service.UserTokenService;
import org.ac.cst8277.islam.moin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class GitHubController {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
  @Autowired
    private UserTokenService userTokenService;

    @GetMapping
    public String handleGithubOAuth2Callback(Principal principal) {
        Util.userLogMap.put(principal.getName(), Boolean.TRUE);
        String token = Util.generateTokenUUID();
        UserToken userToken =new UserToken(principal.getName(), token,LocalDateTime.now());
        if (userTokenService.getTokenById(principal.getName()) == null) {
            userTokenService.saveToken(userToken);
            return "New session started and token generated";
        }
        return "Previous session already running,please use that token !!";
    }
}