package org.musical_pursuit.services.src.FactoryPackage;

import java.util.HashMap;
import java.util.List;

import org.musical_pursuit.services.db.JDBC;
import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;
import org.musical_pursuit.services.src.objects.Song;


public class AssociationPlayCardFactory implements IPlayCardFactory{

    JDBC jdbc;


    public AssociationPlayCardFactory(JDBC jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public IPlayCard CreatePlayCard() {

        /* Calling a function that build a query to send to the local database and receive 2 artists/bands. Lets call
        the return object "databaseOptions". These 2 "row" objects will only contain the name of artist/band that are
        used as the options. */

        /* Calling a function that build a query with the previously received artist/band to send to the local database
        and receive 30 song/album. Lets call the return object "databaseAssociables". These 30 "row" objects will
        only contain the name of song/album and the name of the artist/band that they belong to. */

//        List<Song> databaseAssociables = jdbc.getBandSongs();

        String[] options = new String[2];
        HashMap<String, Integer> associables = new HashMap<>(30);

//        options[0] = databaseAssociables.get(0).getArtist().getArtistName();
//        for (int i = 1; i < databaseAssociables.size(); ++i) {
//            String artistName = databaseAssociables.get(i).getArtist().getArtistName();
//            if (!artistName.equals(options[0])) {
//                options[1] = artistName;
//                break;
//            }
//        }

//        for (int i = 0; i < databaseAssociables.size; ++i) {
//          String songName = databaseAssociables.get(i).getTitle();
//          String artistName = databaseAssociables.get(i).getArtist().getArtistName();
//            if (artistName.equals(options[0]) {
//                    associables.put(songName, 0);
//            } else {
//                    associables.put(SongName, 1);
//            }
//        }


//        return new AssociationPlayCard(options, associables);
        return null;
    }
}
