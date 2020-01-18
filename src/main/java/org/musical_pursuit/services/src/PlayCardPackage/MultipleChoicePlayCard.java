package org.musical_pursuit.services.src.PlayCardPackage;

// A playcard with multiple correct answers to a given question, where the player attempts to find them all.
public class MultipleChoicePlayCard implements IPlayCard{

    private String question;
    private String[] incorrectAnswers;
    private String[] correctAnswers;


    public MultipleChoicePlayCard(String question, String[] incorrectAnswers, String[] correctAnswers) {
        this.question = question;
        this.incorrectAnswers = incorrectAnswers;
        this.correctAnswers = correctAnswers;
    }


    public String getQuestion() {
        return question;
    }


    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }


    public String[] getCorrectAnswers() {
        return correctAnswers;
    }
}
