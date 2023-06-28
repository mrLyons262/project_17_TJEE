package com.project.model;


import com.project.model.entity.HashData;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HashDataRepository {

    @Inject
    private DatastoreBean datastoreBean;

    public List<HashData> getHashData() {
        return new ArrayList<>(datastoreBean.getDatastore().createQuery(HashData.class)
                .find()
                .toList());
    }

    public void createHashData(String hashValue) {
        HashData hashData = new HashData(hashValue);
        datastoreBean.getDatastore().save(hashData);
    }

    public void deleteHashData(String hashValue) {
        HashData hashDataToDelete = datastoreBean.getDatastore().createQuery(HashData.class)
                .find()
                .toList().stream()
                .filter(hashData -> hashData.getHashValue().equals(hashValue))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));

        datastoreBean.getDatastore().delete(hashDataToDelete);
    }
}
