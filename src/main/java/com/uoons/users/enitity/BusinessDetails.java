package com.uoons.users.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_details_TBL")
public class BusinessDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_detail_id")
    private Long businessDetailId;
    @Column(name = "gst_number")
    private String gstNumber;
    @Column(name = "pan_number")
    private String panNumber;
    @Column(name = "seller_id")
    private String sellerId;


}
