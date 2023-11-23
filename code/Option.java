/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

/**
 * Class used to manage the question's options 
 */
public class Option {
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
}
