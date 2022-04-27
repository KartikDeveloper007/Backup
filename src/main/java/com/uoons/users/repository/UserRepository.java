package com.uoons.users.repository;

import com.uoons.users.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);

    @Query(value = "SELECT r.role_name, u.*, uu.* FROM role_tbl r INNER JOIN user_tbl_role u ON r.role_id = u.role_id INNER JOIN user_tbl uu ON uu.id=u.user_id WHERE r.role_name ='SELLER' AND uu.email=?1", nativeQuery = true)
    public UserEntity findSellerByEmail(String email);

    @Query(value = "SELECT r.role_name, u.*, uu.*  FROM role_tbl r INNER JOIN user_tbl_role u ON r.role_id = u.role_id INNER JOIN user_tbl uu ON uu.id=u.user_id WHERE r.role_name ='CUSTOMER' AND uu.email=?1", nativeQuery = true)
    public UserEntity findCustomerByEmail(String email);
}
