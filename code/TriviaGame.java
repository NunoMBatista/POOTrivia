/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.io.Serializable;
import java.util.*;

/**
 * Class to manage an instance of the game 
 */
public class TriviaGame implements Serializable{
    /**
     * Contains the player's name
     */
    protected String playerName;
    /**
     * Contains information about when the game was played
     */
    protected String dateTime;
    /**
     * Array with every question asked in the game
     */
    protected ArrayList<Question> askedQuestions = new ArrayList<>();
    /**
     * Defines if the question of indice i was answered correctly
     */
    protected boolean[] correctIndices;

    /**
     * Constructor for the TriviaGame class
     * @param playerName Contains the player's name
     * @param dateTime Array with every question asked in the game
     * @param askedQuestions Defines if the question of indice i was answered correctly
     * @param correctIndices The player's final score
     */
    public TriviaGame(String playerName, String dateTime, ArrayList<Question> askedQuestions, boolean[] correctIndices){
        this.playerName = playerName; 
        this.dateTime = dateTime;
        this.askedQuestions = askedQuestions;
        this.correctIndices = correctIndices;
    }
    public TriviaGame(){};

    protected int calculateTotalScore(){
        int totalScore = 0; 
        for(int idx = 0; idx < 5; idx++){
            if(this.correctIndices[idx] == true){
                totalScore += this.askedQuestions.get(idx).scoreCalculation();
            }
        }
        return totalScore;
    }

    protected String extractInitials(){
        String[] splitName = this.playerName.split(" ");
        String out = "";
        for(String it: splitName){
            out += it.charAt(0);
        }
        return out;
    }

    protected String constructObjectFileName(){
        String playerInitials = extractInitials();
        return "pootrivia_jogo_" + this.dateTime + "_" + playerInitials + ".dat";
    }

    protected void setName(String name){
        this.playerName = name; 
    }

    protected void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    protected void setAskedQuestions(ArrayList<Question> askedQuestions){
        this.askedQuestions = askedQuestions;
    }

    protected void setCorrectIndices(boolean[] correctIndices){
        this.correctIndices = correctIndices;
    }

    protected String getName(){
        return this.playerName;
    }

    /**
     * This method sets the index corresponding to the game stage in the correctIndices array to the "correctness" of the chosen option
     * @param idx
     * @param correct
     */
    protected void updateCorrectIndices(int gameStage, boolean correct){
        this.correctIndices[gameStage] = correct;
    }
}
