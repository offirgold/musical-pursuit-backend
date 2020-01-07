package org.musical_pursuit.services;

import org.musical_pursuit.services.src.PlayCardPackage.SingleAnswerPlayCard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/pursuit")
public class MusicalPursuitApplication {

    @Path("/0")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson() {
        ObjectMapper mapper = new ObjectMapper();
        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
                y);
        String json = null;
        try {
            json = mapper.writeValueAsString(x);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }


    @Path("/1")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson_2() {
        ObjectMapper mapper = new ObjectMapper();
        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 111111, Billy Talent release the song ______.", "Red Flag",
                y);
        String json = null;
        try {
            json = mapper.writeValueAsString(x);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }


    @Path("/2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson_3() {
        ObjectMapper mapper = new ObjectMapper();
        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 222222, Billy Talent release the song ______.", "Red Flag",
                y);
        String json = null;
        try {
            json = mapper.writeValueAsString(x);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }
}