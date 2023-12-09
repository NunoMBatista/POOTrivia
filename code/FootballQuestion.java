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
     * Contains the later to be converted to options players
     */
    protected ArrayList<FootballPlayer> playerArray = new ArrayList<>();

    /**
     * Constructor for the FootballQuestion class
     * @param scoreValue Defines the value of the question
     * @param questionText Contains the question's text
     * @param optionArray Contains the question's options
     * @param playerArray Contains the football players, later to be converted to options
     * @param quickFact Contains a quick fact about the question
     */
    public FootballQuestion(int scoreValue, String questionText, ArrayList<Option> optionArray, ArrayList<FootballPlayer> playerArray, String quickFact){
        super(scoreValue, questionText, optionArray, quickFact);
        this.playerArray = playerArray; 
        // By default, use the shirt numbers as options
        this.optionArray = playerToOption(true);
    }
    
    /**
     * Translates the football players into options
     * @param useShirts Defines if the options should have the players' names or player's shirt numbers
     */
    protected ArrayList<Option> playerToOption(boolean useShirts){
        ArrayList<Option> updatedArray = new ArrayList<>();
        // Iterates through the player array adding every shirt number or player as an option
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
     * Method used to make the game use the player's names
     * @param 
     */
    @Override
    protected void setEasyMode(int a){
        this.optionArray = playerToOption(false);
    }
    /**
     * Method used to calculate the question's value
     * @return returns the question's value after the category boosts
     */
    @Override
    protected int scoreCalculation(){
        return this.scoreValue + 3 + 1;
    }

    /**
     * Method used to get the question's player array
     * @return the question's player array
     */
    public ArrayList<FootballPlayer> getPlayerArray(){
        return this.playerArray;
    }

    /**
     * Method used to set the question's player array
     * @param playerArray the question's player array
     */
    public void setPlayerArray(ArrayList<FootballPlayer> playerArray){
        this.playerArray = playerArray;
    }

}

