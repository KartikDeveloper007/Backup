
package com.uoons.users.repository;

import com.uoons.users.enitity.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails, Long> {

    public BusinessDetails findBySellerId(String id);

}

