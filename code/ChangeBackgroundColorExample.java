import javax.swing.JFrame;
import java.awt.Color;

public class ChangeBackgroundColorExample {
    public static void main(String[] args) {
        // Create a JFrame instance
        JFrame frame = new JFrame("Change Background Color Example");

        // Set the size of the frame
        frame.setSize(400, 300);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background color (choose your desired color)
        frame.getContentPane().setBackground(Color.BLUE);

        // Make the frame visible
        frame.setVisible(true);
    }
}