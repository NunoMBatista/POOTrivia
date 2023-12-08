/**
* @author Nuno Batista 
* @author Diogo Joaquim
* @version 1.0
*/

import java.util.*;

/**
* Class used to manage swimming questions
*/
public class SwimmingQuestion extends Question{
   /**
    * Constructor class for the SwimmingQuestiin class
    * @param scoreValue Contains the value of the question
    * @param questionText Contains the question's text
    * @param optionArray Contains the question's options
    * @param quickFact Contains a quick fact about the question 
    */
   public SwimmingQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, String quickFact){
       super(scoreValue, questionText, optionArray, quickFact);
   }

    /**
     * Method used to set the question to it's easy mode settings
     * The swimming category's easy mode is the same as the regular one
     */   
   protected void setEasyMode(int a){}; 

   /**
    * Method used to calculate the question's value
    * @return returns the question's value after the category boosts
    */
   @Override
   protected int scoreCalculation(){
       return this.scoreValue + 3 + 10;
   }
}
