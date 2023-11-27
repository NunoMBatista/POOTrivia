package GUITests;

import javax.swing.JFrame;

public class Main {
    public static void main(String args[]){
        EventButton frame = new EventButton();

        frame.setTitle("Event: button");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
