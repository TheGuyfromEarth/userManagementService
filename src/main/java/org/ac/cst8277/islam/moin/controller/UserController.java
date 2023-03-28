package org.ac.cst8277.islam.moin.controller;

import org.ac.cst8277.islam.moin.dto.UserDTO;
import org.ac.cst8277.islam.moin.entity.AuthRequest;
import org.ac.cst8277.islam.moin.entity.Role;
import org.ac.cst8277.islam.moin.entity.User;
import org.ac.cst8277.islam.moin.entity.UserToken;
import org.ac.cst8277.islam.moin.service.UserService;
import org.ac.cst8277.islam.moin.service.UserTokenService;
import org.ac.cst8277.islam.moin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserTokenService userTokenService;

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException("Invalid Username/Password");
        }
        User user = userService.getByUserName(authRequest.getUsername());
        Util.userLogMap.put(user.getUserId(), Boolean.TRUE);
        String token = Util.generateTokenUUID();
        UserToken userToken = new UserToken(user.getUserId(), token);
        if (userTokenService.getTokenById(user.getUserId()) == null) {
            userTokenService.saveToken(userToken);
        }
        return token;
    }



    // get list of users
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // get user by id
    @GetMapping("/id")
    public User getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }

    // delete user
    @DeleteMapping
    public Boolean deleteUser(@RequestParam String userId) {
        return userService.deleteUser(userId);
    }

    // create users
    @PostMapping
    public void createUsers(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @GetMapping("/get-token")
    public String getUserToken(@RequestParam String userId) {
        return userTokenService.getTokenById(userId);
    }

    @GetMapping("/check-login")
    public Boolean checkUserLogin(@RequestParam String userId) {
        return Util.userLogMap.get(userId);
    }
}
