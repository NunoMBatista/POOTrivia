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
     * Defines how many of the options will be included
     */
    protected int nIncluded;

    /**
     * Constructor for the ArtsQuestion class
     * @param scoreValue Defines the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     */
    public ArtsQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, int nIncluded){
        super(scoreValue, questionText, optionArray);
        this.optionArray = limitOptions(nIncluded);
        Collections.shuffle(this.optionArray);
    }

    /**
     * Method used to limit the options in the questions' array
     * @param howMany Defines how many questions we want in the new array
     * @return Returns an updated array with the desired number of options, including the correct one
     */
    protected ArrayList<Option> limitOptions (int howMany){
        ArrayList<Option> updatedArray = new ArrayList<>();
        int addedQuestions = 0;
        boolean addedCorrect = false; 
        for(Option option: this.optionArray){
            if(option.correct == true){
                updatedArray.add(option);
                addedCorrect = true;
            }
            else if(addedQuestions < howMany-1){
                updatedArray.add(option);
                addedQuestions += 1;
            }
            if((addedCorrect == true) && (addedQuestions == howMany-1)){
                break;
            }
        }
        return updatedArray;
    }

    /**
     * Method used to calculate the question's value
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return this.scoreValue * 10;
    }

}