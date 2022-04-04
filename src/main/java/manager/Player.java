package manager;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private int goalsScored;
    private int shotsTaken;
    private int shotsMissed;

    //0 to 4 goals
    int minimum = 0;
    int maximum = 4;

    public void setGoalsScored() {
        goalsScored = ThreadLocalRandom.current().nextInt(minimum, maximum + 1);
    }

    /**
     * Set goals method for majority of players, who will not score a goal
     *
     * @param numberOfGoals,
     */
    public void setGoalsScored(int numberOfGoals) {
        goalsScored = numberOfGoals;
    }

    public void setShotsTaken() {
        if (goalsScored != 0) {
            shotsTaken = ThreadLocalRandom.current().nextInt(goalsScored, goalsScored + 5);
        } else {
            shotsTaken = ThreadLocalRandom.current().nextInt(minimum, maximum + 1);
        }
    }

    public void setShotsTaken(int numberOfShots) {
        shotsTaken = 0;
    }

    public void setShotsMissed() {
        if (goalsScored != 0) {
            shotsMissed = shotsTaken - goalsScored;
        } else {
            shotsMissed = shotsTaken;
        }
    }

    public int getShotsMissed() {
        return shotsMissed;
    }

    public int getShotsTaken() {
        return shotsTaken;
    }

    public int getGoalsScored() {
        return goalsScored;
    }
}
