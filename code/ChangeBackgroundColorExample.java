import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ChangeBackgroundColorExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Custom Font Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JLabel
        JLabel label = new JLabel("Hello, Swing!");

        // Load the custom TTF font
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("gamefiles" + File.separator + "mono.ttf"));
            Font scaledFont = customFont.deriveFont(Font.BOLD, 50);
            label.setFont(scaledFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Add the JLabel to the JFrame
        frame.add(label);

        // Set the JFrame visibility
        frame.setVisible(true);
    }
}