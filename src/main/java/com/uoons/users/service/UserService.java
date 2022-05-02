package com.uoons.users.service;

import com.uoons.users.enitity.UserEntity;

import java.util.List;

public interface UserService {
    public UserEntity saveCustomer(UserEntity userEntity);

    public List<UserEntity> getAllUser();

    public UserEntity getByEmail(String email);

    public void isActive(String email);

    public UserEntity updateCustsomer(UserEntity userEntity, String email);

    public void isDeleted(String email);
//    public UserEntity  saveCustomerAsSeller(UserEntity customer,Long userId);

}
