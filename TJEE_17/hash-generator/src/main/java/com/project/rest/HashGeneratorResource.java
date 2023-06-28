package com.project.rest;

import com.project.service.HashGeneratorService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Path("/generate-hash")
public class HashGeneratorResource {

    @Inject
    private HashGeneratorService hashGeneratorService;

    @POST
    public Response generateHash(@QueryParam("value") String value) {
        try {
            return Response.ok(hashGeneratorService.generateSha256Hash(value)).build();
        } catch (NoSuchAlgorithmException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                            String.format("Could not generate hash for value: %s", value))
                    .build();
        }
    }
}
