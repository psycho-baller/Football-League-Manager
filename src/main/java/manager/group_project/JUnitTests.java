package manager.group_project;
import static org.junit.jupiter.api.Assertions.*;

import manager.FootballClub;
import manager.Match;
import manager.PremierLeagueManager;
import org.junit.jupiter.api.Test;

class JUnitTests {

    @Test
    void test1() {
        PremierLeagueManager manager = new PremierLeagueManager(3);
        manager.addClub(new FootballClub("Chelsea"));
        manager.addClub(new FootballClub("Arsenal"));
        manager.addClub(new FootballClub("Manchester United"));
        manager.addClub(new FootballClub("Manchester City"));

        // Manchester United will not be added because the mex number of clubs for this league is 3
        assertEquals(3, manager.getLeague().size());
    }

    @Test
    void test2() {
        PremierLeagueManager manager = new PremierLeagueManager(3);
        manager.addClub(new FootballClub("Chelsea"));
        manager.addClub(new FootballClub("Chelsea"));

        // Chelsea` will not be added because it is already in the league
        assertEquals(1, manager.getLeague().size());
    }

    @Test
    void test3(){
        PremierLeagueManager manager = new PremierLeagueManager(4);
        FootballClub chelsea = new FootballClub("Chelsea");
        FootballClub arsenal = new FootballClub("Arsenal");
        FootballClub manchesterUnited = new FootballClub("Manchester United");
        FootballClub manchesterCity = new FootballClub("Manchester City");
        manager.addClub(chelsea);
        manager.addClub(arsenal);
        manager.addClub(manchesterUnited);
        manager.addClub(manchesterCity);
        Match match = new Match();
        match.setHomeClub(chelsea);
        match.setAwayClub(arsenal);
        match.setHomeGoals(2);
        match.setAwayGoals(1);
        manager.addMatch(match);
        Match match2 = new Match();
        match2.setHomeClub(manchesterCity);
        match2.setAwayClub(manchesterUnited);
        match2.setHomeGoals(4);
        match2.setAwayGoals(6);
        manager.addMatch(match2);

        // checks if the 2 matches were added to the league
        assertEquals(2, manager.getMatches().size());
    }

    @Test
    void test4(){
        PremierLeagueManager manager = new PremierLeagueManager(4);
        FootballClub chelsea = new FootballClub("Chelsea");
        FootballClub arsenal = new FootballClub("Arsenal");
        FootballClub manchesterUnited = new FootballClub("Manchester United");
        FootballClub manchesterCity = new FootballClub("Manchester City");
        manager.addClub(chelsea);
        manager.addClub(arsenal);
        manager.addClub(manchesterUnited);
        manager.addClub(manchesterCity);
        Match match = new Match();
        match.setHomeClub(chelsea);
        match.setAwayClub(arsenal);
        match.setHomeGoals(2);
        match.setAwayGoals(1);
        manager.addMatch(match);

        // checks if the toString method works
        assertEquals("Chelsea:2 - 1:Arsenal" ,match.toString());
    }

    @Test
    void test5(){
        PremierLeagueManager manager = new PremierLeagueManager(4);
        FootballClub chelsea = new FootballClub("Chelsea");
        FootballClub arsenal = new FootballClub("Arsenal");
        FootballClub manchesterUnited = new FootballClub("Manchester United");
        FootballClub manchesterCity = new FootballClub("Manchester City");
        manager.addClub(chelsea);
        manager.addClub(arsenal);
        manager.addClub(manchesterUnited);
        manager.addClub(manchesterCity);
        assertEquals(4, manager.getLeague().size());

        // checks if the we're able to remove a club from the league
        manager.removeClub(chelsea);
        assertEquals(3, manager.getLeague().size());
        manager.removeClub(arsenal);
        assertEquals(2, manager.getLeague().size());
    }
}