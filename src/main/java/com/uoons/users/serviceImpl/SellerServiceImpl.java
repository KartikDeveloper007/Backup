package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.*;
import com.uoons.users.repository.*;
import com.uoons.users.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private PickupAddressRepository pickupAddressRepository;

    @Override
    public UserEntity saveSeller(UserEntity seller) {
        seller.setUserId(UUID.randomUUID().toString());
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        RoleEntity roleSeller = roleRepository.findByRoleName("SELLER");
        seller.addRole(roleSeller);
        return userRepository.save(seller);
    }

    @Override
    public UserEntity updateSeller(String email, UserEntity userEntity) {
        UserEntity updateSeller = userRepository.findSellerByEmail(email);
        updateSeller.setFirstName(userEntity.getFirstName());
        updateSeller.setLastName(userEntity.getLastName());
        updateSeller.setMobileNo(userEntity.getMobileNo());
        updateSeller.setAddress(userEntity.getAddress());
        return userRepository.save(updateSeller);
    }


    @Override
    public UserEntity getSeller(String email) {
        return userRepository.findSellerByEmail(email);
    }

    @Override
    public BusinessDetails saveBusinessDetails(BusinessDetails businessDetails, String email) {
        UserEntity userEntity = userRepository.findSellerByEmail(email);
        BusinessDetails saveDetails = new BusinessDetails();
        saveDetails.setSellerId(userEntity.getUserId());
        saveDetails.setGstNumber(businessDetails.getGstNumber());
        saveDetails.setPanNumber(businessDetails.getPanNumber());
        return businessDetailsRepository.save(saveDetails);
    }

    @Override
    public BusinessDetails getBusinessDetail(String email) {
        UserEntity user = getSeller(email);
        BusinessDetails businessDetails = businessDetailsRepository.findBySellerId(user.getUserId());
        return businessDetails;
    }

    @Override
    public BusinessDetails updateBusinessDetail(String email, BusinessDetails businessDetails) {
        UserEntity user = userRepository.findSellerByEmail(email);
        BusinessDetails updateBusiness = businessDetailsRepository.findBySellerId(user.getUserId());
        updateBusiness.setPanNumber(businessDetails.getPanNumber());
        updateBusiness.setGstNumber(businessDetails.getGstNumber());
        return businessDetailsRepository.save(updateBusiness);
    }


    @Override
    public BankDetails saveBankDetails(BankDetails bankDetails, String email) {
        UserEntity userEntity = userRepository.findSellerByEmail(email);
        BankDetails saveDetails = new BankDetails();
        saveDetails.setSellerId(userEntity.getUserId());
        saveDetails.setBankHolderName(bankDetails.getBankHolderName());
        saveDetails.setBankName(bankDetails.getBankName());
        saveDetails.setAccountNumber(bankDetails.getAccountNumber());
        saveDetails.setIfscCode(bankDetails.getIfscCode());
        return bankDetailsRepository.save(saveDetails);
    }


    @Override
    public BankDetails getBankDetails(String email) {
        UserEntity user = getSeller(email);
        BankDetails bankDetails = bankDetailsRepository.findBySellerId(user.getUserId());
        return bankDetails;
    }

    @Override
    public BankDetails updateBankDetails(String email, BankDetails bankDetails) {
        UserEntity user = userRepository.findSellerByEmail(email);
        BankDetails updateBankDetail = bankDetailsRepository.findBySellerId(user.getUserId());
        updateBankDetail.setBankName(bankDetails.getBankName());
        updateBankDetail.setBankHolderName(bankDetails.getBankHolderName());
        updateBankDetail.setAccountNumber(bankDetails.getAccountNumber());
        updateBankDetail.setIfscCode(bankDetails.getIfscCode());
        return bankDetailsRepository.save(updateBankDetail);
    }


    @Override
    public PickupAddress savePickupAddress(PickupAddress pickupAddress, String email) {
        UserEntity userEntity = userRepository.findSellerByEmail(email);
        PickupAddress saveAddress = new PickupAddress();
        saveAddress.setSellerId(userEntity.getUserId());
        saveAddress.setAddressLine(pickupAddress.getAddressLine());
        saveAddress.setLandmark(pickupAddress.getLandmark());
        saveAddress.setState(pickupAddress.getState());
        saveAddress.setArea(pickupAddress.getArea());
        saveAddress.setPincode(pickupAddress.getPincode());
        saveAddress.setCity(pickupAddress.getCity());
        saveAddress.setAlternateMobileNo(pickupAddress.getAlternateMobileNo());
        return pickupAddressRepository.save(saveAddress);
    }

    @Override
    public PickupAddress getPickupAddress(String email) {
        UserEntity user = getSeller(email);
        PickupAddress pickupAddress = pickupAddressRepository.findBySellerId(user.getUserId());
        return pickupAddress;
    }

    @Override
    public PickupAddress updatePickupAddress(String email, PickupAddress pickupAddress) {
        UserEntity user = userRepository.findSellerByEmail(email);
        PickupAddress updatePickupAddress = pickupAddressRepository.findBySellerId(user.getUserId());
        updatePickupAddress.setAddressLine(pickupAddress.getAddressLine());
        updatePickupAddress.setLandmark(pickupAddress.getLandmark());
        updatePickupAddress.setArea(pickupAddress.getArea());

        updatePickupAddress.setCity(pickupAddress.getCity());
        updatePickupAddress.setState(pickupAddress.getState());
        updatePickupAddress.setPincode(pickupAddress.getPincode());
        updatePickupAddress.setAlternateMobileNo(pickupAddress.getAlternateMobileNo());
        return pickupAddressRepository.save(updatePickupAddress);
    }








  /*  public BusinessDetails saveSellerDetails(BusinessDetails businessDetails, String email) {

        UserEntity user = getByEmail(email);
        BusinessDetails updateDetails = new BusinessDetails();
        updateDetails.setSellerId(user.getUserId());
        updateDetails.setGstNumber(businessDetails.getGstNumber());
        updateDetails.setPanNumber(businessDetails.getPanNumber());
        return businessDetailsRepository.save(updateDetails);
    }*/


/*
    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;
    @Autowired
    private PickupAddressRepository pickupAddressRepository;

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public BusinessDetails saveSellerDetails(BusinessDetails businessDetails, String email) {

        UserEntity user = getByEmail(email);
        BusinessDetails updateDetails = new BusinessDetails();
        updateDetails.setSellerId(user.getUserId());
        updateDetails.setGstNumber(businessDetails.getGstNumber());
        updateDetails.setPanNumber(businessDetails.getPanNumber());
        return businessDetailsRepository.save(updateDetails);
    }

    @Override
    public BankDetails saveBankDetails(BankDetails bankDetails, String email) {

        UserEntity user = getByEmail(email);
        BankDetails updateBankDetails = new BankDetails();
        updateBankDetails.setSellerId(user.getUserId());
        updateBankDetails.setBankName(bankDetails.getBankName());
        updateBankDetails.setAccountNumber(bankDetails.getAccountNumber());
        updateBankDetails.setIfscCode(bankDetails.getIfscCode());
        updateBankDetails.setBankHolderName(bankDetails.getBankHolderName());

        return bankDetailsRepository.save(updateBankDetails);

    }

    @Override
    public List<PickupAddress> savePickupAddress(List<PickupAddress> pickupAddressList, String email) {
        UserEntity user = getByEmail(email);
        List<PickupAddress> updatePickupAddressList = new ArrayList<>();
        for (PickupAddress pickupAddress : pickupAddressList) {
            PickupAddress updatePickupAddress = new PickupAddress();
            updatePickupAddress.setSellerId(user.getUserId());
            updatePickupAddress.setArea(pickupAddress.getArea());
            updatePickupAddress.setState(pickupAddress.getState());
            updatePickupAddress.setCity(pickupAddress.getCity());
            updatePickupAddress.setLandmark(pickupAddress.getLandmark());
            updatePickupAddress.setPincode(pickupAddress.getPincode());
            updatePickupAddress.setAddressLine(pickupAddress.getAddressLine());
            updatePickupAddress.setAlternateMobileNo(pickupAddress.getAlternateMobileNo());
            updatePickupAddressList.add(updatePickupAddress);


        }
        return pickupAddressRepository.saveAll(updatePickupAddressList);

    }*/
}
