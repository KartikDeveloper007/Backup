package com.uoons.users.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@Data
@MappedSuperclass
public abstract class BaseEntity {
    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date updateDate;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;
    @JsonIgnore
    private boolean isDeleted;

    @JsonIgnore
    @Column(name = "is_active")
    @ColumnDefault("false")
    private boolean isActive;


}
