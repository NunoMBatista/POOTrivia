/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class used to manage science questions
 */

public class ScienceQuestion extends Question{
    /**
     *  Contains the question's easy options
     */
    protected ArrayList<Option> easyOptionArray;

    /**
     * Constructor class for the ScienceQuestion class
     * @param scoreValue Contains the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     * @param easyOptionArray Contains the question's easy options
     * @param quickFact Contains a quick fact about the question 
     */
    public ScienceQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, ArrayList<Option> easyOptionArray, String quickFact){
        super(scoreValue, questionText, optionArray, quickFact);
        this.easyOptionArray = easyOptionArray; 
    }

    /**
     * Method used to set the question to it's easy mode settings
     * The science category's easy mode swaps the regular option array 
     */
    @Override
    protected void setEasyMode(int a){
        this.optionArray = this.easyOptionArray;
    }
    /**
     * Method used to calculate the question's value
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return this.scoreValue + 5;
    }

    /**
     * Method used to set the question's easy option array
     * @param easyOptionArray the question's easy option array;
     */
    public void setEasyOptionArray(ArrayList<Option> easyOptionArray){
        this.easyOptionArray = easyOptionArray;
    }

    /**
     * Method used to get the question's easy option array
     * @return the question's easy option array
     */
    public ArrayList<Option> getEasyOptionArray(){
        return this.easyOptionArray;
    }
}
