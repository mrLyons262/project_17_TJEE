package com.project.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.project.model.entity.HashData;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class DatastoreBean {

    private final static String DATABASE = "studenci_2020";
    private Datastore datastore;

    private final Morphia morphia = new Morphia();

    @PostConstruct
    public void initialize() {
        // Get the database connection string
        String uri = "host.docker.internal";
        int port = 8000;
        // Define a datastore that will connect to the database
        datastore = morphia.createDatastore(new MongoClient(uri, port), DATABASE);
        datastore.delete(datastore.find(HashData.class));

        // Configure the data store
        morphia.mapPackage("com.project.model.entity");
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
