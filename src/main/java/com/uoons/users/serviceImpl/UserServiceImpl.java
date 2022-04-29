package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.RoleEntity;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.exception.EmptyInput;
import com.uoons.users.exception.NotFound;
import com.uoons.users.repository.RoleRepository;
import com.uoons.users.repository.UserRepository;
import com.uoons.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        if (customer.getFirstName().isEmpty() || customer.getLastName().isEmpty() ||
                customer.getEmail().isEmpty() || customer.getPassword().isEmpty() || customer.getAddress().isEmpty()) {
            throw new EmptyInput("601", "Some Fields Empty");
        } else {
            customer.setUserId(UUID.randomUUID().toString());
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            RoleEntity roleCustomer = roleRepository.findByRoleName("CUSTOMER");
            customer.addRole(roleCustomer);
            return userRepository.save(customer);
        }
    }


    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user.getEmail().contains(email)) {
            return user;
        } else {
            throw new NotFound("608", "email does not exist");
        }

    }


    @Override
    public void isActive(String email) {
        UserEntity updateIsActive = userRepository.findByEmail(email);

        userRepository.save(updateIsActive);
    }

    @Override
    public UserEntity updateCustsomer(UserEntity userEntity, String email) {

        UserEntity updateUser = userRepository.findCustomerByEmail(email);

        if (userEntity.getFirstName().isEmpty() ||
                userEntity.getLastName().isEmpty() || userEntity.getMobileNo().isEmpty() || userEntity.getAddress().isEmpty()) {
            throw new EmptyInput("601", "Some Fields are Empty");


        } else {
            updateUser.setFirstName(userEntity.getFirstName());
            updateUser.setLastName(userEntity.getLastName());
            updateUser.setMobileNo(userEntity.getMobileNo());
            updateUser.setAddress(userEntity.getAddress());
            return userRepository.save(updateUser);
        }
    }


}
