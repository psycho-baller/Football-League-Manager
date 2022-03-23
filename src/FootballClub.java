/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class FootballClub extends SportsClub {

    private int winCount;
    private int drawCount;
    private int defeatCount;
    private int scoredGoalsCount;
    private int receivedGoalsCount;

    public FootballClub(String name) {
        super(name);
    }

    public FootballClub(String name, int winCount, int drawCount, int defeatCount, int scoredGoalsCount, int receivedGoalsCount) {
        super(name);
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.defeatCount = defeatCount;
        this.scoredGoalsCount = scoredGoalsCount;
        this.receivedGoalsCount = receivedGoalsCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getDefeatCount() {
        return defeatCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }

    public int getPoints() {
        return 3 * winCount + drawCount;
    }

    public int getMatchesPlayed() {
        return winCount + drawCount + defeatCount;
    }

    public void setScoredGoalsCount(int i) {
        scoredGoalsCount = i;
    }

    public void setReceivedGoalsCount(int i) {
        receivedGoalsCount = i;
    }


    public void addWin() {
        winCount++;
    }

    public void addDraw() {
        drawCount++;
    }

    public void addDefeat() {
        defeatCount++;
    }

    @Override
    public String toString() {

        String clubStats = "Club " + getName() + " matches won: " + getWinCount() + "\n" +
                "Club " + getName() + " matches lost: " + getDefeatCount() + "\n" +
                "Club " + getName() + " matches draw: " + getDrawCount() + "\n" +
                "Club " + getName() + " scored goals: " + getScoredGoalsCount() + "\n" +
                "Club " + getName() + " received goals: " + getReceivedGoalsCount() + "\n" +
                "Club " + getName() + " points: " + getPoints() + "\n" +
                "Club " + getName() + " matches played: " + getMatchesPlayed() + "\n";
        return clubStats;

    }
}
