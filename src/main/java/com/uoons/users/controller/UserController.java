package com.uoons.users.controller;

import com.uoons.users.enitity.UserEntity;
import com.uoons.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/savecustomer")
    public UserEntity saveCustomer(@RequestBody UserEntity customer) {
        return userService.saveCustomer(customer);
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String customerDashboard() {
        return "Customer dashboard";
      }

   /* @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    public UserEntity getByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }*/


    @GetMapping("/getAllcustomer")
    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    public List<UserEntity> getALlUser() {
        return userService.getAllUser();
    }


    @PutMapping("/updatecustomer/{emailId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public UserEntity updateCustomer(@PathVariable("emailId") String email, @RequestBody UserEntity userEntity) {
        return userService.updateCustsomer(userEntity, email);
    }
}


