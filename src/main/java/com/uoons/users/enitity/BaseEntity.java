package com.uoons.users.enitity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;


@Data
@MappedSuperclass
public abstract class BaseEntity {
    private Date createdDate;
    private Date updateDate;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;
    private boolean isDeleted;



}
