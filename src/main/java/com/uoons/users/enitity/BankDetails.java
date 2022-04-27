package com.uoons.users.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_details_TBL")
public class BankDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_details_id")
    private Long bankDetailsId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "ifsc_code")
    private String ifscCode;
    @Column(name = "bank_holder_name")
    private String bankHolderName;
    @Column(name = "seller_id")
    private String sellerId;

//    @OneToOne
//    private BusinessDetails businessDetails;
//


}
