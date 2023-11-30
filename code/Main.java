/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import java.time.*;
import java.time.format.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Class to manage the game
 */
public class Main{

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

        String quickFact = optionStr[7];
        return new ArtsQuestion(score, questionText, optionArray, quickFact);
    }

    private static Question scienceParsing(String questionText, String str){
        ArrayList<Option> normalOptionArray = new ArrayList<>(); // Initialize the normal option array
        ArrayList<Option> easyOptionArray = new ArrayList<>(); // Initialize the easy option array
        String[] optionStr = str.split(";"); 

        int score = Integer.parseInt(optionStr[0]); // Read the score value
        
        /**
        * Read the normal options
        */
        boolean correct = true; // The first normal option is always correct
        for(int i = 1; i < 5; i++){
            String optionText = optionStr[i];
            if(i > 1){
                correct = false;
            }
            Option newOption = new Option(correct, optionText); 
            normalOptionArray.add(newOption);
        }
        /**
         * Read the easy options
         */
        correct = true;
        for(int i = 5; i < 9; i++){
            String easyOptionText = optionStr[i];
            if(i > 5){
                correct = false;
            }
            Option newOption = new Option(correct, easyOptionText);
            easyOptionArray.add(newOption);
        }

        String quickFact = optionStr[9];
        return new ScienceQuestion(score, questionText, normalOptionArray, easyOptionArray, quickFact);
    }

    private static Question footballParsing(String questionText, String str){
        ArrayList<FootballPlayer> playerArray = new ArrayList<>();
        String[] playerStr = str.split(";");

        int score = Integer.parseInt(playerStr[0]);
        boolean correct = true;

        for(int i = 1; i < 5; i++){
            String[] playerSplit = playerStr[i].split("-");
            if(i > 1){
                correct = false; 
            }
            FootballPlayer newPlayer = new FootballPlayer(correct, playerSplit[0], playerSplit[1]);
            playerArray.add(newPlayer);
        }

        String quickFact = playerStr[5];
        return new FootballQuestion(score, questionText, new ArrayList<>(), playerArray, quickFact);
    }

    private static Question skiParsing(String questionText, String str){
        ArrayList<Option> optionArray = new ArrayList<>(); 
        String[] optionStr = str.split(";");
        boolean correct = false;
        int score = Integer.parseInt(optionStr[0]);
        if(optionStr[1].equals("true")){
            correct = true;
        }
        optionArray.add(new Option(correct, "True"));
        optionArray.add(new Option(!correct, "False"));

        String quickFact = optionStr[2];
        return new SkiQuestion(score, questionText, optionArray, quickFact);
    }

    private static Question swimmingParsing(String questionText, String str){
        ArrayList<Option> optionArray = new ArrayList<>(); 
        String[] optionStr = str.split(";");
        boolean correct = false;
        int score = Integer.parseInt(optionStr[0]);
        if(optionStr[1].equals("true")){
            correct = true;
        }
        optionArray.add(new Option(correct, "True"));
        optionArray.add(new Option(!correct, "False"));

        String quickFact = optionStr[2];
        return new SwimmingQuestion(score, questionText, optionArray, quickFact);
    }

    public static void storeGame(TriviaGame game){
        String fileName = game.constructObjectFileName();
        File objFile = new File("gamefiles/" + fileName);
        File gamesFile = new File("gamefiles/gamesplayed.txt");
        try{
            FileOutputStream fos = new FileOutputStream(objFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();

            FileWriter fw = new FileWriter(gamesFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + fileName);
            bw.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: file not created");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error: data not written");
        }
    }

    public static ArrayList<TriviaGame> readGames(){
        ArrayList<TriviaGame> games = new ArrayList<>();
        File gamesFile = new File("gamefiles/gamesplayed.txt");
    
        try{
            FileReader fr = new FileReader(gamesFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Skips empty row
            line = br.readLine();
            while(line != null){
                File fileName = new File("gamefiles/" + line);
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);

                TriviaGame game = (TriviaGame) ois.readObject();
                games.add(game);
                line = br.readLine();
                ois.close();
            }
            br.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: file not found");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error: Input/Output problem");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: can't convert object");
        }

        return games;
    }

    public static ArrayList<TriviaGame> getTop(ArrayList<TriviaGame> games){
        int nGames = games.size();
        int[] scoreIndices = new int[nGames + 1];
        scoreIndices[nGames] = -1;
        int first = nGames, second = nGames, third = nGames;
        for(int i = 0; i < nGames; i++){
            scoreIndices[i] = (games.get(i)).calculateTotalScore();
            if (scoreIndices[i] >= scoreIndices[first]){
                third = second;
                second = first; 
                first = i;
            }
            else if(scoreIndices[i] < scoreIndices[first] && scoreIndices[i] >= scoreIndices[second]){
                third = second;
                second = i;
            }
            else if(scoreIndices[i] < scoreIndices[second] && scoreIndices[i] >= scoreIndices[third]){
                third = i;
            }
        } 
        
        ArrayList<TriviaGame> topGames = new ArrayList<>();
        topGames.add(games.get(first));
        if(scoreIndices.length == 2){
            return topGames;
        }
        topGames.add(games.get(second));
        if(scoreIndices.length == 3){
            return topGames;
        }
        topGames.add(games.get(third));
        return topGames;
    }

    /**
     * Method used to setup the current game, setting it's asked questions, the player's name, the current date/time
     * @param game
     */
    public static void setupGame(TriviaGame game){
        /**
         * Load the questions from pootrivia.txt to questionArray
         */
        File questionsFile = new File("gamefiles/pootrivia.txt");
        ArrayList<Question> questionArray = new ArrayList<>();

        try{
            FileReader fr = new FileReader(questionsFile);
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            while(line != null){
                String questionText = br.readLine();; //Reads a question's text
                /**
                    * Reads the category type.
                    * & - Arts
                    * $ - Science 
                    * @ - Sports
                    */
                String categoryStr = br.readLine(); 
                String questionDetails = br.readLine();

                if(categoryStr.charAt(0) == '&'){
                    questionArray.add(artsParsing("Arts: " + questionText, questionDetails));
                }

                if(categoryStr.charAt(0) == '$'){
                    questionArray.add(scienceParsing("Science: " + questionText, questionDetails));
                }

                if(categoryStr.charAt(0) == '@'){
                    /**
                        * Reads sub-category type.
                        * 1 - Football
                        * 2 - Ski
                        * 3 - Swimming
                        */
                    if(categoryStr.charAt(1) == '1'){
                        questionArray.add(footballParsing("Sports/Football: " + questionText, questionDetails));
                    }
                    if(categoryStr.charAt(1) == '2'){
                        questionArray.add(skiParsing("Sports/Ski: " + questionText, questionDetails));
                    }
                    if(categoryStr.charAt(1) == '3'){
                        questionArray.add(swimmingParsing("Sports/Swimming: " + questionText, questionDetails));
                    }                   
                }
                /**
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
            e.printStackTrace();
            System.out.println("Error: Input/Output problem");
            return;
        }
    

        ArrayList<Question> askedQuestions = new ArrayList<>();
        /**
         * Shuffle the array containing every question and take the first 5 into askedQuestions.
         * The askedQuestions array list will be used in the TriviaGame object.
         */
        Collections.shuffle(questionArray);
        for(int i = 0; i < 5; i++){
            Question selectedQuestion = questionArray.get(i);
            selectedQuestion.shuffleOptions();
            askedQuestions.add(selectedQuestion);
        }

        game.setAskedQuestions(askedQuestions);
        boolean[] correctIndices = new boolean[5];  

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
        String formattedDateTime = currentDateTime.format(formatter);
        game.setDateTime(formattedDateTime);
        game.setAskedQuestions(askedQuestions);
        game.setCorrectIndices(correctIndices);
    }        

    public static void main(String args[]){
        Window window = new Window();
        window.mainMenu();
    }
}
