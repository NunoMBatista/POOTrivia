/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.io.Serializable;

/**
 * Class used to manage the question's options 
 */
public class Option implements Serializable{
    /**
     * Defines if the option is correct
     */
    protected boolean correct; 
    /**
     * Contains the option's text
     */
    protected String optionText;

    /**
     * Constuctor for the Option class
     * @param correct Defines if the option is correct
     * @param optionText Contains the option's text
     */
    public Option(boolean correct, String optionText){
        this.correct = correct; 
        this.optionText = optionText; 
    } 
    /**
     * Constructor for the (empty) Option class
     */
    public Option(){};

    /**
     * Method used to print the option's text
     * @return out A string with the option's text and marked if correct
     */
    public String toString(){
        String out = this.optionText;
        return out;
    }

    public String getOptionText(){
        return this.optionText;
    }

    public boolean getCorrect(){
        return this.correct;
    }

}
