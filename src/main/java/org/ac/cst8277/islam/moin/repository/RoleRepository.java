package org.ac.cst8277.islam.moin.repository;

import org.ac.cst8277.islam.moin.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {

    Role findByRole(String role);
}
