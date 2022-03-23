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

        String clubStats = "Club " + getName() + ":\n" + "matches won: " + getWinCount() + "\n" +
                "matches lost: " + getDefeatCount() + "\n" +
                "matches draw: " + getDrawCount() + "\n" +
                "matches played: " + getMatchesPlayed() + "\n" +
                "scored goals: " + getScoredGoalsCount() + "\n" +
                "received goals: " + getReceivedGoalsCount() + "\n" +
                "points: " + getPoints();
        return clubStats;

    }
}
