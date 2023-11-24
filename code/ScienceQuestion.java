/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.io.Serializable;
import java.util.*;

/**
 * Class used to manage science questions
 */

public class ScienceQuestion extends Question{
    /**
     *  Contains the question's hard options 
     */
    protected ArrayList<Option> easyOptionArray;
    /**
     * Defines if the easy options should be presented 
     */
    protected boolean useEasy;
    /**
     * Constructor class for the ScienceQuestion class
     * @param scoreValue Contains the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     * @param easyOptionArray Contains the question's easy options
     */
    public ScienceQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, ArrayList<Option> easyOptionArray, String quickFact){
        super(scoreValue, questionText, optionArray, quickFact);
        this.easyOptionArray = easyOptionArray; 
    }

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
}
