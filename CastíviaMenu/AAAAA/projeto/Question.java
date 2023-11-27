import java.io.*;
import java.util.*;

abstract public class Question {
    ArrayList<Option> arrayOption = new ArrayList<Option>();
    //protected String[] optionList;
    protected String question;
    protected String typeQuestion;
    // Ver se vale a pena criar variável que indica o tipo de pergunta, honestamente facilita muita coisa

    public Question(ArrayList<Option> arrayOption, String question, String typeQuestion) {
       
        this.typeQuestion = typeQuestion;
        this.arrayOption = new ArrayList<>(arrayOption);
        this.question = question;

    }

    public Question() {

    }
    protected String getQuestion() {
        return question;
    }

    public ArrayList<Option> getOptions() {
        return arrayOption;
    }


    abstract protected int scoreCalculator();
    public void saveQuestion(Question question) {
        File QuestionFile = new File("PerguntasPoo.txt");
        
    }


}
