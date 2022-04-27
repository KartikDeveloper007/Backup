package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.RoleEntity;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.repository.RoleRepository;
import com.uoons.users.repository.UserRepository;
import com.uoons.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    List<RoleEntity> roles = new ArrayList<>();


    @Override
    public UserEntity saveCustomer(UserEntity customer) {
        customer.setUserId(UUID.randomUUID().toString());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        RoleEntity roleCustomer = roleRepository.findByRoleName("CUSTOMER");
        customer.addRole(roleCustomer);
        return userRepository.save(customer);
    }


    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void isActive(String email) {
        UserEntity updateIsActive = userRepository.findByEmail(email);

        userRepository.save(updateIsActive);
    }

    @Override
    public UserEntity updateCustsomer(UserEntity userEntity, String email) {

        UserEntity updateUser =userRepository.findCustomerByEmail(email);


            updateUser.setFirstName(userEntity.getFirstName());
            updateUser.setLastName(userEntity.getLastName());
            updateUser.setMobileNo(userEntity.getMobileNo());
            updateUser.setAddress(userEntity.getAddress());
            return userRepository.save(updateUser);
    }

   /* @Override
    public UserEntity saveCustomerAsSeller(UserEntity customer,Long userId) {
        UserEntity addAsSeller = userRepository.findById(userId).get();


        return null;
    }*/

}
