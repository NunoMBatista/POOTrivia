/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

/**
 * Class used to manage football player options
 */
public class FootballPlayer extends Option{
    /**
     * The name of the player
     */
    protected String name;
    /**
     * The player's shirt number
     */
    protected String shirtNumber;

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

    /**
     * Method used to get the football player's name
     * @return the football player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method used to get the football player's shirt number
     * @return the football player's shirt number
     */
    public String getNumber(){
        return this.shirtNumber;
    }

    /**
     * Method used to set the football player's name
     * @param name the football player's name
     */    
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method used to set the football player's shirt number
     * @param number the football player's shirt number
     */
    public void setNumber(String number){
        this.shirtNumber = number;
    }

}
