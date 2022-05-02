package com.uoons.users.repository;

import com.uoons.users.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByEmail(String email);

    @Query(value = "SELECT r.role_name, u.*, uu.* FROM role_tbl r INNER JOIN user_tbl_role u ON r.role_id = u.role_id INNER JOIN user_tbl uu ON uu.id=u.user_id WHERE r.role_name ='SELLER' AND uu.email=?1", nativeQuery = true)
    public UserEntity findSellerByEmail(String email);

    @Query(value = "SELECT r.role_name, u.*, uu.*  FROM role_tbl r INNER JOIN user_tbl_role u ON r.role_id = u.role_id INNER JOIN user_tbl uu ON uu.id=u.user_id WHERE r.role_name ='CUSTOMER' AND uu.email=?1", nativeQuery = true)
    public UserEntity findCustomerByEmail(String email);

    @Query(value = "SELECT r.role_name, u.*, uu.*  FROM role_tbl r INNER JOIN user_tbl_role u ON r.role_id = u.role_id INNER JOIN user_tbl uu ON uu.id=u.user_id WHERE r.role_name ='SELLER'",nativeQuery = true )
    public List<UserEntity> findAllSeller();

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_tbl SET is_active = :status WHERE email = :email", nativeQuery = true)
    public void findByUserEmail(@Param("email") String email, @Param("status") Boolean status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_tbl SET is_deleted = :status WHERE email = :email", nativeQuery = true)
    public void deactiveUserByEmail(@Param("email") String email, @Param("status") Boolean status);
}
