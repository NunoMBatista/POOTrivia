import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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
            mainPanel.add(imagePanel, BorderLayout.CENTER);

        }
        catch(IOException e){
            System.out.println("Couldn't read the game's logo");
        }        

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
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);


        // Text field
        JTextField nameField = new JTextField(30);
        nameField.setPreferredSize(new Dimension(100, 50));

        // Button 
        JButton accept = new JButton("Accept");
        accept.setPreferredSize(new Dimension(120, 50));
        accept.addActionListener(new ActionListener() {
            @Override
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
        secondayPanel.add(nameField);
        secondayPanel.add(accept);
        
        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(secondayPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }
    
    public void loadQuestion(){
        Question currQuestion = game.askedQuestions.get(gameStage);
        if(gameStage < 2){
            currQuestion.setEasyMode(3);    
        }

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
                    afterQuestion(opt.correct, currQuestion.getQuickFact());
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

    private void afterQuestion(boolean correct, String quickFact){
        gameStage += 1; 
        clearFrame();

        // Labels
        JLabel correctLabel = new JLabel();
        if(correct == true){
            correctLabel.setText("CORRECT!");
        }
        else{
            correctLabel.setText("INCORRECT!");
        }
        correctLabel.setFont(new Font("Roboto", Font.BOLD, 50));
        JLabel quickFactLabel = new JLabel();
        quickFactLabel.setText("<html><p>" + quickFact + "</p></html>");
        quickFactLabel.setFont(new Font("Roboto", Font.BOLD, 30));

        // Button
        JButton nextButton = new JButton("NEXT");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(gameStage < 5){
                    loadQuestion();
                }
                else{
                    endGame();
                } 
            }
        });

        // Panel 
        JPanel mainPanel = new JPanel();
        mainPanel.add(correctLabel);
        mainPanel.add(quickFactLabel);
        mainPanel.add(nextButton); 

        this.add(mainPanel);
        this.setVisible(true);
    }

    private void endGame(){
        clearFrame();
        Main.storeGame(game);
        ArrayList<TriviaGame> games = Main.readGames();
        for(TriviaGame game: games){
            System.out.println(game.getName());
        }
        ArrayList<TriviaGame> topGames = Main.getTop(games);
        for(TriviaGame topgame: topGames){
            System.out.println(topgame.getName());
        }
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
