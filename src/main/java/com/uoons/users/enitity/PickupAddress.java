package com.uoons.users.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pickup_address_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickupAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long pickupaddressId;
    @Column(name = "address_line")
    private String addressLine;
    @Column(name = "area")
    private String area;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "alternate_mobile")
    private String alternateMobileNo;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "seller_id")
    private String sellerId;


}
