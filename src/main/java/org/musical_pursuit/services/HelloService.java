package org.musical_pursuit.services;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/hello")
public class HelloService {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson(@PathParam("id") String name) {

        return Response
                .status(Response.Status.OK)
                .entity("Hello " + name)
                .build();
    }
}
