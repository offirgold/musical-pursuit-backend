package org.musical_pursuit.services.db;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.musical_pursuit.services.src.objects.Song;
import org.musical_pursuit.services.src.objects.Artist;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.musical_pursuit.services.CustomExceptions.NullJdbcException;
import sun.lwawt.macosx.CSystemTray;

/**
 * this class will create our db tables and fill them in from the csv file using SQL queries.
 * then SongBlob class will use it to getRandomly according to attributes our questions and
 * answers.
 */

public class JDBC {
    Connection conn; // DB connection

    /**
     * constructor
     */
    public JDBC(){
        this.conn = null;
        //open connection on construction
        while(this.conn == null){
            try {
                this.openConnection("/Users/davidgoldberg/IdeaProjects/musical_pursuit_backend/src/main/resources/application.properties");
//                this.openConnection("C:\\Users\\David\\application.properties");
            } catch (NullJdbcException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @return true if the connection was successfully set
     */
    /*TODO: here config file connection details*/
    public Connection openConnection(String configFileName /*"src/main/resources/application.properties"*/) throws NullJdbcException {

        String host = "";
        String port = "";
        String schema = "";
        String user = "";
        String password = "";

        System.out.print("Trying to connect... ");

        try (InputStream input = new FileInputStream(configFileName)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // creating the connection. Parameters should be taken from config file.
            host = prop.getProperty("host").toString();
            port = prop.getProperty("port").toString();
            schema = prop.getProperty("schema").toString();
            user = prop.getProperty("user").toString();
            password = prop.getProperty("password").toString();

        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: end program...
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema
                    /*+ "?serverTimezone=UTC"*/, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unable to connect - " + e.getMessage());
            return null;
        }
        System.out.println("Connected!");
        return this.conn;
    }

    /*Assuming db already exists, this function will create the 3 tables
     * we need with all primary keys and foreign keys
     */
    public void createDB() throws IOException, SqlToolError {

        SqlFile sf = new SqlFile(new File(
                "src/main/java/com/example/dbcourse/Musical_Pursuit/db/musical_db_creator.sql"));
        sf.setConnection(conn);
        try {
            sf.execute();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void fillDBfromCSV(String csvFileName) throws IOException, SqlToolError {

        SqlFile sf = new SqlFile(new File(
                "src/main/java/com/example/dbcourse/Musical_Pursuit/db/musical_db_inserts.sql"));
        sf.setConnection(conn);
        try {
            sf.execute();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /* @param context: background for the question,
     *e.g. artist of a question about who composed this song
     * @param about: the right answer
     * @param correctAmount:
     * @param wrongAmount:
     * @return: HashMap with list of right answers as song objects (and key 1 e.g.)
     *  and list of wrong answers as song objects (and key 0 e.g.)
     */
    public HashMap<Integer, List<Song>> getSongsDtl(int correctAmount, int wrongAmount) {

        HashMap<Integer, List<Song>> answers = new HashMap<>();
        List<Song> right = new ArrayList<>();
        List<Song> wrong = new ArrayList<>();
        String corr = Integer.toString(correctAmount);
        String wron = Integer.toString(wrongAmount);
        String artist_id, title, song_id, getRightAns, getWrongAns, artist_name = null;
        int year;
        ResultSet rs, rs1;
        StringBuilder notRightOnes = new StringBuilder();

        //create statement
        try {
            Statement stmt = this.conn.createStatement();

            //get right answers
            try {
                //Statement stmt = this.conn.createStatement();

                for (int i = 0; i < correctAmount; i++) {
                    //clear db by dropping schema and creating again the db
                    getRightAns = "SELECT * " +
                            "FROM  `songs` " +
                            "ORDER BY RAND() " +
                            "LIMIT 1";

                    rs = stmt.executeQuery(getRightAns);

                    if (rs.next()) {
                        song_id = rs.getString("song_id");
                        year = rs.getInt("year");
                        title = rs.getString("title");
                        artist_id = rs.getString("artist_id");

                        getRightAns = "SELECT  `name` " +
                                "FROM  `artists` " +
                                "WHERE `id`='" + artist_id + "'";
                        rs1 = stmt.executeQuery(getRightAns);

                        if (rs1.next()) {
                            artist_name = rs1.getString("name");
                        }

                        right.add(new Song(song_id, title, year, new Artist(artist_id, artist_name)));
                    }
                }

                //add to HashMap right answers
                answers.put(0, right);

            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            }

            //get wrong ones
            try {
                notRightOnes.append("`title`= '" + right.get(0).getTitle() + "'");

                //Statement stmt = this.conn.createStatement();
                for (int n = 1; n < right.size(); n++) {
                    title = (right.get(n).getTitle());
                    notRightOnes.append(" OR `title`= '" + title + "'");
                }

                for (int j = 0; j < wrongAmount; j++) {
                    //clear db by dropping schema and creating again the db
                    getWrongAns = "SELECT * " +
                            "FROM  `songs` " +
                            "WHERE NOT (" + notRightOnes + ") " +
                            "ORDER BY RAND() " +
                            "LIMIT 1";

                    rs = stmt.executeQuery(getWrongAns);

                    if (rs.next()) {
                        song_id = rs.getString("song_id");
                        year = rs.getInt("year");
                        title = rs.getString("title");
                        artist_id = rs.getString("artist_id");

                        getWrongAns = "SELECT  `name` " +
                                "FROM  `artists` " +
                                "WHERE `id`='" + artist_id + "'";
                        rs1 = stmt.executeQuery(getWrongAns);

                        if (rs1.next()) {
                            artist_name = rs1.getString("name");
                        }

                        wrong.add(new Song(song_id, title, year, new Artist(artist_id, artist_name)));
                    }
                }

                //add to HashMap wrong answers
                answers.put(1, wrong);

            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            }

            //catch of failed create statement
        } catch (NullPointerException | SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return answers;
    }

    /**
     * close the connection
     */
    public void closeConnection() {

        try {
            Statement stmt = this.conn.createStatement();

            //clear db by dropping schema and creating again the db
            String drop = "DROP SCHEMA IF EXISTS `musical_pursuit`";
            stmt.executeUpdate(drop);

            String createDB = "CREATE IF NOT EXISTS DATABASE `musical_pursuit`";
            stmt.executeUpdate(createDB);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

            // closing the connection
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the connection - " + e.getMessage());
            }
        }
    }
}
