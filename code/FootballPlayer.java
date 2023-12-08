/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.io.Serializable;

/**
 * Class used to manage football player options
 */
public class FootballPlayer implements Serializable{
    /**
     * The name of the player
     */
    protected String name;
    /**
     * The player's shirt number
     */
    protected String shirtNumber;
    /**
     * Defines if the option is correct
     */ 
    protected boolean correct; 

    /**
     * Constructor class for the FootballPlayer class
     * @param name The name of the player
     * @param shirtNumber The player's shirt number
     * @param correct Defines if the option is correct
     */
    public FootballPlayer(boolean correct, String name, String shirtNumber){
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.correct = correct; 
    }
}
