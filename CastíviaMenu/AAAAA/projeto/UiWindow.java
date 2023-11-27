import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UiWindow extends POOaoTrivia {
    private JFrame frame;
    private JPanel panel;

    public UiWindow() {
    }
    public void mainWindow() {
        ArrayList<Question> questionList = getQuestionsFromFile();
        frame = new JFrame();
        frame.setTitle("Poo trivia");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (questionList != null && !questionList.isEmpty()) {
            loadNextQuestion(questionList);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No questions available.", "Game Over", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void loadNextQuestion(ArrayList<Question> questionList) {
        Question currentQuestion = obterPerguntaAleatoria(questionList, questionTypeArray, value);
        addType(questionTypeArray, currentQuestion, value);
        incValue();
        System.out.println(value);
        ArrayList<Option> optionsList = currentQuestion.getOptions();

        //JLabel typeLabel = new JLabel(currentQuestion.typeQuestion, SwingConstants.CENTER);
        JLabel questionLabel = new JLabel("<html><center>" + currentQuestion.typeQuestion + "<br/>Pergunta: " + currentQuestion.getQuestion() + "<center><html>", SwingConstants.CENTER);

        panel = new JPanel();
        panel.setLayout(new GridLayout(optionsList.size() + 2, 1));
        //panel.add(typeLabel);
        panel.add(questionLabel);

        if (optionsList.size() > 2) {
            for (int i = 0; i < optionsList.size(); i++) {
                char optionLetter = (char) ('A' + i);
                JButton button = new JButton("Opção " + optionLetter + ": " + optionsList.get(i).getOpString());
                button.addActionListener(new OptionButtonListener(questionList));
                panel.add(button);
            }
        } else {
            JButton buttonTrue = new JButton("Verdadeiro");
            buttonTrue.addActionListener(new OptionButtonListener(questionList));
            panel.add(buttonTrue);

            JButton buttonFalse = new JButton("Falso");
            buttonFalse.addActionListener(new OptionButtonListener(questionList));
            panel.add(buttonFalse);
        }

        // Set the content pane of the JFrame
        frame.setContentPane(panel);

        // Update the UI
        frame.revalidate();
        frame.repaint();
    }

    private class OptionButtonListener implements ActionListener {
        private ArrayList<Question> questionList;

        public OptionButtonListener(ArrayList<Question> questionList) {
            this.questionList = questionList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the button click event
            // Load the next random question
            loadNextQuestion(questionList);
        }
    }


    }

