
package com.uoons.users.repository;

import com.uoons.users.enitity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {
    public BankDetails findBySellerId(String id);
}

