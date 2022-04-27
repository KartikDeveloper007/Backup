package com.uoons.users.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address_TBL")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "house_no")
    private String hosueNo;
    @Column(name = "area")
    private String area;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "alternate_mobile")
    private String alternateMobileNo;
    @Column(name = "address_type")
    private String addressType;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "is_primary_address")
    private boolean isPrimaryAddress;


}
