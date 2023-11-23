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

    protected String quickFact;

    /**
     * Constructor class for the Question class
     * @param scoreValue Contains the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     */
    public Question(int scoreValue, String questionText, ArrayList<Option> optionArray, String quickFact){
        this.scoreValue = scoreValue;
        this.questionText = questionText;
        this.optionArray = optionArray;
        this.quickFact = quickFact;        
    }
    /**
     * Method used to calculate the question's score value
     * @return the question's score
     */
    abstract protected int scoreCalculation();

    abstract protected void setEasyMode(int n);

    /**
     * Method used to present the question in a GUI to the player
     */
    // protected void presentQuestion(){
    //     ...
    // }
    
    protected void shuffleOptions(){
        Collections.shuffle(this.optionArray);
    }

    /**
     * Method used to print the question's text along with it's options
     * @return out A string with the question's text along with it's options
     */
    public String toString(){
        String out = "For " + this.scoreValue + " points!\n" + this.questionText;
        char counter = 'A';
        for(Option opt: this.optionArray){
            out += "\n" + counter + " - " + opt;
            counter += 1;
        }
        out += "\n[" + quickFact + "]";
        return out; 
    }
        
}