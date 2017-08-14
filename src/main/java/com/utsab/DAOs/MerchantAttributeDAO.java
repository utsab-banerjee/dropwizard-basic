package com.utsab.DAOs;

import com.utsab.repositories.Merchant;
import com.utsab.repositories.MerchantAttribute;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
public class MerchantAttributeDAO extends AbstractDAO<MerchantAttribute> {
    public MerchantAttributeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long createMerchant(MerchantAttribute merchantAttribute) {
        return persist(merchantAttribute).getId();
    }
}
