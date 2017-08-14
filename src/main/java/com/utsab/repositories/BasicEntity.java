package com.utsab.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
@Data
@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false, updatable = true)
    public Date updateTime;

    public BasicEntity(){
        createTime = new Date();
        updateTime = new Date();
    }
}
