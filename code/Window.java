import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;

public class Window extends JFrame{

    JPanel panel; 

    public Window(){
        this.setTitle("POO Trivia");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
    }

    public void mainMenu(){
        this.panel.removeAll();
        this.panel.setLayout(new FlowLayout());
         
        JLabel title = new JLabel("POO Trivia");
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        this.panel.add(title);
        
        Box box = new Box(BoxLayout.Y_AXIS);
        Dimension buttonSize = new Dimension(300, 300); 
        
        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(buttonSize);
        box.add(newGame);

        JButton quit = new JButton("Quit"); 
        quit.setPreferredSize(buttonSize);
        box.add(quit);


        this.panel.add(box);
        this.setVisible(true);
    }

    // private class OptionButtonListener implements ActionListener{
        
        
    // }

}
