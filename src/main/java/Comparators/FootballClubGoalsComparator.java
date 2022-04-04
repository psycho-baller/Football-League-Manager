package Comparators;

import manager.FootballClub;

import java.util.Comparator;


public class FootballClubGoalsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t, FootballClub t1) {
        float goalsPerMatch = (float) t.getScoredGoalsCount() / t.getMatchesPlayed();
        float goalsPerMatch1 = (float) t1.getScoredGoalsCount() / t1.getMatchesPlayed();
        if (goalsPerMatch > goalsPerMatch1) {
            return -1;
        } else {
            if (goalsPerMatch < goalsPerMatch1) {
                return 1;
            } else {
                if (t.getPoints() > t1.getPoints())
                    return -1;
                else {
                    return 1;
                }
            }
        }
    }
}