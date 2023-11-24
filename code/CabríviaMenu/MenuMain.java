import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 */
public class MenuMain extends Menu {

    /**
     * 
     */
    public MenuMain() {
        super();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
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
                play.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {option = iterator++;}});
                buttonPanel.add(play);
            } else if (i == 4) { 
                JButton quit = new JButton("Quit");
                quit.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {option = iterator++;}});
                buttonPanel.add(quit);
            } else buttonPanel.add(new JLabel(""));
        }

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JLabel(new String(new char[50]).replace('\0', ' ')), BorderLayout.EAST);
        panel.add(new JLabel(new String(new char[50]).replace('\0', ' ')), BorderLayout.WEST);
        this.add(panel);
    }
}
