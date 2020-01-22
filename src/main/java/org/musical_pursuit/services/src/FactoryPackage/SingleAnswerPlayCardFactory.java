package org.musical_pursuit.services.src.FactoryPackage;

import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;
import org.musical_pursuit.services.src.PlayCardPackage.SingleAnswerPlayCard;
import org.musical_pursuit.services.src.objects.Artist;
import org.musical_pursuit.services.src.objects.Song;

import java.util.HashMap;
import java.util.List;
import java.util.Random;


// A factory for creating playcard of the single answer type.
public class SingleAnswerPlayCardFactory implements IPlayCardFactory{

    JDBC jdbc; // The object used to get info from the database.
    int totalOptionsNum = 4; // The total number of options.


    public SingleAnswerPlayCardFactory(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * The function creates a playcard using jdbc.
     * @return the playcard that it created.
     */
    @Override
    public IPlayCard CreatePlayCard() {

        Random randomizer = new Random();
        String question;
        String correctAnswer;
        String[] options = new String[totalOptionsNum];

        while(true) {
            boolean restartLoop = false;
            int about =  randomizer.nextInt(2); // Decides whether the question is about a song or about an artist.
            int correctPosition = randomizer.nextInt(4);

            // Here the jdbc gets all of the options and specifies the correct answer.
            HashMap<Integer, List< Song >> answers =  jdbc.getSongsDtl(1, totalOptionsNum - 1);

            Song correctSong = answers.get(0).get(0);
            Artist correctArtist = correctSong.getArtist();
            List<Song> incorrect = answers.get(1);

            // build the question and gets the correct answer based on what the question is about.
            switch (about) {

                // The question is about a song.
                case 0:
                    question = "Which of the following songs was released by " + correctArtist.getArtistName() + " in " + String.valueOf(correctSong.getYear()) + "?";
                    try {
                        correctAnswer = correctSong.getTitle();
                    } catch (NullPointerException e) {
                        continue;
                    }
                    break;

                // The question is about an artist.
                case 1:
                    question = "Which of the following artists has released the song " + correctSong.getTitle() + " in " + String.valueOf(correctSong.getYear()) + "?";
                    try {
                        correctAnswer = correctArtist.getArtistName();
                    } catch (NullPointerException e) {
                        continue;
                    }
                    break;

                // Only enters if something went wrong with the jdbc's function of getting the options.
                default:
                    correctAnswer = "no answer";
                    question = "no question";
                    System.out.println("Something is wrong with 'about'");
            }

            options[correctPosition] = correctAnswer;
            int incorrectIndex = 0;

            switch (about) {

                case 0:
                    for (int i = 0; i < totalOptionsNum; ++i) {
                        if (i == correctPosition) {
                            continue;
                        }
                        try {
                            options[i] = incorrect.get(incorrectIndex).getTitle();
                            ++incorrectIndex;
                        } catch (NullPointerException e) {
                            restartLoop = true;
                            break;
                        }
                    }
                    if (restartLoop) {
                        continue;
                    }
                    break;

                case 1:
                    for (int i = 0; i < totalOptionsNum; ++i) {
                        if (i == correctPosition) {
                            continue;
                        }
                        try {
                            options[i] = incorrect.get(incorrectIndex).getArtist().getArtistName();
                            ++incorrectIndex;
                        } catch (NullPointerException e) {
                            restartLoop = true;
                            break;
                        }
                    }
                    if (restartLoop) {
                        continue;
                    }
                    break;

                default:
                    System.out.println("Something is wrong with the randomizer.");
                    continue;
            }

            return new SingleAnswerPlayCard(question, correctAnswer, options);
        }
//        return null;
    }
}
