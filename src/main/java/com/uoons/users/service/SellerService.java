package com.uoons.users.service;

import com.uoons.users.enitity.BankDetails;
import com.uoons.users.enitity.BusinessDetails;
import com.uoons.users.enitity.PickupAddress;
import com.uoons.users.enitity.UserEntity;

public interface SellerService {

    public UserEntity saveSeller(UserEntity userEntity);
    public UserEntity getSeller(String email);
    public UserEntity updateSeller(String email, UserEntity userEntity);




    public BusinessDetails saveBusinessDetails(BusinessDetails businessDetails, String email);
    public BusinessDetails getBusinessDetail(String email);
    public BusinessDetails updateBusinessDetail(String email, BusinessDetails businessDetails);

    public BankDetails saveBankDetails(BankDetails bankDetails, String email);
    public BankDetails getBankDetails(String email);
    public BankDetails updateBankDetails(String email, BankDetails bankDetails);

    public PickupAddress savePickupAddress(PickupAddress pickupAddress, String email);
    public PickupAddress getPickupAddress(String email);
    public PickupAddress updatePickupAddress(String email, PickupAddress pickupAddress);


}
