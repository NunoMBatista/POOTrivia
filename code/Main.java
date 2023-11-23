/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Class to manage the game
 */
public class Main {

    private static Question artsParsing(String questionText, String str){
        ArrayList<Option> optionArray = new ArrayList<>(); // Initialize the option array
        String[] optionStr = str.split(";");

        int score = Integer.parseInt(optionStr[0]); // Read the score value 
        boolean correct = true; // The first option is always correct

        for(int i = 1; i < 7; i++){
            String optionText = optionStr[i]; // Read the option text 
            if(i > 1){
                correct = false;
            }
            Option newOption = new Option(correct, optionText);
            optionArray.add(newOption);
        }

        return new ArtsQuestion(score, questionText, optionArray);
    }

    private static Question scienceParsing(String questionText, String str){
        ArrayList<Option> normalOptionArray = new ArrayList<>(); // Initialize the normal option array
        ArrayList<Option> easyOptionArray = new ArrayList<>(); // Initialize the easy option array
        String[] optionStr = str.split(";"); 

        int score = Integer.parseInt(optionStr[0]); // Read the score value
        boolean correct = true; // The first normal option is always correct

        /*
         * Read the normal options
         */
        for(int i = 1; i < 7; i++){
            String optionText = optionStr[i];
            if(i > 1){
                correct = false;
            }
            Option newOption = new Option(correct, optionText); 
            normalOptionArray.add(newOption);
        }
        /*
         * Read the easy options
         */
        correct = true;
        for(int i = 7; i < 13; i++){
            String easyOptionText = optionStr[i];
            if(i > 7){
                correct = false;
            }
            Option newOption = new Option(correct, easyOptionText);
            easyOptionArray.add(newOption);
        }

        return new ScienceQuestion(score, questionText, normalOptionArray, easyOptionArray);
    }

    private static Question footballParsing(String questionText, String str){
        ArrayList<FootballPlayer> playerArray = new ArrayList<>();
        String[] playerStr = str.split(";");

        int score = Integer.parseInt(playerStr[0]);
        boolean correct = true;

        for(int i = 1; i < 4; i++){
            String[] playerSplit = playerStr[i].split("-");
            if(i > 1){
                correct = false; 
            }
            FootballPlayer newPlayer = new FootballPlayer(correct, playerSplit[0], playerSplit[1]);
            playerArray.add(newPlayer);
        }
        return new FootballQuestion(score, questionText, new ArrayList<>(), playerArray);
    }

    private static Question skiParsing(String questionText, String str){
        ArrayList<Option> optionArray = new ArrayList<>(); 
        String[] optionStr = str.split(";");
        boolean correct = false;
        int score = Integer.parseInt(optionStr[0]);
        System.out.println(optionStr[1]);
        if(optionStr[1].equals("true")){
            correct = true;
        }
        optionArray.add(new Option(correct, "True"));
        optionArray.add(new Option(!correct, "False"));

        return new SkiQuestion(score, questionText, optionArray);
    }

    private static Question swimmingParsing(String questionText, String str){
        ArrayList<Option> optionArray = new ArrayList<>(); 
        String[] optionStr = str.split(";");
        boolean correct = false;
        int score = Integer.parseInt(optionStr[0]);
        System.out.println(optionStr[1]);
        if(optionStr[1].equals("true")){
            correct = true;
        }
        optionArray.add(new Option(correct, "True"));
        optionArray.add(new Option(!correct, "False"));

        return new SwimmingQuestion(score, questionText, optionArray);
    }

    public static void main(String args[]){
        File questionsFile = new File("pootrivia.txt");
        ArrayList<Question> questionArray = new ArrayList<>();

        if(questionsFile.exists()){
            try{
                FileReader fr = new FileReader(questionsFile);
                BufferedReader br = new BufferedReader(fr);
                
                String line = "";
                while(line != null){
                    String questionText = br.readLine();; //Reads a question's text
                    /*
                     * Reads the category type.
                     * & - Arts
                     * $ - Science 
                     * @ - Sports
                     */
                    String categoryStr = br.readLine(); 
                    String questionDetails = br.readLine();

                    if(categoryStr.charAt(0) == '&'){
                        questionArray.add(artsParsing(questionText, questionDetails));
                    }
 
                    if(categoryStr.charAt(0) == '$'){
                        
                        questionArray.add(scienceParsing(questionText, questionDetails));
                    }

                    if(categoryStr.charAt(0) == '@'){
                        System.out.println("a");
                        if(categoryStr.charAt(1) == '1'){
                        System.out.println("b");
                            questionArray.add(footballParsing(questionText, questionDetails));
                        }
                        if(categoryStr.charAt(1) == '2'){
                            questionArray.add(skiParsing(questionText, questionDetails));
                        }
                        if(categoryStr.charAt(1) == '3'){
                            questionArray.add(swimmingParsing(questionText, questionDetails));
                        }                   
                    }

                    /*
                     *  Checks if EOF has been reached
                     *  If line == null, the reading process ceases
                     */
                    line = br.readLine();
                }
                br.close();
            }
            catch(FileNotFoundException e){
                System.out.println("Error: questions file is missing from the folder");
                return;
            }
            catch(IOException e){
                System.out.println("Error: Input/Output problem");
                return;
            }
        }
        else{
            System.out.println("Error: questions file is missing from the folder");
            return;
        }

        for(Question quest: questionArray){
            quest.setEasyMode(3);
            System.out.println(quest+"\n\n");
        }

    }

}
