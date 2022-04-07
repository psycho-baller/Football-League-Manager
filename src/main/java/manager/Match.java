package manager;
/*
 * @authors Rami Maalouf, Max kaczmarek
 * @TUT T02, T01
 * @date 2022-4-7
 */
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

    public int getTeamAScore() {
        return homeTeamScore;
    }

    public int getTeamBScore() {
        return awayTeamScore;
    }


    public void setHomeClub(FootballClub homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayClub(FootballClub awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeGoals(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayGoals(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public String toString() {
        return homeTeam.getName() + ":" + homeTeamScore + " - " + awayTeamScore + ":" + awayTeam.getName();
    }
}
