/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Class to manage the game
 */
public class Testador {
    public static void main(String args[]){
        Option option1 = new Option(true, "option1text");
        Option option2 = new Option(false, "option2text");
        Option option3 = new Option(false, "option3text");
        Option option4 = new Option(false, "option4text");
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        optionList.add(option4);
        String texto = "bazinga ou não?";
        ArtsQuestion quest = new ArtsQuestion(5, texto, optionList, 3);

        System.out.println(quest);
        System.out.println("\n");
        String texto2 = "Quem marca um golo?";
        FootballPlayer sebio = new FootballPlayer("HumSébio", "4", true);
        FootballPlayer sebio2 = new FootballPlayer("DoiSébio", "5", true);
        FootballPlayer sebio3 = new FootballPlayer("TreSébio", "23", false);
        FootballPlayer sebio4 = new FootballPlayer("QuatroSébio", "8", false);
        ArrayList<FootballPlayer> playerList = new ArrayList<>();
        playerList.add(sebio);
        playerList.add(sebio2);
        playerList.add(sebio3);
        playerList.add(sebio4);

        FootballQuestion festion = new FootballQuestion(5, texto2, new ArrayList<>(), true, playerList);
        System.out.println(festion);
        System.out.println("\n");
        
        Option Hoption1 = new Option(true, "hard1text");
        Option Hoption2 = new Option(false, "hard2text");
        Option Hoption3 = new Option(false, "hard3text");
        Option Hoption4 = new Option(false, "hard4text");
        ArrayList<Option> HardList = new ArrayList<>();
        HardList.add(Hoption1);
        HardList.add(Hoption2);
        HardList.add(Hoption3);
        HardList.add(Hoption4);

        ScienceQuestion sestion = new ScienceQuestion(5, texto, HardList, optionList, false);
        System.out.println(sestion);
        String [] a = new String[0];
        GUI.main(a);
    }
}
