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
    */
   public SwimmingQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray){
       super(scoreValue, questionText, optionArray);
   }

   /**
    * Method used to calculate the question's value
    * @return returns the question's value after the category boosts
    */
   @Override
   protected int scoreCalculation(){
       return this.scoreValue + 3 + 10;
   }
}
