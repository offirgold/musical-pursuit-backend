package org.musical_pursuit.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.FactoryPackage.AssociationPlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.MultipleChoicePlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.SingleAnswerPlayCardFactory;

import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.musical_pursuit.services.src.PlayCardPackage.SingleAnswerPlayCard;

import java.util.Arrays;


@Path("/pursuit")
public class MusicalPursuitApplication {

    int gameLength = 5;
    JDBC jdbc = new JDBC();


//    @Path("/MainMenu")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String mainMenu() {
//        return null;
//    }


    @GET
    @Path("/Play")
//    @Produces(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response getFullGame() {
        SingleAnswerPlayCardFactory singleAnswerPlayCardFactory = new SingleAnswerPlayCardFactory(jdbc);

        ObjectMapper mapper = new ObjectMapper();
        IPlayCard[] fullGame = new IPlayCard[gameLength];
        String[] result = new String[gameLength];

        for (int i = 0; i < gameLength; ++i) {
            fullGame[i] = singleAnswerPlayCardFactory.CreatePlayCard();
        }

        for (int i = 0; i < gameLength; ++i) {
            try {
                result[i] = mapper.writeValueAsString(fullGame[i]);
            } catch (Exception e) {
                System.out.println("problem in making the String array in iteration " + i);
            }
        }

        return Response
                .status(Response.Status.OK)
                .entity(Arrays.toString(result))
                .build();
    }

    @POST
    @Path("/submitScore")
    public Response receiveHello(@FormParam("id") String id, @FormParam("name") String name, @FormParam("score") int score) {

        jdbc.insertNewPlayer(id, name, score);

        return Response.status(200).build();
    }
}
