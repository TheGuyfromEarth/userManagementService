package org.ac.cst8277.islam.moin.service;

import org.ac.cst8277.islam.moin.dto.UserDTO;
import org.ac.cst8277.islam.moin.entity.Role;
import org.ac.cst8277.islam.moin.entity.User;
import org.ac.cst8277.islam.moin.entity.UserToken;
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
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    public boolean deleteUser(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId));
        if(mongoTemplate.remove(query, User.class).getDeletedCount() > 0 && mongoTemplate.remove(query, UserToken.class).getDeletedCount()>0) {
            Util.userLogMap.remove(userId);
            return true;
        }
        return false;
    }

    public void createUser(UserDTO userDTO) {
        User user = new User() ;
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        List<Role> roleList = new ArrayList<>();

        if(userDTO.getRoles().contains("ADMIN"))
            roleList.add(roleService.findByRole("ADMIN"));

        if(userDTO.getRoles().contains("PRODUCER"))
            roleList.add(roleService.findByRole("PRODUCER"));

        if(userDTO.getRoles().contains("SUBSCRIBER"))
            roleList.add(roleService.findByRole("SUBSCRIBER"));

        user.setRoles(roleList);

        Util.userLogMap.put(user.getUserId(),Boolean.FALSE);
        userRepository.save(user);
    }

    public User getByUserName(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
