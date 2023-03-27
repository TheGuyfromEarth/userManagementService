package org.ac.cst8277.islam.moin.controller;

import org.ac.cst8277.islam.moin.entity.Role;
import org.ac.cst8277.islam.moin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    // get list of roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }
}
