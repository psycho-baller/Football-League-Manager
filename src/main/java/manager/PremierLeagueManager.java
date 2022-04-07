package manager;

import java.util.*;
/*
 * @authors Rami Maalouf, Max Kaczmarek
 * @TUT T02, T01
 * @date 2022-4-7
 */
public class PremierLeagueManager {

    private final int maxNumberOfClubs;

    private final ArrayList<FootballClub> league;
    private final ArrayList<Match> matches;

    public PremierLeagueManager(int maxNumberOfClubs) {
        // initialize arraylists of football clubs and matches for each new league
        this.maxNumberOfClubs = maxNumberOfClubs;
        league = new ArrayList<>();
        matches = new ArrayList<>();

    }

    public void addClub(FootballClub club) {
        if((league.size() < maxNumberOfClubs) && (!league.contains(club))) {
            // add club to league if club is not already in league and league is not full
            league.add(club);
        }
    }
    public void removeClub(FootballClub club) {
        league.remove(club);
    }
    public void addMatch(Match match) {
        matches.add(match);
    }
    public ArrayList<FootballClub> getLeague() {
        return league;
    }
    public ArrayList<Match> getMatches() {
        return matches;
    }
    public int getMaxNumberOfClubs() {
        return maxNumberOfClubs;
    }
}
    
    
    
    

