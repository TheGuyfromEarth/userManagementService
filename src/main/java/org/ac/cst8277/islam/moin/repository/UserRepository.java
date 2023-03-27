package org.ac.cst8277.islam.moin.repository;

import org.ac.cst8277.islam.moin.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);

}
