package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.*;
import com.uoons.users.exception.EmptyInput;
import com.uoons.users.exception.ServiceException;
import com.uoons.users.repository.*;
import com.uoons.users.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
        if (seller.getFirstName().isEmpty() || seller.getLastName().isEmpty() ||
                seller.getEmail().isEmpty() || seller.getPassword().isEmpty() || seller.getAddress().isEmpty() || seller.getMobileNo().isEmpty()) {
            throw new EmptyInput("601", "Some Fields Empty");
        } else {
            seller.setUserId(UUID.randomUUID().toString());
            seller.setPassword(passwordEncoder.encode(seller.getPassword()));
            seller.setCreatedBy(seller.getFirstName()+" "+seller.getLastName());
            seller.setCreatedDate(new Date());
            List<AddressEntity> addressEntityList=seller.getAddress();
            for (AddressEntity address:addressEntityList)
            {
                address.setCreatedBy(seller.getFirstName()+" "+seller.getLastName());
                address.setCreatedDate(new Date());
            }
            RoleEntity roleSeller = roleRepository.findByRoleName("SELLER");
            seller.addRole(roleSeller);
            return userRepository.save(seller);

        }
    }

    @Override
    public UserEntity updateSeller(String email, UserEntity userEntity) {
        if (userEntity.getFirstName().isEmpty() || userEntity.getLastName().isEmpty() ||
                userEntity.getEmail().isEmpty() || userEntity.getPassword().isEmpty() || userEntity.getAddress().isEmpty() || userEntity.getMobileNo().isEmpty()) {
            throw new EmptyInput("609", "Some Fields are Empty!! Please Fill the required field");
        } else {
            UserEntity updateSeller = userRepository.findSellerByEmail(email);
            updateSeller.setFirstName(userEntity.getFirstName());
            updateSeller.setLastName(userEntity.getLastName());
            updateSeller.setMobileNo(userEntity.getMobileNo());
            updateSeller.setAddress(userEntity.getAddress());
            updateSeller.setUpdatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
            List<AddressEntity> addressEntityList=updateSeller.getAddress();
            for (AddressEntity address:addressEntityList)
            {
                address.setUpdatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
                address.setUpdateDate(new Date());
            }
            return userRepository.save(updateSeller);
        }
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
        saveDetails.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
        saveDetails.setCreatedDate(new Date());
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
        updateBusiness.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
        updateBusiness.setUpdateDate(new Date());
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
        saveDetails.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
        saveDetails.setCreatedDate(new Date());
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
        updateBankDetail.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
        updateBankDetail.setUpdateDate(new Date());
        return bankDetailsRepository.save(updateBankDetail);
    }

    @Override
    public List<UserEntity> getAllSeller() {
        try {
            List<UserEntity> userEntities = userRepository.findAllSeller();
            if (userEntities.isEmpty()) {
                throw new ServiceException("604", "List is empty");
            }
            return userEntities;
        } catch (Exception e) {
            throw new ServiceException("605", "Something went wrong while fetching employee" + e.getMessage());
        }
    }


//    @Override
//    public List<UserEntity> getAllUser() {
//        try {
//            List<UserEntity> userEntities = userRepository.findAll();
//            if (userEntities.isEmpty()) {
//                throw new ServiceException("604", "List is empty");
//            }
//            return userEntities;
//        } catch (Exception e) {
//            throw new ServiceException("605", "Something went wrong while fetching employee" + e.getMessage());
//        }
//    }
//







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
        saveAddress.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
        saveAddress.setCreatedDate(new Date());
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
        updatePickupAddress.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
        updatePickupAddress.setUpdateDate(new Date());
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
