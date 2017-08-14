package com.utsab;

import com.google.common.collect.ImmutableList;
import com.utsab.DAOs.MerchantDAO;
import com.utsab.configurations.DropwizardBasicConfiguration;
import com.utsab.repositories.Merchant;
import com.utsab.repositories.MerchantAttribute;
import com.utsab.resources.BasicController;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by utsab.banerjee on 11/08/17.
 */
public class DropwizardBasic extends Application<DropwizardBasicConfiguration> {

    static ImmutableList<Class<?>> entities;

    static {
        entities = ImmutableList.<Class<?>>builder()
                .add(Merchant.class)
                .add(MerchantAttribute.class)
                .build();
    }

    private final HibernateBundle<DropwizardBasicConfiguration> hibernateApplicationDatabaseBundle = new HibernateBundle<DropwizardBasicConfiguration>(entities, new SessionFactoryFactory()) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropwizardBasicConfiguration configuration) {
            return configuration.getApplicationDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<DropwizardBasicConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateApplicationDatabaseBundle);
    }

    @Override
    public void run(DropwizardBasicConfiguration dropwizardBasicConfiguration, Environment environment) throws Exception {
        final MerchantDAO merchantDAO = new MerchantDAO(hibernateApplicationDatabaseBundle.getSessionFactory());
        environment.jersey().register(new BasicController(merchantDAO));
    }
}
