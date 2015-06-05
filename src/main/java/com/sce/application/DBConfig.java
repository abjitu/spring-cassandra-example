package com.sce.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.sce.dao.UserAccessor;

@Configuration
public class DBConfig {

    private static Logger logger = LoggerFactory.getLogger(DBConfig.class);

    private @Value("${db.host}") String host;

    private @Value("${db.keyspace}") String keyspace;

    public PoolingOptions poolingOptions() {
        PoolingOptions poolingOptions = new PoolingOptions();
        return poolingOptions;
    }

    public Cluster cluster() {
        Cluster cluster = Cluster.builder().addContactPoint(host).withPoolingOptions(poolingOptions()).build();
        Metadata metadata = cluster.getMetadata();
        logger.info("Connected to cluster: {}", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            logger.info("Datatacenter: {}; Host: {}; Rack: {}\n", host.getDatacenter(), host.getAddress(),
                    host.getRack());
        }
        return cluster;
    }

    @Bean
    public Session session() {
        Session session = cluster().connect(keyspace);
        dbSetup(session);
        return session;
    }

    @Bean
    public MappingManager getManager() {
        return new MappingManager(session());
    }

    @Bean
    public UserAccessor userAccessor() {
        return getManager().createAccessor(UserAccessor.class);
    }

    public void dbSetup(Session session) {
        logger.debug("Initializing schema");
        createUserTable(session);
        logger.debug("Exiting prepopulate");
    }

    private void createUserTable(Session session) {
        session.execute("CREATE TABLE IF NOT EXISTS user (email text PRIMARY KEY, first_name text, last_name text ) ;");

    }

}