package com.uoons.users.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="role_TBL" )
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name")
    private  String roleName;

    public RoleEntity(Long roleId){
        this.roleId=roleId;
    }
    public RoleEntity(String roleName){
        this.roleName=roleName;
    }
}
