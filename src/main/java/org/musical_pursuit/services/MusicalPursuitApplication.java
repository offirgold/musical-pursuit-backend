package org.musical_pursuit.services;

import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.FactoryPackage.AssociationPlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.MultipleChoicePlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.SingleAnswerPlayCardFactory;
import org.musical_pursuit.services.src.PlayCardPackage.AssociationPlayCard;
import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;
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

    JDBC jdbc = new JDBC();
    SingleAnswerPlayCardFactory singleAnswerPlayCardFactory = new SingleAnswerPlayCardFactory(jdbc);
    MultipleChoicePlayCardFactory multipleChoicePlayCardFactory = new MultipleChoicePlayCardFactory(jdbc);
    AssociationPlayCardFactory associationPlayCardFactory = new AssociationPlayCardFactory(jdbc);

    @Path("/0")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson() {
        ObjectMapper mapper = new ObjectMapper();
//        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
//        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
//                y);

        IPlayCard newPlaycard = singleAnswerPlayCardFactory.CreatePlayCard();
        String json = null;

        try {
            json = mapper.writeValueAsString(newPlaycard);
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
//        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
//        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 111111, Billy Talent release the song ______.", "Red Flag",
//                y);

        IPlayCard newPlaycard = multipleChoicePlayCardFactory.CreatePlayCard();
        String json = null;

        try {
            json = mapper.writeValueAsString(newPlaycard);
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
//        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
//        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 222222, Billy Talent release the song ______.", "Red Flag",
//                y);

        IPlayCard newPlaycard = associationPlayCardFactory.CreatePlayCard();
        String json = null;

        try {
            json = mapper.writeValueAsString(newPlaycard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }
}