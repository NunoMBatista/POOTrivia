/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class to manage questions
 */
public abstract class Question{
    /**
     * Defines the value of the question
     */
    protected int scoreValue;
    /**
     * Contains the question's text
     */
    protected String questionText; 
    /**
     * Contains the question's options
     */
    protected ArrayList<Option> optionArray;

    /**
     * Constructor class for the Question class
     * @param scoreValue Contains the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     */
    public Question(int scoreValue, String questionText, ArrayList<Option> optionArray){
        this.scoreValue = scoreValue;
        this.questionText = questionText;
        this.optionArray = optionArray;
    }
    /**
     * Method used to calculate the question's score value
     * @return the question's score
     */
    abstract protected int scoreCalculation();

    /**
     * Method used to present the question in a GUI to the player
     */
    // protected void presentQuestion(){
    //     ...
    // }
        
}