package GUITests;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class EventButton extends JFrame {
    private JLabel label1, label2, result; 
    private JTextField number1, number2; 
    private JButton buttonSum;
    private JPanel panel; 

    private class ButtonSumListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            int num1 = Integer.parseInt(number1.getText());
            int num2 = Integer.parseInt(number2.getText());
            result.setText("Sum: " + (num1+num2));
        }
    }

    public EventButton(){
        super();
        label1 = new JLabel("Numero 1:");
        number1 = new JTextField(10);
        label2 = new JLabel("Numero 2:");
        number2 = new JTextField(10);
        buttonSum = new JButton("Somar");
        result = new JLabel();

        panel = new JPanel(); 
        panel.setLayout(new GridLayout(3, 2));
        panel.add(label1);
        panel.add(number1);
        panel.add(label2);
        panel.add(number2);
        panel.add(buttonSum);
        panel.add(result);

        buttonSum.addActionListener(new ButtonSumListener());

        this.add(panel);
    }

    public static void main(String[] args){
        EventButton frame = new EventButton(); 
        frame.setTitle("Event: button");
        frame.setSize(300, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

