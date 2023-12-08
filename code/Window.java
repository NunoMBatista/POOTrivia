/**
 * @author Nuno Batista 
 * @author Diogo Joaquim
 * @version 1.0
 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Class used to manage the game's graphical user interface
*/
public class Window extends JFrame{
    FileManager fileManager = new FileManager();
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
    public Window(){
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
        Dimension buttonSize = new Dimension(300, 100); 
        CustomButton newGame = new CustomButton("New Game", 30, buttonSize);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                fileManager.setupGame(game);
                loadQuestion();
            }
        });
        CustomButton quit = new CustomButton("Quit", 30, buttonSize);
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
        CustomLabel label = new CustomLabel("Insert your name:", 35);
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.setBackground(Color.BLACK);
        labelPanel.add(label);


        // Text field
        JTextField nameField = new JTextField(30);
        nameField.setPreferredSize(new Dimension(100, 50));
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(new Color(93,212,49));
        nameField.setFont(new CustomFontBold(20));

        // Button 
        CustomButton accept = new CustomButton("Accept", 25,new Dimension(200, 50));
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
        CustomLabel questionText = new CustomLabel("<html><center> For " + currQuestion.getScoreValue() + " points!!<br>" + currQuestion.getText() + "</center></html>", 30);

        // Buttons
        Dimension buttonSize = new Dimension(200, 100);
        ArrayList<Option> optionArray = currQuestion.getOptionArray();
        int nOption = optionArray.size();
        for(int idx = 0; idx < nOption; idx++){
            Option opt = optionArray.get(idx);
            CustomButton optButton = new CustomButton("<html><center>" + opt.getOptionText() + "</center></html>", 15, buttonSize);
            
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
        CustomLabel correctLabel = new CustomLabel("", 50);
        if(correct == true){
            correctLabel.setText("Correct");
            correctLabel.setForeground(Color.GREEN);
        }
        else{
            correctLabel.setText("INCORRECT");
            correctLabel.setForeground(Color.RED);
        }
        CustomLabel quickFactLabel = new CustomLabel("<html><p>Quick Fact: " + quickFact + "</p></html>", 30);
    
        // Button
        CustomButton nextButton = new CustomButton("NEXT", 30, new Dimension(20, 80));
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
        fileManager.storeGame(game);
        ArrayList<TriviaGame> games = fileManager.readGames();
        ArrayList<TriviaGame> topGames = fileManager.getTop(games);

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
            CustomLabel gameLabel = new CustomLabel("<html><center>" + (i+1) + " -> " + curGame.getName() + " with score: " + curGame.calculateTotalScore() + " [" + (curGame.getDateTime()).substring(0, 2) + "/" + (curGame.getDateTime()).substring(2, 4) + "/" + (curGame.getDateTime()).substring(4, 8) + " - " + (curGame.getDateTime()).substring(8, 10) + ":" + (curGame.getDateTime()).substring(10, 12) + "]</center></html>", 25);
            topScoresPanel.add(gameLabel);
        }
        JLabel newGame = new CustomLabel("<html><center> You got " + game.calculateTotalScore() + " points!</center></html>",25);

        // Buttons 
        Dimension buttonSize = new Dimension(300, 100);
        CustomButton playAgain = new CustomButton("Play Again", 25, buttonSize);
        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameStage = 0;
                mainMenu();
            }
        });
        CustomButton quit = new CustomButton("Quit", 25, buttonSize);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        buttonPanel.add(quit); 
        buttonPanel.add(playAgain);
        newGamePanel.add(newGame);

        mainPanel.add(newGamePanel, BorderLayout.NORTH);
        mainPanel.add(topScoresPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
        }

        /**
         * Method used to remove the content from the frame
         */
        private void clearFrame() {
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
    }


    /**
     * Class defining the font used throughout the game
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
    /**
     * Class defining the button costumizations used throughout the game 
     */
    private class CustomButton extends JButton{
        Color buttonColor = new Color (93, 212, 49);
        /**
         * Constructor for the CustomButton class
         * @param text The button's text
         * @param textSize the size of the button's text
         * @param buttonSize the dimension of the button's box
         */
        private CustomButton(String text, int textSize, Dimension buttonSize){
            super(text);
            this.setFont(new CustomFontBold(textSize));
            this.setForeground(buttonColor);
            this.setBackground(Color.BLACK);
            this.setPreferredSize(buttonSize);
            this.setFocusPainted(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            JButton button = this;
            this.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    button.setForeground(Color.GREEN);
                }
                public void mouseExited(MouseEvent me) {
                    button.setForeground(buttonColor);
                }
            });
        }
    }

    /**
     * Class defining the label costumizations used throughout the game
     */
    private class CustomLabel extends JLabel{
        private CustomLabel(String text, int size){
            super(text);
            this.setFont(new CustomFontBold(size));
            this.setForeground(new Color(93, 212, 49));
            this.setBackground(Color.BLACK);
        }
    }    

    public static void main(String args[]){
        Window window = new Window();
        window.mainMenu();
    }
}
  