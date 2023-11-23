/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class used to manage FootballQuestions
 */
public class FootballQuestion extends Question{
    /**
     * Defines if the question's options should be presented as the player's name or player's shirt number
     */
    protected boolean useShirts; 

    /**
     * Contains the later to be converted to options players
     */
    ArrayList<FootballPlayer> playerArray = new ArrayList<>();

    /**
     * Constructor for the FootballQuestion class
     * @param scoreValue Defines the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     */
    public FootballQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, boolean useShirts, ArrayList<FootballPlayer> playerArray){
        super(scoreValue, questionText, optionArray);
        this.useShirts = useShirts; 
        this.playerArray = playerArray; 
        this.optionArray = playerToOption(useShirts);
    }
    
    /**
     * Translates the football players to options
     * @param useShirts Defines if the options should have the players' names or player's shirt numbers
     */
    protected ArrayList<Option> playerToOption(boolean useShirts){
        ArrayList<Option> updatedArray = new ArrayList<>();
        for(FootballPlayer player: this.playerArray){
            Option opt;
            if(useShirts == true){
                opt = new Option(player.correct, "Shirt number: " + player.shirtNumber);    
            }
            else{
                opt = new Option(player.correct, player.name);
            }   
            updatedArray.add(opt);
        }
        return updatedArray;
    }

    /**
     * Method used to calculate the question's value
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return this.scoreValue + 3 + 1;
    }

}

