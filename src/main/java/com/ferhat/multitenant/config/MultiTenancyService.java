package com.ferhat.multitenant.config;

import com.ferhat.multitenant.TenantContext;
import com.ferhat.multitenant.exceptions.ExceptionMessage;
import com.ferhat.multitenant.exceptions.InvalidParametersException;
import com.ferhat.multitenant.repository.DatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiTenancyService {

    private DatabaseRepository databaseRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiTenancyService.class);

    @Autowired
    public MultiTenancyService(DatabaseRepository databaseRepository) {
        TenantContext.setDefaultTenant();
        LOGGER.info("TENANT SET TO: default");
        this.databaseRepository = databaseRepository;
    }

    public void setTenant(String databaseName) {
            TenantContext.setDefaultTenant();
            if (databaseRepository.findFirstByDatabaseNameEqualsIgnoreCaseAndIsActive(databaseName, true) == null) {
                throw new InvalidParametersException(ExceptionMessage.DATABASE_NOT_FOUND);
            }

            TenantContext.setTenant(databaseName);
            LOGGER.info("TENANT SET TO: " + databaseName);
    }

}
