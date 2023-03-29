package org.ac.cst8277.islam.moin.repository;

import org.ac.cst8277.islam.moin.entity.UserToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTokenRepository extends MongoRepository<UserToken,String> {

    UserToken getByUserId(String userId);

    UserToken findByToken(String token);

}
