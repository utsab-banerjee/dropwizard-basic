package com.utsab.DAOs;

import com.utsab.repositories.Merchant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by utsab.banerjee on 12/08/17.
 */
public class MerchantDAO extends AbstractDAO<Merchant> {
    public MerchantDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Merchant findById(Long id) {
        return get(id);
    }

    public Merchant findByMerchantCode(String merchantCode) {
        Criteria criteria = super.currentSession().createCriteria(Merchant.class);
        criteria.add(Restrictions.eq("merchantCode", merchantCode));
        return uniqueResult(criteria);
    }

    public Merchant findByUserName(String userName) {
        Query query = super.currentSession().createQuery("from Merchant where userName = :userName");
        query.setParameter("userName", userName);
        return uniqueResult(query);
    }

    public Long createMerchant(Merchant merchant) {
        return persist(merchant).getId();
    }
}
