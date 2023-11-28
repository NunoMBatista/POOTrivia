import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;
import java.util.*;

public class Window extends JFrame{

    int gameStage = 0; 
    JPanel panel; 
    TriviaGame game = new TriviaGame(); 

    public Window(){
        this.setTitle("POO Trivia");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mainMenu(){
        clearFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Label
        JLabel title = new JLabel("POO Trivia", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        

        // Buttons
        Font buttonFont = new Font("Roboto", Font.BOLD, 20);
        Dimension buttonSize = new Dimension(200, 100); 
        
        JButton newGame = new JButton("New Game");
        newGame.setFont(buttonFont);
        newGame.setPreferredSize(buttonSize);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getPlayerName();
            }
        });

        JButton quit = new JButton("Quit"); 
        quit.setFont(buttonFont);
        quit.setPreferredSize(buttonSize);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            } 
        });


        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(newGame);
        buttonPanel.add(quit);
        
        mainPanel.add(title, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void getPlayerName(){
        clearFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Label
        JLabel label = new JLabel("Insert your name:");
        label.setFont(new Font("Roboto", Font.BOLD, 35));
        
        // Text field
        JTextField nameField = new JTextField(30);
        nameField.setPreferredSize(new Dimension(100, 50));

        // Button 
        JButton accept = new JButton("Accept");
        accept.setPreferredSize(new Dimension(80, 50));
        accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().isEmpty()){
                    game.setName("No Name");
                }
                else{
                    game.setName(nameField.getText());
                }
                Main.setupGame(game);
                System.out.println("Player name was set to: " + game.playerName);
                loadQuestion();
            }
        });
        // FlowLayout Panel
        JPanel secondayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        secondayPanel.add(label);
        secondayPanel.add(nameField);
        secondayPanel.add(accept);
        
        mainPanel.add(secondayPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void loadQuestion(){

        Question currQuestion = game.askedQuestions.get(gameStage);
        clearFrame();

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Label 
        JLabel questionText = new JLabel("<html><center> For " + currQuestion.getScoreValue() + " points!!<br>" + currQuestion.getText() + "</center></html>");
        questionText.setFont(new Font("Roboto", Font.BOLD, 30));
        
        // Buttons
        Dimension buttonDimension = new Dimension(200, 100);
        ArrayList<Option> optionArray = currQuestion.getOptionArray();
        int nOption = optionArray.size();
        for(int idx = 0; idx < nOption; idx++){
            Option opt = optionArray.get(idx);
            JButton optButton = new JButton("<html><center>" + opt.getOptionText() + "</center></html>");
            
            optButton.setFont(new Font("Helvetica", Font.BOLD, 20));
            optButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    game.updateCorrectIndices(gameStage, opt.getCorrect());
                    System.out.println("selected " + opt.getOptionText());
                    gameStage += 1;
                    if(gameStage < 5){
                        loadQuestion();
                    }
                    else{
                        endGame();
                    }
                }
            });
            optButton.setPreferredSize(buttonDimension);
            buttonPanel.add(optButton);
        }

        mainPanel.add(questionText, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void endGame(){
        clearFrame();
        ArrayList<TriviaGame> games = Main.readGames();
        games.add(game);
        Main.storeGame(game);
        ArrayList<TriviaGame> topGames = Main.getTop(games);
        Font namesFont = new Font("Roboto", Font.BOLD, 30);

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topScoresPanel = new JPanel(new GridLayout(3, 1));
        JPanel newGamePanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        // Labels
        for(int i = 0; i < topGames.size(); i++){
            TriviaGame curGame = topGames.get(i);
            JLabel gameLabel = new JLabel("<html><center>" + (i+1) + " -> " + curGame.getName() + " with score: " + curGame.calculateTotalScore() + "</center></html>");
            gameLabel.setFont(namesFont);
            topScoresPanel.add(gameLabel);
        }
        JLabel newGame = new JLabel("<html><center> You got " + game.calculateTotalScore() + " points!</center></html>");

        // Buttons 
        Font buttonFont = new Font("Roboto", Font.BOLD, 20);
        JButton playAgain = new JButton("Play Again");
        playAgain.setFont(buttonFont);
        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameStage = 0;
                mainMenu();
            }
        });
        JButton quit = new JButton("Quit");
        quit.setFont(buttonFont);
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

    private void clearFrame() {
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
    }

}
