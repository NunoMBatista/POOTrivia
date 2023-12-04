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
     * Method used to get the option's text
     * @return the option's text
     */
    public String getOptionText(){
        return this.optionText;
    }

    /**
     * Method used to get the option's boolean value 
     * @return the option's boolean value
     */
    public boolean getCorrect(){
        return this.correct;
    }

    /**
     * Method used to set the option's text
     * @param optionText the option's text
     */
    public void setOptionText(String optionText){
        this.optionText = optionText;
    }

    /**
     * Method used to set the option's boolean value
     * @param correct the option's boolean value
     */
    public void setCorrect(boolean correct){
        this.correct = correct; 
    }

    /**
     * Method used to print the option's text
     * @return out A string with the option's text and marked if correct
     */
    public String toString(){
        String out = this.optionText;
        return out;
    }

}
