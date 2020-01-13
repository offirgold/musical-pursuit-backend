package org.musical_pursuit.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.FactoryPackage.AssociationPlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.MultipleChoicePlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.SingleAnswerPlayCardFactory;

import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.musical_pursuit.services.src.PlayCardPackage.SingleAnswerPlayCard;

import java.util.Arrays;


@Path("/pursuit")
public class MusicalPursuitApplication {

    int SingleAnswerCardAmount = 3, MultipleAnswerCardAmount = 3, AssociationCardAmount = 1;
    int gameLength = SingleAnswerCardAmount + MultipleAnswerCardAmount + AssociationCardAmount;
    JDBC jdbc = new JDBC();
    SingleAnswerPlayCardFactory singleAnswerPlayCardFactory = new SingleAnswerPlayCardFactory(jdbc);
    MultipleChoicePlayCardFactory multipleChoicePlayCardFactory = new MultipleChoicePlayCardFactory(jdbc);
    AssociationPlayCardFactory associationPlayCardFactory = new AssociationPlayCardFactory(jdbc);


    @Path("/MainMenu")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String mainMenu() {
        return null;
    }


//  Used for testing.
//    @Path("/MainMenu2")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String returnVoid() {
//        int x = 7;
//        return null;
//    }


    @GET
    @Path("/Play")
//    @Produces(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response getFullGame() {

        int levelIndex = 0;
        ObjectMapper mapper = new ObjectMapper();
        IPlayCard[] fullGame = new IPlayCard[gameLength];
        JSONObject finalJson = new JSONObject();

//        for(int i = 0; i < SingleAnswerCardAmount; ++i) {
//            fullGame[levelIndex] = singleAnswerPlayCardFactory.CreatePlayCard();
//            ++levelIndex;
//        }
//
//        for(int i = 0; i < MultipleAnswerCardAmount; ++i) {
//            fullGame[levelIndex] = multipleChoicePlayCardFactory.CreatePlayCard();
//            ++levelIndex;
//        }
//
//        for(int i = 0; i < AssociationCardAmount; ++i) {
//            fullGame[levelIndex] = associationPlayCardFactory.CreatePlayCard();
//            ++levelIndex;
//        }
//
//        for (int i = 0; i < gameLength; ++i) {
//            try {
//                finalJson.put("level " + (i + 1), mapper.writeValueAsString(fullGame[i]));
//                System.out.println(finalJson);
//            } catch (Exception e) {
//                System.out.println("problem casting to JSONObject in iteration " + i);
//            }
//        }

        IPlayCard[] fullGame2 = new IPlayCard[gameLength];
        String[] result = new String[2];

        String[] y1 = {"Alive & Amplified", "Permanent", "Dance Dance"};
        IPlayCard x1 = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
                y1);
        String[] y2 = {"Sick Boy", "Radioactive", "Young Blood"};
        IPlayCard x2 = new SingleAnswerPlayCard("In 111111, Foster the People release the song ______.", "Houdini",
                y2);

        fullGame2[0] = x1;
        fullGame2[1] = x2;

        for (int i = 0; i < 2; ++i) {
            try {
                result[i] = mapper.writeValueAsString(fullGame2[i]);
                finalJson.put("level " + (i + 1), mapper.writeValueAsString(fullGame2[i]));
//                System.out.println(mapper.writeValueAsString(fullGame2[i]));
            } catch (Exception e) {
                System.out.println("problem casting to JSONObject in iteration " + i);
            }
        }
//        System.out.println(finalJson);
        System.out.println(Arrays.toString(result));

        return Response
                .status(Response.Status.OK)
                .entity(Arrays.toString(result))
                .build();

//        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
//        IPlayCard x = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
//                y);
//
//        fullGame[0] = x;
//        fullGame[1] = x;
//
//        try {
//            finalJson.put("level 1", mapper.writeValueAsString(x));
//            finalJson.put("level 2", mapper.writeValueAsString(x));
//            System.out.println(finalJson);
//        } catch (Exception e) {
//            System.out.println("here");
//        }

//        String json = null;
//
//        try {
//            json = mapper.writeValueAsString(newPlaycard);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(json)
//                .build();
    }


//    @Path("/0")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response returnJson() {
//        ObjectMapper mapper = new ObjectMapper();
////        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
////        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
////                y);
//
//        IPlayCard newPlaycard = singleAnswerPlayCardFactory.CreatePlayCard();
//        String json = null;
//
//        try {
//            json = mapper.writeValueAsString(newPlaycard);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(json)
//                .build();
//    }
//
//
//    @Path("/1")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response returnJson_2() {
//        ObjectMapper mapper = new ObjectMapper();
////        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
////        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 111111, Billy Talent release the song ______.", "Red Flag",
////                y);
//
//        IPlayCard newPlaycard = multipleChoicePlayCardFactory.CreatePlayCard();
//        String json = null;
//
//        try {
//            json = mapper.writeValueAsString(newPlaycard);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(json)
//                .build();
//    }
//
//
//    @Path("/2")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response returnJson_3() {
//        ObjectMapper mapper = new ObjectMapper();
////        String[] y = {"Alive & Amplified", "Permanent", "Dance Dance"};
////        SingleAnswerPlayCard x = new SingleAnswerPlayCard("In 222222, Billy Talent release the song ______.", "Red Flag",
////                y);
//
//        IPlayCard newPlaycard = associationPlayCardFactory.CreatePlayCard();
//        String json = null;
//
//        try {
//            json = mapper.writeValueAsString(newPlaycard);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(json)
//                .build();
//    }
}