package org.ac.cst8277.islam.moin.service;

import org.ac.cst8277.islam.moin.entity.Role;
import org.ac.cst8277.islam.moin.entity.User;
import org.ac.cst8277.islam.moin.entity.UserToken;
import org.ac.cst8277.islam.moin.repository.RoleRepository;
import org.ac.cst8277.islam.moin.repository.UserRepository;
import org.ac.cst8277.islam.moin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role findByRole(String role){
        return roleRepository.findByRole(role);
    }
   }
