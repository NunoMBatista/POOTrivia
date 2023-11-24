import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 */
public class MenuQuestion extends JFrame {

    /**
     * 
     */
    boolean active;

    /**
     * 
     * @param question
     */
    public MenuQuestion(Question question) {
        int optionGridSize = 2 * question.getOptions().size() + 1;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        JPanel secondaryTextPanel = new JPanel();
        secondaryTextPanel.setLayout(new BorderLayout());
        JPanel terciaryTextPanel = new JPanel();
        terciaryTextPanel.setLayout(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(optionGridSize, 1));

        JLabel label = new JLabel(question.getText());
        label.setFont(new Font("Courier", Font.BOLD, 18));
        terciaryTextPanel.add(label, BorderLayout.CENTER);
        terciaryTextPanel.add(new JLabel(new String(new char[113]).replace('\0', '_')), BorderLayout.CENTER);
        secondaryTextPanel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.WEST);
        secondaryTextPanel.add(terciaryTextPanel);
        textPanel.add(new JLabel(""));
        textPanel.add(secondaryTextPanel);

        for (int i = 0; i < optionGridSize; i++) {
            if ((i & 1) == 1) {
                JButton tempButton = new JButton(question.getOptions().get(i / 2));
                tempButton.setHorizontalAlignment(SwingConstants.LEFT);
                tempButton.addActionListener(new ButtonListener());
                buttonPanel.add(tempButton);
            }
            else buttonPanel.add(new JLabel(""));
        }

        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.EAST);
        panel.add(new JLabel(new String(new char[10]).replace('\0', ' ')), BorderLayout.WEST);
        this.add(panel);
    }

    /**
     * 
     * @param b
     */
    public void visibility(boolean b) {this.setVisible(b);}

    /**
     * 
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
