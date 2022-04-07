package manager;

import java.util.*;

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

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public void addClub(FootballClub club) {
        league.add(club);
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
    
    
    
    

