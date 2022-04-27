package com.uoons.users.controller;


import com.uoons.users.enitity.BankDetails;
import com.uoons.users.enitity.BusinessDetails;
import com.uoons.users.enitity.PickupAddress;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.service.SellerService;
import com.uoons.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/saveseller")
    public UserEntity saveSeller(@RequestBody UserEntity seller) {
        return sellerService.saveSeller(seller);
    }


    @GetMapping("/getseller/{emailid}")
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public UserEntity findSellerByemail(@PathVariable("emailid") String email) {
        return sellerService.getSeller(email);
    }

    @PostMapping("/savebusinessdetails/{emailid}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BusinessDetails saveBusinessDetails(@PathVariable("emailid") String email, @RequestBody BusinessDetails businessDetails) {
        return sellerService.saveBusinessDetails(businessDetails, email);
    }

    @PostMapping("/savebankdetails/{emailid}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BankDetails saveBankDetail(@PathVariable("emailid") String email, @RequestBody BankDetails bankDetails) {
        return sellerService.saveBankDetails(bankDetails, email);
    }

    @PostMapping("/savepickupaddress/{emailid}")
    @PreAuthorize("hasAuthority('SELLER')")
    public PickupAddress pickupAddress(@PathVariable("emailid") String email, @RequestBody PickupAddress pickupAddress) {
        return sellerService.savePickupAddress(pickupAddress, email);
    }

    @PutMapping("/updateseller/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public UserEntity updateSeller(@PathVariable("email") String email, @RequestBody UserEntity user) {
        return sellerService.updateSeller(email, user);
    }

    @GetMapping("/getbusinessdetail/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BusinessDetails getBusinessDetail(@PathVariable("email") String email) {
        return sellerService.getBusinessDetail(email);
    }

    @PutMapping("/updatebusinessdetail/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BusinessDetails updateBusinessdetails(@PathVariable("email") String email, @RequestBody BusinessDetails businessDetails) {
        return sellerService.updateBusinessDetail(email, businessDetails);
    }


    @GetMapping("/getbankdetail/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BankDetails getBankDetail(@PathVariable("email") String email) {
        return sellerService.getBankDetails(email);
    }

    @PutMapping("/updatebankdetail/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public BankDetails updateBankDetail(@PathVariable("email") String email, @RequestBody BankDetails bankDetails) {
        return sellerService.updateBankDetails(email, bankDetails);
    }


    @GetMapping("/getpickupaddress/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public PickupAddress getPickupAddress(@PathVariable("email") String email) {
        return sellerService.getPickupAddress(email);
    }

    @PutMapping("/updatepickupaddress/{email}")
    @PreAuthorize("hasAuthority('SELLER')")
    public PickupAddress updatePickupAddress(@PathVariable("email") String email, @RequestBody PickupAddress pickupAddress) {
        return sellerService.updatePickupAddress(email, pickupAddress);
    }


//    @PostMapping("/businessdetails/{email}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
//    public BusinessDetails saveSellerDetails(@RequestBody BusinessDetailsDto businessDetailsDto, @PathVariable("email") String email) {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        BusinessDetails businessDetails = modelMapper.map(businessDetailsDto, BusinessDetails.class);
//        return sellerService.saveSellerDetails(businessDetails, email);
//    }
//
//
//    @PostMapping("/bankdetails/{email}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
//    public BankDetails saveBankDetails(@RequestBody BankDetailsDto bankDetailsDto, @PathVariable("email") String email) {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        BankDetails bankDetails = modelMapper.map(bankDetailsDto, BankDetails.class);
//        return sellerService.saveBankDetails(bankDetails, email);
//    }
//
//    @GetMapping("/selleremail/{email}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
//    public UserEntity getByEmail(@PathVariable("email") String email) {
//        return sellerService.getByEmail(email);
//    }
//
//    @PostMapping("/pickupaddress/{email}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
//    public List<PickupAddress> pickupAddress(@RequestBody List<PickupAddressDto> pickupAddressDto, @PathVariable("email") String email) {
//
//        List<PickupAddress> paddress = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        for (PickupAddressDto pickupAddress : pickupAddressDto) {
//            PickupAddress pickupAddresslist = modelMapper.map(pickupAddress, PickupAddress.class);
//            paddress.add(pickupAddresslist);
//        }
//        return sellerService.savePickupAddress(paddress, email);
//
//    }
//

}