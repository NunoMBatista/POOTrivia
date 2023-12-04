/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.io.Serializable;
import java.util.*;

/**
 * Class to manage questions
 */
public abstract class Question implements Serializable{
    /**
     * Defines the value of the question before the category boosts
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
     * Contains a quick fact about the question
     */
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

    /**
     * Method used to set a question to it's respective easy mode setting
     * @param n Number of options to show in case the question's easy mode setting consists of showing less options
     */
    abstract protected void setEasyMode(int n);

    /**
     * Method used to shuffle the positions of the question's options
     */
    protected void shuffleOptions(){
        Collections.shuffle(this.optionArray);
    }

    /**
     * Method used to get the question's text
     * @return the question's text
     */
    public String getText(){
        return this.questionText;
    }
    
    /**
     * Method used to get the question's option array
     * @return the question's option array
     */
    public ArrayList<Option> getOptionArray(){
        return this.optionArray;
    }

    /**
     * Method used to get the question's score value before the category boosts
     */
    public int getScoreValue(){
        return this.scoreValue;
    }
    
    /**
     * Method used to get the question's quick fact
     */
    public String getQuickFact(){
        return this.quickFact;
    }

    /**
     * Method used to set the question's text
     */
    public void setText(String text){
        this.questionText = text; 
    }

    /**
     * Method used to set the question's option array
     * @param optionArray the option array
     */
    public void setOptionArray(ArrayList<Option> optionArray){
        this.optionArray = optionArray;
    }

    /**
     * Method used to set the question's score value before the category boosts
     * @param score the score value before the category boosts
     */
    public void setScoreValue(int score){
        this.scoreValue = score;
    }

    /**
     * Methos used to set the question's quick fact
     * @param quickFact the question's quick fact
     */
    public void setQuickFact(String quickFact){
        this.quickFact = quickFact;
    }

    /**
     * Method used to print the question's text along with it's options on the stdout
     * @return out A string with the question's text along with it's options
     */
    public String toString(){
        String out = "For " + this.scoreValue + " points!\n" + this.questionText;
        char counter = 'A';
        for(Option opt: this.optionArray){
            out += "\n" + counter + " - " + opt;
            counter += 1;
        }
        return out; 
    }
}