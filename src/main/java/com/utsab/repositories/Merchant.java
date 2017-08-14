package com.utsab.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant", uniqueConstraints = {@UniqueConstraint(columnNames = {"merchantName", "merchantCode"}), @UniqueConstraint(columnNames = "userName")})
public class Merchant extends BasicEntity {

    @Column(name = "merchantName")
    private String merchantName;

    @Column(name = "merchantCode")
    private String merchantCode;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "merchantId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<MerchantAttribute> merchantAttributes;
}
