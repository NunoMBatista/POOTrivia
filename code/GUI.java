import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("pauzásso");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("PERGUNTA NÚMERO 1");
        JLabel gunta = new JLabel("<html><p>O Alexandre é um rapaz guloso, seja Q a quantidade de queijo roubado por cada 100kg e G_i os elementos de um array G, sendo que G_i representa o quão guloso se sentia o Alexandro no mês i. Calcule o número N de queijos roubados no dia mais guloso do ano<p><html>");
        JButton button1 = new JButton ("o joca");
        JButton button2 = new JButton ("come");
        JButton button3 = new JButton ("pau");
        JButton button4 = new JButton ("LOOOOOOOOOL");
        JButton button5 = new JButton ("Nenhuma das outras(opção correta)");
        //n pode porque é mega vegetarians of the lifes

        JPanel panel = new JPanel("Questão impossivel");
        panel.setLayout(new GridLayout(5,2));
        panel.add(label);
        panel.add(gunta);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        frame.add(panel);
        frame.setVisible(true);
    }
}
