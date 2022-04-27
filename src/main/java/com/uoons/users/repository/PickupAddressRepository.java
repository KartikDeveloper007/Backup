
package com.uoons.users.repository;

import com.uoons.users.enitity.PickupAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupAddressRepository extends JpaRepository<PickupAddress,Long> {
    public PickupAddress findBySellerId(String id);
}

