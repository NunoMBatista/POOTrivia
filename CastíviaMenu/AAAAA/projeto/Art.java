import java.util.ArrayList;

public class Art extends Question{
    public Art(){
        super();
    }



    public Art(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
    }


        @Override
    protected int scoreCalculator() {
        int questionScore = 5*10;
        return questionScore;
    }

}
