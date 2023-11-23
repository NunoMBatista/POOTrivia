/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;

/**
 * Class to manage the game
 */
public class Main {
    public static void main(String args[]){
        Option option1 = new Option(true, "option1text");
        Option option2 = new Option(false, "option2text");
        Option option3 = new Option(true, "option3text");
        Option option4 = new Option(false, "option4text");
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        optionList.add(option4);

        for(Option i: optionList){
            System.out.println(i.correct);
        }
    }
}
