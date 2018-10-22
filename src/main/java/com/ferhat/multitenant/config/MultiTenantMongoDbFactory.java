package com.ferhat.multitenant.config;

import com.ferhat.multitenant.TenantContext;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.HashMap;
import java.util.Map;

public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

    private static final Map<String, SimpleMongoDbFactory> dbFactories = new HashMap<>();
    private MongoClientURI mongoClientURI;

    public MultiTenantMongoDbFactory(MongoClient mongoClient, String databaseName, String mongoClientUriStr) {
        super(mongoClient, databaseName);
        this.mongoClientURI = new MongoClientURI(mongoClientUriStr);
    }

    private SimpleMongoDbFactory getDbFactoryForTenant(String tenant) {
        SimpleMongoDbFactory dbFactory = dbFactories.get(tenant);

        if (dbFactory == null) {
            dbFactory = new SimpleMongoDbFactory(new MongoClient(mongoClientURI), tenant);
            dbFactories.put(tenant, dbFactory);
        }
        return dbFactory;
    }

    @Override
    public void setWriteConcern(WriteConcern writeConcern) {
        getDbFactoryForTenant(TenantContext.getTenant()).setWriteConcern(writeConcern);
    }

    @Override
    public MongoDatabase getDb() throws DataAccessException {
        return getDbFactoryForTenant(TenantContext.getTenant()).getDb();
    }

    @Override
    public MongoDatabase getDb(String dbName) throws DataAccessException {
        return getDbFactoryForTenant(TenantContext.getTenant()).getDb(dbName);
    }

    @Override
    public void destroy() throws Exception {
        getDbFactoryForTenant(TenantContext.getTenant()).destroy();
        dbFactories.remove(TenantContext.getTenant());
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return getDbFactoryForTenant(TenantContext.getTenant()).getExceptionTranslator();
    }

    @Override
    public DB getLegacyDb() {
        return getDbFactoryForTenant(TenantContext.getTenant()).getLegacyDb();
    }

}
