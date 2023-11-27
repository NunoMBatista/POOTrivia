import java.util.ArrayList;

public class  Ski extends Question{
    public Ski(){
        super();
    }
    


    public Ski(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);

    }

    
    @Override
    protected int scoreCalculator() {
        int questionScore = (5 + 3)*2;
        return questionScore;
    }
}
