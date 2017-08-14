package com.utsab.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class MerchantDetail implements Serializable {
    private String merchantName;
    private String merchantCode;
    private String userName;
    private String password;
    private List<MerchantAttributeDetail> merchantAttributeDetails;
}
