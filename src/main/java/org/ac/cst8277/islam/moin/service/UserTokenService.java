package org.ac.cst8277.islam.moin.service;

import org.ac.cst8277.islam.moin.entity.UserToken;
import org.ac.cst8277.islam.moin.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Autowired
    UserTokenRepository userTokenRepository;

    public void saveToken(UserToken userToken){
        userTokenRepository.save(userToken);
    }
    public void deleteToken(UserToken userToken){
        userTokenRepository.save(userToken);
    }


    public UserToken findByToken(String token)
    {
        UserToken userToken = userTokenRepository.findByToken(token);
        if(userToken != null){
            return userToken;
        }
        return null;

    }



    public String getTokenById(String uid){
        UserToken userToken = userTokenRepository.getByUserId(uid);
        if(userToken != null){
            return userToken.getToken();
        }
       return null;
    }

}
