package com.uoons.users.controller;

import com.uoons.users.enitity.UserEntity;
import com.uoons.users.service.UserService;
import com.uoons.users.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/savecustomer")
    public ResponseEntity<?> saveCustomer(@RequestBody UserEntity customer) {
        UserEntity saveCustomer = userService.saveCustomer(customer);
        return new ResponseEntity<UserEntity>(saveCustomer, HttpStatus.CREATED);

    }


    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String customerDashboard() {
        return "Customer dashboard";
    }


    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        UserEntity findCustomerByEmail = userServiceImpl.getByEmail(email);
        return new ResponseEntity<UserEntity>(findCustomerByEmail, HttpStatus.OK);
    }

    @GetMapping("/getalluser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserEntity>> getALlUser() {
        List<UserEntity> findAllCustomer = userServiceImpl.getAllUser();
        return new ResponseEntity<List<UserEntity>>(findAllCustomer, HttpStatus.OK);
    }

//    @GetMapping("/getallcustomer")
//    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
//    public ResponseEntity<List<UserEntity>> getALlUser() {
//        List<UserEntity> findAllCustomer = userServiceImpl.getAllUser();
//        return new ResponseEntity<List<UserEntity>>(findAllCustomer, HttpStatus.OK);
//    }




    @PutMapping("/updatecustomer/{emailId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public UserEntity updateCustomer(@PathVariable("emailId") String email, @RequestBody UserEntity userEntity) {
        return userService.updateCustsomer(userEntity, email);
    }

    @PutMapping("/deactive/{emailid}")
    @PreAuthorize("hasAuthority('SELLER')")

    public void deactiveUser(@PathVariable("emailid") String email)
    {
        userService.isDeleted(email);
    }

}


