import java.util.ArrayList;

public class Football extends Question {

    public Football(){
        super();
    }

    ArrayList<FootballPlayer> arrayPlayers = new ArrayList<FootballPlayer>();
    public Football(ArrayList<Option> arrayOption, String question, ArrayList<FootballPlayer> arrayPlayers, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
        this.arrayPlayers = arrayPlayers;
    }
    

    @Override
    protected int scoreCalculator() {
        int questionScore = 5+3+1;
        return questionScore;
    }






}
