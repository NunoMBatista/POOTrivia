/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class used to manage ski questions
 */

public class SkiQuestion extends Question{
    /**
     * Constructor class for the SkiQuestion class
     * @param scoreValue Contains the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     */
    public SkiQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray){
        super(scoreValue, questionText, optionArray);
    }

    protected void setEasyMode(int a){}; 
    /**
     * Method used to calculate the question's value
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return (this.scoreValue + 3) * 2;
    }
}
