package org.musical_pursuit.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.FactoryPackage.AssociationPlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.MultipleChoicePlayCardFactory;
import org.musical_pursuit.services.src.FactoryPackage.SingleAnswerPlayCardFactory;

import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.musical_pursuit.services.src.PlayCardPackage.SingleAnswerPlayCard;

import java.util.Arrays;


@Path("/pursuit")
public class MusicalPursuitApplication {

    int singleAnswerCardAmount = 3, multipleAnswerCardAmount = 0, associationCardAmount = 0;
    int gameLength = singleAnswerCardAmount + multipleAnswerCardAmount + associationCardAmount;
    JDBC jdbc = new JDBC();
    SingleAnswerPlayCardFactory singleAnswerPlayCardFactory = new SingleAnswerPlayCardFactory(jdbc);
    MultipleChoicePlayCardFactory multipleChoicePlayCardFactory = new MultipleChoicePlayCardFactory(jdbc);
    AssociationPlayCardFactory associationPlayCardFactory = new AssociationPlayCardFactory(jdbc);


//    @Path("/MainMenu")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String mainMenu() {
//        return null;
//    }


//  Used for testing.
//    @Path("/MainMenu2")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String returnVoid() {
//        int x = 7;
//        return null;
//    }


    public IPlayCard[] mergeArrays(IPlayCard[] arr1, IPlayCard[] arr2, IPlayCard[] arr3) {

        int index = 0;
        IPlayCard[] merged = new IPlayCard[gameLength];

        for(IPlayCard iPlayCard: arr1) {
            merged[index] = iPlayCard;
            ++index;
        }

        for(IPlayCard iPlayCard: arr2) {
            merged[index] = iPlayCard;
            ++index;
        }

        for(IPlayCard iPlayCard: arr3) {
            merged[index] = iPlayCard;
            ++index;
        }

        return merged;
    }


    @GET
    @Path("/Play")
//    @Produces(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response getFullGame() {

        int levelIndex = 0;
        ObjectMapper mapper = new ObjectMapper();
        IPlayCard[] fullGame = new IPlayCard[gameLength];
        String[] result = new String[gameLength];

//        IPlayCard[] singleArray = new IPlayCard[SingleAnswerCardAmount];
//        IPlayCard[] multipleArray = new IPlayCard[MultipleAnswerCardAmount];
//        IPlayCard[] associationArray = new IPlayCard[AssociationCardAmount];
//        ExecutorService executor = Executors.newFixedThreadPool(gameLength);

        for(int i = 0; i < singleAnswerCardAmount; ++i) {
//            Callable<IPlayCard> thread = new MyThread(singleAnswerPlayCardFactory);
//            singleArray[i] = executor.submit(thread);
            fullGame[levelIndex] = singleAnswerPlayCardFactory.CreatePlayCard();
            ++levelIndex;
        }

        for(int i = 0; i < multipleAnswerCardAmount; ++i) {
//            Callable<IPlayCard> thread = new MyThread(multipleChoicePlayCardFactory);
//            multipleArray[i] = executor.submit(thread);
            fullGame[levelIndex] = multipleChoicePlayCardFactory.CreatePlayCard();
            ++levelIndex;
        }

        for(int i = 0; i < associationCardAmount; ++i) {
//            Callable<IPlayCard> thread = new MyThread(associationPlayCardFactory);
//            associationArray[i] = executor.submit(thread);
            fullGame[levelIndex] = associationPlayCardFactory.CreatePlayCard();
            ++levelIndex;
        }

//        fullGame = mergeArrays(singleArray, multipleArray, associationArray)

        for (int i = 0; i < gameLength; ++i) {
            try {
                result[i] = mapper.writeValueAsString(fullGame[i]);
            } catch (Exception e) {
                System.out.println("problem in making the String array in iteration " + i);
            }
        }

//        executor.shutdown();

        return Response
                .status(Response.Status.OK)
                .entity(Arrays.toString(result))
                .build();

//        IPlayCard[] fullGame2 = new IPlayCard[gameLength];
//        String[] result2 = new String[2];
//
//        String[] y1 = {"Alive & Amplified", "Permanent", "Dance Dance", "Red Flag"};
//        IPlayCard x1 = new SingleAnswerPlayCard("In 000000, Billy Talent release the song ______.", "Red Flag",
//                y1);
//        String[] y2 = {"Sick Boy", "Radioactive", "Young Blood", "Houdini"};
//        IPlayCard x2 = new SingleAnswerPlayCard("In 111111, Foster the People release the song ______.", "Houdini",
//                y2);
//
//        fullGame2[0] = x1;
//        fullGame2[1] = x2;
//
//        for (int i = 0; i < 2; ++i) {
//            try {
//                result2[i] = mapper.writeValueAsString(fullGame2[i]);
//            } catch (Exception e) {
//                System.out.println("problem casting to JSONObject in iteration " + i);
//            }
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(Arrays.toString(result2))
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
}
