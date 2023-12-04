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
    
    /**
     * Constructor for the (empty) TriviaGame class
     */
    public TriviaGame(){};

    /**
     * Method used to calculate sum of every question's score values after the category boosts
     * @return The sum of every question's score values after the category boosts
     */
    protected int calculateTotalScore(){
        int totalScore = 0; 
        for(int idx = 0; idx < 5; idx++){
            if(this.correctIndices[idx] == true){
                totalScore += this.askedQuestions.get(idx).scoreCalculation();
            }
        }
        return totalScore;
    }

    /**
     * Method used to extract the initials of the player's name
     * @return the initials of the player's name 
     */
    protected String extractInitials(){
        // Starts by splitting the player's name on empty spaces
        String[] splitName = this.playerName.split(" ");
        String out = "";
        // Iterates through each one of the strings on the splitName array
        for(String it: splitName){
            // Concatenates the first character of the current iteration to the output string
            out += it.charAt(0);
        }
        return out;
    }

    /**
     * Method used to construct the name of the file in which the serialized TriviaGame object will be stored
     * @return the name of the file in which the serialized TriviaGame object will be stored
     */
    protected String constructObjectFileName(){
        String playerInitials = extractInitials();
        return "pootrivia_jogo_" + this.dateTime + "_" + playerInitials + ".dat";
    }

    /**
     * Method used to set the player's name
     * @param name the player's name
     */
    protected void setName(String name){
        this.playerName = name;
    }

    /**
     * Method used to set the game's date and time
     * @param dateTime the game's date and time
     */
    protected void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    /**
     * Method used to set the questions that the player will be asked throughout the game (or has already been asked)
     * @param askedQuestions the questions that the player will be asked throughout the game (or has already been asked)
     */
    protected void setAskedQuestions(ArrayList<Question> askedQuestions){
        this.askedQuestions = askedQuestions;
    }

    /**
     * Method used to keep set the game's information about which questions were answered correcly
     * @param correctIndices the game's information about which questions were answered correcly
     */
    protected void setCorrectIndices(boolean[] correctIndices){
        this.correctIndices = correctIndices;
    }

    /**
     * Method used to get the player's name
     * @return the player's name
     */
    protected String getName(){
        return this.playerName;
    }

    /**
     * Method used to get the game's date and time
     * @return the game's date and time
     */
    protected String getDateTime(){
        return this.dateTime;
    }

    /**
     * Method used to get the questions that the player will be asked throughout the game (or has already been asked)
     * @return askedQuestions the questions that the player will be asked throughout the game (or has already been asked)
     */
    protected ArrayList<Question> getAskedQuestions(){
        return this.askedQuestions;
    }

    /**
     * Method used to get the game's information about which questions were answered correctly 
     * @return the game's information about which questions were answered correctly
     */
    protected boolean[] getCorrectIndices(){
        return this.correctIndices;
    }

    /**
     * Method used to set the index corresponding to a certain question in the correctIndices array to the boolean value of the option chosen
     * @param idx The index of the question answered
     * @param correct The boolean value of the option chosen
     */
    protected void updateCorrectIndices(int gameStage, boolean correct){
        this.correctIndices[gameStage] = correct;
    }
}
