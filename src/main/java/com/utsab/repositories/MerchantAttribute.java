package com.utsab.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "merchantAttribute", uniqueConstraints = @UniqueConstraint(columnNames = {"merchantId", "AttributeKey", "isActive"}))
public class MerchantAttribute extends BasicEntity {
//    NOT MANDATORY, WHEN NAMES ARE SAME
//    @Column(name = "mobileNumber")
    private Long merchantId;
    private String AttributeKey;
    private String attributeValue;
    private Boolean isActive;
}
