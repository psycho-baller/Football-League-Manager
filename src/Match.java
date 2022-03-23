public class Match {
    
    private FootballClub homeTeam;
    private FootballClub awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    
    
    public FootballClub getTeamA() {
        return homeTeam;
    }
    
    public FootballClub getTeamB() {
        return awayTeam;
    }
    
    public int getTeamAScore(){
        return homeTeamScore;
    }
    
    public int getTeamBScore(){
        return awayTeamScore;
    }
    

    public void setHomeTeam(FootballClub homeTeam) {
        this.homeTeam = homeTeam;
    }
    
    public void setAwayTeam(FootballClub awayTeam) {
        this.awayTeam = awayTeam;
    }
    
    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }
    
    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
    
}
