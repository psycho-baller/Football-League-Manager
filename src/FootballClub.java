import java.util.ArrayList;
import java.util.Collections;

public class FootballClub extends SportsClub {

    private int winCount;
    private int drawCount;
    private int defeatCount;
    private int scoredGoalsCount;
    private int receivedGoalsCount;
    private final ArrayList<Player> players = new ArrayList<Player>();

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

    /**
     * setPlayers() method which adds 11 players to the football
     * club, with random data for each player
     */
    public void setPlayers() {
        // players who will attempt to score
        for (int i = 0; i < 1; i++) {
            Player player = new Player();
            player.setGoalsScored(0);
            player.setShotsTaken();
            player.setShotsMissed();
            players.add(player);
        }

        // players who will score
        for (int i = 0; i < 2; i++) {
            Player player = new Player();
            player.setGoalsScored();
            player.setShotsTaken();
            player.setShotsMissed();
            players.add(player);
        }

        // majority of players who won't score
        for (int i = 0; i < 8; i++) {
            Player player = new Player();
            player.setGoalsScored(0);
            player.setShotsTaken(0);
            player.setShotsMissed();
            players.add(player);
        }

    }

    /**
     * Method for dislpaying each player's stats for the given club
     */
    public void displayAllPlayerStats() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("****** PLAYER " + (i+1) + "******");
            System.out.println("Goals: " + players.get(i).getGoalsScored());
            System.out.println("Shots taken: " + players.get(i).getShotsTaken());
            System.out.println("Shots missed: " + players.get(i).getShotsMissed() + "\n");
        }
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

    /**
     * Method for finding and returning the best player for specified club, aka
     * player who has scored most goals. Can be possibly refined to compare
     * ratio of shots taken/shots missed instead
     * @return player with the most goals
     */
    public void getBestPlayer() {
        ArrayList<Integer> goals = new ArrayList<Integer>();
        for (Player player : players) {
            goals.add(player.getGoalsScored());
        }


        int maximumGoals = Collections.max(goals);
        System.out.println("Best player for club " + getName() + " is PLAYER " + (goals.indexOf(maximumGoals)+1) + "\n\n");
    }



    /**
     * Method for dislpaying overall stats
     */
    @Override
    public String toString() {

        return "Club " + getName() + ":\n" + "matches won: " + getWinCount() + "\n" +
                "matches lost: " + getDefeatCount() + "\n" +
                "matches draw: " + getDrawCount() + "\n" +
                "matches played: " + getMatchesPlayed() + "\n" +
                "scored goals: " + getScoredGoalsCount() + "\n" +
                "received goals: " + getReceivedGoalsCount() + "\n" +
                "points: " + getPoints();
    }
}