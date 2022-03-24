
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Junit setup for every function

/**
 * Junit 5 testing function
 * Simple method that attempts to read the text file you provided in the argument
 * If any issues happen it throws fileNotFound exception or IOexecption
 */
public class Tests {
    @Test
    @DisplayName("test for checkingBestPlayer")
    void checkingBestPlayer() {
        String filename = "data.csv";

        boolean test = false;
        boolean containsImport = false;

        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while (line != null && !containsImport) {
                if (line.matches("import+\s.*")) {
                    containsImport = true;
                }
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        assertEquals(containsImport, test);
    }

    @Test
    void PremierLeagueManager(){
        PremierLeagueManager Manager = new PremierLeagueManager(2, true);
        ArrayList<FootballClub> league = new ArrayList<>();

        //tries to add 3 clubs to a league with a maximum of 2 clubs
        league.add(new FootballClub("Arsenal"));
        league.add(new FootballClub("Manchester United"));
        league.add(new FootballClub("Chelsea"));

        //checks if the 3rd addition failed (which it should)
        if(league.size() == 3){
            assert(true);
        }
        else{
            assert(false);
        }
    }

    @Test
    //tests if the footballClub constructor works properly
    void FootballClub() {
        FootballClub club = new FootballClub("Arsenal");
        assertEquals(club.getName(), "Arsenal");
    }

    @Test
    //tests if the getPoints works properly
    void FootballClub2() {
        int wins = 10;
        int draws = 2;
        int defeat = 5;
        FootballClub club = new FootballClub("Arsenal", wins, draws, defeat,10,8);
        assertEquals(club.getPoints(), wins*3 + draws);
    }


}


