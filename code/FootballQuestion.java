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
     * Defines if the question's options should be presented as the player's name or shirt number
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
    }
    
    /**
     * Translates the football players to options
     * @param useShirts Defines if the options should have the players' names or shirt numbers
     */
    protected void playerToOption(boolean useShirts){
        for(FootballPlayer player: playerArray){
            Option opt;
            if(useShirts == true){
                opt = new Option(player.correct, player.shirtNumber);    
            }
            else{
                opt = new Option(player.correct, player.name);
            }   
            this.optionArray.add(opt);
        }
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

