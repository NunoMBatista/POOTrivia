/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class used to manage the arts questions
 */
public class ArtsQuestion extends Question {    
    /**
     * Constructor for the ArtsQuestion class
     * @param scoreValue Defines the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     * @param quickFact Contains a quick fact about the question 
     */
    public ArtsQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, String quickFact){
        super(scoreValue, questionText, optionArray, quickFact);
    }
    
    /**
     * Method used to set the question to it's easy mode settings
     * The arts category's easy mode removes some of the options
     * @param howMany Defines how many options to keep on the option array
     */
    @Override
    protected void setEasyMode (int howMany){
        ArrayList<Option> updatedArray = new ArrayList<>();
        // Keeps track of the number of questions selected so far in the loop 
        int addedQuestions = 0;
        // Checks if the correct option has already been selected
        boolean addedCorrect = false;
      
        // Iterates through the option array in order to extract the correct option
        // as well as howMany - 1 other options
        for(Option option: this.optionArray){
            if(option.correct == true){
                updatedArray.add(option);
                // If the correct option is added, update the addedCorrect value accordingly
                addedCorrect = true;
            }
            else if(addedQuestions < howMany){
                updatedArray.add(option);
            }
            
            // Increments the option counter every time an option is added
            addedQuestions += 1;

            // The loop will end as soon as the correct option is added along side 
            // alongside howMany - 1 other options
            if((addedCorrect == true) && (addedQuestions == howMany)){
                break;
            }
        }
        this.optionArray = updatedArray;
    }

    /**
     * Method used to calculate the question's value
     * The arts category's score is given by [Base Score]*10
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return this.scoreValue * 10;
    }

}