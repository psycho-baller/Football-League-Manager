package Comparators;

import manager.FootballClub;

import java.util.Comparator;

/*
 * @authors Rami Maalouf, Max kaczmarek
 * @TUT T02, T01
 * @date 2022-4-7
 */
public class FootballClubPointsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t, FootballClub t1) {

        if (t.getPoints() > t1.getPoints())
            return -1;
        else {
            if (t.getPoints() < t1.getPoints()) {
                return 1;
            } else {
                int goalDif = t.getScoredGoalsCount() - t.getReceivedGoalsCount();
                int goalDif1 = t1.getScoredGoalsCount() - t1.getReceivedGoalsCount();
                return Integer.compare(goalDif1, goalDif);
            }
        }
    }
}