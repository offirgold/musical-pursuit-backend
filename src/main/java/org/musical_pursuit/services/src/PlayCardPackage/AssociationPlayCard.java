package org.musical_pursuit.services.src.PlayCardPackage;

import java.util.HashMap;

// A lighting round type playcard with a set of options and an array of strings the relate to one of the options.
public class AssociationPlayCard implements IPlayCard{

    private String question = "Choose the band that released the following songs.";
    private String[] options;
    private HashMap<String, Integer> associables;


    public AssociationPlayCard(String[] options, HashMap<String, Integer> associables) {
        this.options = options;
        this.associables = associables;
    }


    public String getQuestion() {
        return question;
    }


    public String[] getOptions() {
        return options;
    }


    public HashMap<String, Integer> getAssociationables() {
        return associables;
    }
}
