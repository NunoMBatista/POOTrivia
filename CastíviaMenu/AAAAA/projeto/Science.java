import java.util.ArrayList;

public class Science extends Question{
    ArrayList<Option> arrayOptionHard = new ArrayList<Option>();
    
    public Science(){
        super();
    }


    public Science(ArrayList<Option> arrayOption, String question,String typeQuestion, ArrayList<Option> arrayOptionHard) {
        super(arrayOption, question, typeQuestion);
        this.arrayOptionHard = arrayOptionHard;
    }


    @Override
    protected int scoreCalculator() {
        int questionScore = 5+5;
        return questionScore;
    }

}