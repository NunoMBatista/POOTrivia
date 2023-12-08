/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class used to manage the game's graphical user interface
*/
public class Window2 extends JFrame{
/**
 * Defines the current stage of the game
*/
int gameStage = 0; 
/**
 * Contains the panel shown to the user 
*/
JPanel panel;
/**
 * Contains information about the current game beeing played
*/
TriviaGame game = new TriviaGame(); 

/**
 * Constructor class for the Window class
*/
public Window2(){
    this.setTitle("POO Trivia");
    this.setSize(800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

/**
 * Method used to display the main menu on the GUI
*/
public void mainMenu(){
    JPanel mainPanel = new JPanel(new BorderLayout());
    clearFrame();
    // Image Panel
    try{
        BufferedImage logo = ImageIO.read(new File("gamefiles" + File.separator + "logo.png"));
        JPanel imagePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(logo, 150, 150, 512, 121, rootPane);
            }
        };
        imagePanel.setBackground(Color.BLACK);
        mainPanel.add(imagePanel, BorderLayout.CENTER);

    }
    catch(IOException e){
        System.out.println("Couldn't read the game's logo");
    }        

    // Buttons
    Font font = new CustomFontBold(30);
    Dimension buttonSize = new Dimension(300, 100); 
    
    JButton newGame=setupButton("New Game", buttonSize,  font);
    newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setupGame(game);
                loadQuestion();
            }
        });

        JButton quit=setupButton("Quit", buttonSize,  font);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            } 
        });

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(quit);
        buttonPanel.add(newGame);
        buttonPanel.setBackground(Color.BLACK);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setForeground(Color.BLACK);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Method used to present to the player a screen asking for their name
    */
    public void getPlayerName(){
        clearFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Label
        JLabel label = setupLabel("Insert your name:", new CustomFontBold(35));
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.setBackground(Color.BLACK);
        labelPanel.add(label);


        // Text field
        JTextField nameField = new JTextField(30);
        nameField.setPreferredSize(new Dimension(100, 50));
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(new Color(93,212,49));

        // Button 
        JButton accept=setupButton("Accept", new Dimension(200, 50),  new CustomFontBold(25));
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().isEmpty()){
                    game.setName("No Name");
                }
                else{
                    game.setName(nameField.getText());
                }
                endGame();
            }
        });

        // FlowLayout Panel
        JPanel secondayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        secondayPanel.setBackground(Color.BLACK);
        secondayPanel.add(nameField);
        secondayPanel.add(accept);
        
        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(secondayPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }
    
    /**
     * Method used to display the current question being asked as well as it's options
    */
    public void loadQuestion(){
        Question currQuestion = game.askedQuestions.get(gameStage);
        if(gameStage < 2){
            currQuestion.setEasyMode(3);    
        }

        clearFrame();

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);

        // Label 
        JLabel questionText = setupLabel("<html><center> For " + currQuestion.getScoreValue() + " points!!<br>" + currQuestion.getText() + "</center></html>", new CustomFontBold(30));
        
        // Buttons
        Dimension buttonDimension = new Dimension(200, 100);
        ArrayList<Option> optionArray = currQuestion.getOptionArray();
        int nOption = optionArray.size();
        for(int idx = 0; idx < nOption; idx++){
            Option opt = optionArray.get(idx);
            JButton optButton = setupButton("<html><center>" + opt.getOptionText() + "</center></html>", buttonDimension, new CustomFontBold(20));
            
            optButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    game.updateCorrectIndices(gameStage, opt.getCorrect());
                    afterQuestion(opt.correct, currQuestion.getQuickFact());
                }
            });
            buttonPanel.add(optButton);
        }

        mainPanel.add(questionText, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Method used to present a screen informing the player if the option they chose is the correct one
    * as well as a quick fact about the question itself
    * @param correct Defines if the question was answered correcly
    * @param quickFact Contains a quick fact about the question
    */
    private void afterQuestion(boolean correct, String quickFact){
        gameStage += 1; 
        clearFrame();

        // Labels
        JLabel correctLabel= new JLabel();
        if(correct == true){
            correctLabel = setupLabel("CORRECT!", new CustomFontBold(50));
            correctLabel.setForeground(new Color(93,212,49));
        }
        else{
            correctLabel = setupLabel("INCORRECT!", new CustomFontBold(50));
            correctLabel.setForeground(Color.RED);
        }
        JLabel quickFactLabel = setupLabel("<html><p>Quick Fact: " + quickFact + "</p></html>", new CustomFontBold(30));
        quickFactLabel.setPreferredSize(new Dimension(100, 100));
    
        // Button
        JButton nextButton = setupButton("NEXT", new Dimension(20, 80), new CustomFontBold(30));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(gameStage < 5){

                    loadQuestion();
                }
                else{
                    getPlayerName();
                } 
            }
        });

        // Panel 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(correctLabel, BorderLayout.NORTH);
        mainPanel.add(nextButton, BorderLayout.SOUTH);
        mainPanel.add(quickFactLabel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Method used to present to the player their score as well as the 3 best scores of the games previously played
     */
    private void endGame(){
        clearFrame();
        storeGame(game);
        ArrayList<TriviaGame> games = readGames();
        for(TriviaGame game: games){
            System.out.println(game.getName());
        }
        ArrayList<TriviaGame> topGames = getTop(games);
        for(TriviaGame topgame: topGames){
            System.out.println(topgame.getName());
        }

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topScoresPanel = new JPanel(new GridLayout(3, 1));
        topScoresPanel.setBackground(Color.BLACK);
        JPanel newGamePanel = new JPanel(new FlowLayout());
        newGamePanel.setBackground(Color.BLACK);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        // Labels
        for(int i = 0; i < topGames.size(); i++){
            TriviaGame curGame = topGames.get(i);
            JLabel gameLabel = setupLabel("<html><center>" + (i+1) + " -> " + curGame.getName() + " with score: " + curGame.calculateTotalScore() + " [" + (curGame.getDateTime()).substring(0, 2) + "/" + (curGame.getDateTime()).substring(2, 4) + "/" + (curGame.getDateTime()).substring(4, 8) + " - " + (curGame.getDateTime()).substring(8, 10) + ":" + (curGame.getDateTime()).substring(10, 12) + "]</center></html>", new CustomFontBold(25));
            topScoresPanel.add(gameLabel);
        }
        JLabel newGame = setupLabel("<html><center> You got " + game.calculateTotalScore() + " points!</center></html>", new CustomFontBold(25));

        // Buttons 
        JButton playAgain=setupButton("Play Again", new CustomFontBold(30));
        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameStage = 0;
                mainMenu();
            }
        });
        JButton quit=setupButton("Quit", new CustomFontBold(30));
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });


        buttonPanel.add(playAgain);
        buttonPanel.add(quit); 
        newGamePanel.add(newGame);
        
        mainPanel.add(newGamePanel, BorderLayout.NORTH);
        mainPanel.add(topScoresPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Method remove the content from the frame
     */
    private void clearFrame() {
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
    }

    /**
     * Method used to read options from the question's section in the questions file between two indices
     * @param startingIdx the index in which to start reading
     * @param endingIdx the index in which to stop reading
     * @param optionStr the option string array from which to extract the options
     * @return optionArray the new option array
     */
    private ArrayList<Option> readOptions (int startingIdx, int endingIdx, String[] optionStr){
        ArrayList<Option> optionArray = new ArrayList<>();
        boolean correct = true; // The first option is always correct
        for(int i = startingIdx; i < endingIdx; i++){
            String optionText = optionStr[i]; // Read the option text 
            if(i > startingIdx){
                correct = false;
            }
            Option newOption = new Option(correct, optionText); // Initialize new option
            optionArray.add(newOption); // Add the option to the array       
        }
        return optionArray;
    }

    /**
     * Method used to read an arts question from the question's file and initialize a new ArtsQuestion object accordingly
     * @param questionText The question's text
     * @param str The question's score, options and the quick fact
     * @return a new ArtsQuestion object
     */
    private Question artsParsing(String questionText, String str){
        String[] optionStr = str.split(";");

        int score = Integer.parseInt(optionStr[0]); // Read the score value 

        ArrayList<Option> optionArray = readOptions(1, 7, optionStr); // Initialize the options array 

        String quickFact = optionStr[7];
        return new ArtsQuestion(score, questionText, optionArray, quickFact);
    }

    /**
     * Method used to read a science question from the question's file and initialize a new ScienceQuestion object accordingly
     * @param questionText The question's text
     * @param str The question's score, regular options, easy options and the quick fact
     * @return a new ScienceQuestion object
     */
    private Question scienceParsing(String questionText, String str){
        String[] optionStr = str.split(";"); 
        
        int score = Integer.parseInt(optionStr[0]); // Read the score value
        
        
        ArrayList<Option> normalOptionArray = readOptions(1, 6, optionStr); // Initialize the normal options array
        ArrayList<Option> easyOptionArray = readOptions(6, 11, optionStr); // Initialize the easy options array

        String quickFact = optionStr[11];
        return new ScienceQuestion(score, questionText, normalOptionArray, easyOptionArray, quickFact);
    }

    /**
     * Method used to read a football question from the question's file and initializes a new FootballQuestion object accordingly
     * @param questionText The question's text
     * @param str The question's score, football player's shirt numbers and names and the quick fact
     * @return a new FootballQuestion object
     */
    private Question footballParsing(String questionText, String str){
        ArrayList<FootballPlayer> playerArray = new ArrayList<>();
        String[] playerStr = str.split(";");

        int score = Integer.parseInt(playerStr[0]);
        boolean correct = true;

        for(int i = 1; i < 6; i++){
            String[] playerSplit = playerStr[i].split("-");
            System.out.println(playerSplit[0]);
            if(i > 1){
                correct = false; 
            }
            FootballPlayer newPlayer = new FootballPlayer(correct, playerSplit[0], playerSplit[1]);
            playerArray.add(newPlayer);
        }

        String quickFact = playerStr[6];
        return new FootballQuestion(score, questionText, new ArrayList<>(), playerArray, quickFact);
    }

    /**
     * Method used to read a question with boolean values as options from the questions file i.e. ski and swimming and initializes a new Question object accordingly
     * @param questionText The question's text
     * @param str The question's score, boolean value and the quick fact
     * @param category The question's category 
     * @return a new SkiQuestion or SwimmingQuestion Object
     */
    private Question booleanParsing(String questionText, String str, char category){
        ArrayList<Option> optionArray = new ArrayList<>(); 
        String[] optionStr = str.split(";");
        boolean correct = false;
        int score = Integer.parseInt(optionStr[0]);

        if(optionStr[1].equals("true")){ // Reads the question's boolean value
            correct = true;
        }

        // The True option boolean value is always the complement of the False option's
        optionArray.add(new Option(correct, "True")); 
        optionArray.add(new Option(!correct, "False"));

        String quickFact = optionStr[2];

        // If the category is marked as two, it returns a new SkiQuestion object else, returns a new SwimmingQuestion object
        if(category == '2'){
            return new SkiQuestion(score, questionText, optionArray, quickFact);
        }
        return new SwimmingQuestion(score, questionText, optionArray, quickFact);
    }

    /**
     * Method used at the end of a game to store the game's data
     * @param game the game 
     */
    private void storeGame(TriviaGame game){
        String fileName = game.constructObjectFileName(); // Gets the name of the file in which the data will be stored
        File objFile = new File("gamefiles" + File.separator + fileName); // Creates new file to store the TriviaGame object
        File gamesFile = new File("gamefiles" + File.separator + "gamesplayed.txt"); // Opens the file that keeps track of the game files so far
        try{
            FileOutputStream fos = new FileOutputStream(objFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("aaaa");
            oos.writeObject(game); // Writes the TriviaGame in the new file
            System.out.println("bbbb");
            oos.close(); 

            FileWriter fw = new FileWriter(gamesFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + fileName); // Apends the name of the new TriviaGame file to the text file that keeps track of the games played so far
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

    /**
     * Method used to read the data from the previously played games
     * @return a new ArrayList containing every game played so far
     */
    private ArrayList<TriviaGame> readGames(){
        ArrayList<TriviaGame> games = new ArrayList<>(); 
        File gamesFile = new File("gamefiles" + File.separator + "gamesplayed.txt"); // Opens the text file containing the name of every TriviaGame file
    
        try{
            FileReader fr = new FileReader(gamesFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Skips empty row
            
            line = br.readLine();
            while(line != null){
                File fileName = new File("gamefiles" + File.separator + line); // Gets the name of every TriviaGame file
                FileInputStream fis = new FileInputStream(fileName); 
                ObjectInputStream ois = new ObjectInputStream(fis);

                TriviaGame game = (TriviaGame) ois.readObject(); // Reads the current game and stores it in a variable
                games.add(game); // Adds the object just read to the games array 
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

    /**
     * Method used to determine the 3 players with the best scores so far
     * @param games An ArrayList containing every game played so far
     * @return an ArrayList with the 3 best games so far
     */
    private ArrayList<TriviaGame> getTop(ArrayList<TriviaGame> games){
        int nGames = games.size(); // Number of games played
        int[] scoreIndices = new int[nGames + 1]; // Paralel int array keeping track of the scores from the game i
        
        scoreIndices[nGames] = -1; // The last is a dummy element so that the first, second and third indices start at a negative value
        // Indices of the best, second best and third best games (they start at score -1)
        int first = nGames, second = nGames, third = nGames;

        // Iterates through every game
        for(int i = 0; i < nGames; i++){
            // Stores the score of the game i in the scoreIndices array
            scoreIndices[i] = (games.get(i)).calculateTotalScore();

            // If game i's score is larger or equal than the score of the best game so far, pushes every game on the top 3 one step down
            if (scoreIndices[i] >= scoreIndices[first]){
                third = second;
                second = first; 
                first = i;
            }

            // Else if game i's score is larger or equal than the score of the second best game so far, pushes the games on the spots 2 and 3 one step down
            else if(scoreIndices[i] >= scoreIndices[second]){
                third = second;
                second = i;
            }

            // Else if game i's score is larger or equal than the score of the third best game so far, pushes the third best game one spot down
            else if(scoreIndices[i] >= scoreIndices[third]){
                third = i;
            }
        } 
        

        ArrayList<TriviaGame> topGames = new ArrayList<>();
        // Iterates through the top 3 indices (if the indice is equal to nGames, it means that the indice wasn't set)
        for(int i: new int[] {first, second, third}){
            // If the indice was set, add it to the top games ArrayList
            if(i != nGames){
                topGames.add(games.get(i));
            }
        }

        return topGames;
    }

    /**
     * Method used to setup the current game, setting it's asked questions, the player's name, the current date/time
     * @param game the TriviaGame object where the game's information will be stored
     */
    private void setupGame(TriviaGame game){
        // Load the questions from pootrivia.txt to questionArray
        File questionsFile = new File("gamefiles" + File.separator + "pootrivia.txt");
        ArrayList<Question> questionArray = new ArrayList<>();

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
                // Selects the parsing function to use based on the special character denoting the question's category
                if(categoryStr.charAt(0) == '&'){
                    questionArray.add(artsParsing("Arts: " + questionText, questionDetails));
                }

                if(categoryStr.charAt(0) == '$'){
                    questionArray.add(scienceParsing("Science: " + questionText, questionDetails));
                }
                
                if(categoryStr.charAt(0) == '@'){
                    /*
                    * Reads sub-category type.
                    * 1 - Football
                    * 2 - Ski
                    * 3 - Swimming
                    */
                    if(categoryStr.charAt(1) == '1'){
                        questionArray.add(footballParsing("Sports/Football: " + questionText, questionDetails));
                    }
                    if(categoryStr.charAt(1) == '2'){
                        questionArray.add(booleanParsing("Sports/Ski: " + questionText, questionDetails, '2'));
                    }
                    if(categoryStr.charAt(1) == '3'){
                        questionArray.add(booleanParsing("Sports/Swimming: " + questionText, questionDetails, '3'));
                    }                   
                }
                /*
                * Checks if EOF has been reached
                * If line == null, the reading process ceases
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
        /*
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

        // Get the local time and date and format it
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
        String formattedDateTime = currentDateTime.format(formatter);
        
        // Set the game's date/time and the questions to be asked
        game.setDateTime(formattedDateTime);
        game.setAskedQuestions(askedQuestions);
        game.setCorrectIndices(correctIndices);
    }        

    /**
     * Class defining the font used throughout the game (with bold)
    */
    private class CustomFontBold extends Font{
        /**
         * Constructor for the class CustomFont
        * @param size Contains the font's size
        */
        private CustomFontBold(int size){
            super(loadFont(size));
        }
        
        private static Font loadFont(int size){
            try{
                Font base = Font.createFont(Font.TRUETYPE_FONT, new File("gamefiles" + File.separator + "ibmbiosfont.ttf"));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(base);
                return base.deriveFont(Font.BOLD, size);
            }
            catch(IOException | FontFormatException e){
                    System.out.println("Couldn't find font file");
                    e.printStackTrace();
            }
            return new Font("Serif", Font.PLAIN, size); 
        }
    }

private JButton setupButton(String text, Dimension buttonSize, Font font){
    JButton button = new JButton(text);
    button.setText(text);
    button.setFont(font);
    button.setForeground(new Color(93,212,49));
    button.setBackground(Color.BLACK);
    button.setPreferredSize(buttonSize);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    return button;
}

private JButton setupButton(String text, Font font){
    JButton button = new JButton(text);
    button.setText(text);
    button.setFont(font);
    button.setForeground(Color.GREEN);
    button.setBackground(Color.BLACK);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    return button;
}

private JLabel setupLabel(String text, Font font){
    JLabel label = new JLabel(text);
    label.setFont(font);
    label.setForeground(new Color(93,212,49));
    label.setBackground(Color.BLACK);
    return label;
}

/**
private class CustomButton extends JButton{
    public CustomButton(String text, Dimension buttonSize, Font font){
        super(CustomButton(text));
    }

    private static JButton CustomButton(String text){
    JButton button = new JButton(text);
    button.setPreferredSize(buttonSize);
    button.setFont(font);
    button.setForeground(Color.GREEN);
    button.setBackground(Color.BLACK);
    button.setPreferredSize(buttonSize);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    return button;
    }
}
*/
    

    public static void main(String args[]){
        Window2 window = new Window2();
        window.mainMenu();
    }

}
  