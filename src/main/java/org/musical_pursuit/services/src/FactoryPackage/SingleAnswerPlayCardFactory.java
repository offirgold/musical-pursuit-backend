package org.musical_pursuit.services.src.FactoryPackage;

import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;
import org.musical_pursuit.services.src.objects.Song;

import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class SingleAnswerPlayCardFactory implements IPlayCardFactory{

    JDBC jdbc;


    public SingleAnswerPlayCardFactory(JDBC jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public IPlayCard CreatePlayCard() {

        Random randomizer = new Random();

        int about =  randomizer.nextInt(2);

//        HashMap<Integer, List< Song >> answers =  jdbc.getSongsDtl(0, 1, 3);

        /* Calling a function that uses the "about" array and to build a query to send to the local database and
        receive a row. Let's call the object type that contains the data Row and the specific instance row. This
        "row" object will contain the year, the name of the song/album/artist that are asked about.*/

        String question;
        String correctAnswer;

        //Song correctSong = answers.get(0).get(0);
        //Artist correctArtist = correctSong.getArtist()
        //List<Song> incorrectSongs = answers.get(1);

        switch (about) {
            case 0:
                //question = "Which of the following songs was released by " + correctArtist.getArtistName() + " in " + String.valueOf(correctSong.getYear()) + "?";
                //correctAnswer = correctSong.getTitle();
                break;
            case 1:
                //question = "Which of the following artists has released the song " + correctSong.getTitle() + " in " + String.valueOf(correctSong.getYear()) + "?";
                //correctAnswer = correctArtist.getArtistName();
                break;
            default: System.out.println("Something is wrong with 'about'");
        }

        /* Calling a function that uses the "about" array to build a query to send to the local database and
        receive 3 rows. Lets call the return object falseRows. These 3 "row" objects will contain the year, the name of
        the song/album/artist that are used as the incorrect answers.*/

        String[] incorrectAnswers = new String[3];
        switch (about) {
            case 0:
//                correctAnswer = row.song;
//                for (int i = 0; i < incorrectAnswers.length; ++i) {
//                    incorrectAnswers[i] = incorrectSongs.get(i).getTitle();
//                }
                break;
            case 1:
//                correctAnswer = row.album;
//                for (int i = 0; i < incorrectAnswers.length; ++i) {
//                    incorrectAnswers[i] = incorrectSongs.get(i).getArtist().getArtistName();
//                }
                break;
            default: System.out.println("Something is wrong with 'about'");
        }

//        return new SingleAnswerPlayCard(question, correctAnswer, incorrectAnswers);
        return null;
    }
}
