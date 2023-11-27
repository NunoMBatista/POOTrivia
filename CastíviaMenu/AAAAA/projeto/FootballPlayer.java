public class FootballPlayer{

    protected String playerName;
    protected String shirtNumber;

    public FootballPlayer(){
        super();
    }
    public String getPlayerName() {
        return playerName;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }
    
    public FootballPlayer(String playerName, String shirtNumber){
        this.playerName = playerName;
        this.shirtNumber = shirtNumber;
    }


}
