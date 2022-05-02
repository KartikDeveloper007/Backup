package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.AddressEntity;
import com.uoons.users.enitity.RoleEntity;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.exception.EmptyInput;
import com.uoons.users.exception.NotFound;
import com.uoons.users.exception.ServiceException;
import com.uoons.users.repository.RoleRepository;
import com.uoons.users.repository.UserRepository;
import com.uoons.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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
            customer.setCreatedBy(customer.getFirstName()+" "+customer.getLastName());
            customer.setCreatedDate(new Date());
            //customer.setIsDeleted(false);
            //customer.setIsActive(false);
            List<AddressEntity> addressEntityList=customer.getAddress();
            for(AddressEntity address:addressEntityList)
            {
                address.setCreatedBy(customer.getFirstName()+" "+customer.getLastName());
                address.setCreatedDate(new Date());
            }
            RoleEntity roleCustomer = roleRepository.findByRoleName("CUSTOMER");
            customer.addRole(roleCustomer);
            return userRepository.save(customer);
        }
    }


    @Override
    public List<UserEntity> getAllUser() {
        try {
            List<UserEntity> userEntities = userRepository.findAll();
            if (userEntities.isEmpty()) {
                throw new ServiceException("604", "List is empty");
            }
            return userEntities;
        } catch (Exception e) {
            throw new ServiceException("605", "Something went wrong while fetching employee" + e.getMessage());
        }
    }

    @Override
    public List<UserEntity> getAllCustomer() {
        try {
            List<UserEntity> allCustomerList = userRepository.findAllCustomer();
            if (allCustomerList.isEmpty()) {
                throw new ServiceException("604", "List is empty");
            }
            return allCustomerList;
        } catch (Exception e) {
            throw new ServiceException("604", "List is empty");
        }
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
        userRepository.findByUserEmail(email, true);
    }

    @Override
    public UserEntity updateCustsomer(UserEntity userEntity, String email) {

        UserEntity updateUser = userRepository.findCustomerByEmail(email);

        if (userEntity.getFirstName().isEmpty() || userEntity.getLastName().isEmpty() || userEntity.getMobileNo().isEmpty() || userEntity.getAddress().isEmpty()) {
            throw new EmptyInput("601", "Some Fields are Empty");


        } else {
            updateUser.setFirstName(userEntity.getFirstName());
            updateUser.setLastName(userEntity.getLastName());
            updateUser.setMobileNo(userEntity.getMobileNo());
            updateUser.setAddress(userEntity.getAddress());
            updateUser.setUpdatedBy(userEntity.getFirstName() + " " + userEntity.getLastName());
            updateUser.setUpdateDate(new Date());
            List<AddressEntity> addressEntityList = updateUser.getAddress();
            for (AddressEntity address : addressEntityList) {
                address.setUpdatedBy(userEntity.getFirstName() + " " + userEntity.getLastName());
                address.setUpdateDate(new Date());
            }
            return userRepository.save(updateUser);
        }
    }


}
