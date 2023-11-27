import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Tiago Silva
 * @author Miguel Cabral Pinto
 * @version 1.0
 * 
 */
public class Window extends JFrame {

    /**
     * 
     * -1 -> Quit Game
     * 0  -> Main Menu
     * 1  -> Question Menu
     */
    int stage;

    /**
     * 
     */
    int option;

    /**
     * 
     */
    JPanel panel;

    /**
     * 
     */
    public Window() {
        this.setTitle("POO Trivia");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.stage = 0;
        this.panel = new JPanel();
        mainMenu();
    }

    /**
     * 
     * @return
     */
    public int getStage() {return stage;}

    /**
     * 
     * @param stage
     */
    public void setStage(int stage) {this.stage = stage;}

    /**
     * 
     * @return
     */
    public int getOption() {return option;}

    /**
     * 
     * @param option
     */
    public void setOption(int option) {this.option = option;}

    /**
     * 
     * @return
     */
    public JPanel getQuestionPanel() {return panel;}

    /**
     * 
     * @param panel
     */
    public void setQuestionPanel(JPanel panel) {this.panel = panel;}

    /**
     * 
     * @param b
     */
    public void visibility(boolean b) {this.setVisible(b);}

    /**
     * 
     */
    public void mainMenu() {
        fillMainPanel();
        this.add(this.panel);
        this.setVisible(true);
    }

    /**
     * 
     */
    private void fillMainPanel() {
        this.panel.removeAll();
        this.panel.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2, 3));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));

        JLabel label = new JLabel("POO Trivia");
        label.setFont(new Font("Courier", Font.BOLD, 50));
        for (int i = 0; i < 6; i++) {
            if (i == 4) titlePanel.add(label);
            else titlePanel.add(new JLabel(""));
        }
        for (int i = 0; i < 7; i++) {
            if (i == 2) {
                JButton play = new JButton("Play");
                play.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {stage = 1;}});
                buttonPanel.add(play);
            } else if (i == 4) { 
                JButton quit = new JButton("Quit");
                quit.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {stage = -1;}});
                buttonPanel.add(quit);
            } else buttonPanel.add(new JLabel(""));
        }

        this.panel.add(titlePanel, BorderLayout.NORTH);
        this.panel.add(buttonPanel, BorderLayout.CENTER);
        this.panel.add(new JLabel(new String(new char[50]).replace('\0', ' ')), BorderLayout.EAST);
        this.panel.add(new JLabel(new String(new char[50]).replace('\0', ' ')), BorderLayout.WEST);
    }    
    /**
     * 
     * @param question
     */
    public void questionMenu(Question question) {
        this.option = -1;
        fillQuestionPanel(question);
        this.add(this.panel);
        this.setVisible(true);
    }

    /**
     * 
     */
    public void fillQuestionPanel(Question question) {
        this.panel.removeAll();
        int optionGridSize = 2 * question.getOptions().size() + 1;
        this.panel.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        JPanel secondaryTextPanel = new JPanel();
        secondaryTextPanel.setLayout(new BorderLayout());
        JPanel terciaryTextPanel = new JPanel();
        terciaryTextPanel.setLayout(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(optionGridSize, 1));

        JLabel label = new JLabel(question.getText());
        label.setFont(new Font("Courier", Font.BOLD, 17));
        terciaryTextPanel.add(label, BorderLayout.CENTER);
        terciaryTextPanel.add(new JLabel(new String(new char[113]).replace('\0', '_')), BorderLayout.CENTER);
        secondaryTextPanel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.WEST);
        secondaryTextPanel.add(terciaryTextPanel);
        textPanel.add(new JLabel(""));
        textPanel.add(secondaryTextPanel);

        for (int i = 0; i < optionGridSize; i++) {
            if ((i & 1) == 1) {
                final int optionValue = i / 2;
                JButton tempButton = new JButton(question.getOptions().get(i / 2));
                tempButton.setHorizontalAlignment(SwingConstants.LEFT);
                tempButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {option = optionValue;}});
                buttonPanel.add(tempButton);
            }
            else buttonPanel.add(new JLabel(""));
        }

        this.panel.add(textPanel, BorderLayout.NORTH);
        this.panel.add(buttonPanel, BorderLayout.CENTER);
        this.panel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.EAST);
        this.panel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.WEST);
    }
}
