import javax.swing.*;

/**
 * 
 */
public class Menu extends JFrame {

    /**
     * 
     */
    int option;

    /**
     * 
     */
    int iterator;

    /**
     * 
     */
    public Menu() {
        this.setTitle("POO Trivia");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.option = 0;
        this.iterator = 1;
    }

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
    public int getIterator() {return iterator;}

    /**
     * 
     * @param iterator
     */
    public void setIterator(int iterator) {this.iterator = iterator;}

    /**
     * 
     * @param b
     */
    public void visibility(boolean b) {this.setVisible(b);}
}
