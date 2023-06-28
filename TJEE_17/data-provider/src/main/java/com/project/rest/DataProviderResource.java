package com.project.rest;


import com.project.model.dto.HashDataDTO;
import com.project.service.DataProviderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hash")
public class DataProviderResource {

    @Inject
    private DataProviderService dataProviderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HashDataDTO> getHashData() {
        return dataProviderService.getHashData();
    }

    @POST
    public void createHashData(@QueryParam("value") String hashValue) {
        dataProviderService.createHashData(hashValue);
    }

    @DELETE
    public void deleteHashData(@QueryParam("value") String hashValue) {
        dataProviderService.deleteHashData(hashValue);
    }
}
