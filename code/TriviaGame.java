/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class to manage an instance of the game 
 */
public class TriviaGame {
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
     * The player's final score
     */
    protected int finalScore = 0;
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
}
