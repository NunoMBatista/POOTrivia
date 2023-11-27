import java.util.ArrayList;

public class Swimming extends Question{

    public Swimming(){
        super();
    }


    public Swimming(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
    }



    @Override
    protected int scoreCalculator() {
        int questionScore = 5 + 10 + 3;
        return questionScore;
    }
}