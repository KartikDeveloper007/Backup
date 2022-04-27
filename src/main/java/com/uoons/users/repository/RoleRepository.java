package com.uoons.users.repository;

import com.uoons.users.enitity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

   // @Query("SELECT r FROM RoleEntity r WHERE r.roleName = ?1")
    public RoleEntity findByRoleName(String name);
}
