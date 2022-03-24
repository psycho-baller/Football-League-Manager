import java.io.*;
import java.util.*;


public class PremierLeagueManager{
    
    private final int maxNumberOfClubs;
    
    private final ArrayList<FootballClub> league;
    private final Scanner scanner;
    private final ArrayList<Match> matches;
    
    public PremierLeagueManager(int maxNumberOfClubs) {
        // initialize arraylists of football clubs and matches for each new league
        this.maxNumberOfClubs = maxNumberOfClubs;
        league = new ArrayList<>();
        matches = new ArrayList<>();
        scanner = new Scanner(System.in);
        Menu();
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * the first thing that the user sees when they start the program
     */
    private void Menu() {
        
        while(true) {
            System.out.println("---------------------------------------------");
            System.out.println("    Premier League menu: ");
            System.out.println("    1: Create new team and add it to the league");
            System.out.println("    2: Delete existing team from league");
            System.out.println("    3: Add a Played Match");
            System.out.println("    4: Display raw Data");
            System.out.println("    5: Display cool Data");
            System.out.println("    6: get stored data");
            System.out.println("    7: Exit (save data)");
            System.out.println("---------------------------------------------");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception ignored) {}

            switch (command) {
                case 1 -> addTeam();
                case 2 -> deleteTeam();
                case 3 -> addPlayedMatch();
                case 4 -> displayRawData();
                case 5 -> displayStatistics();
                case 6 -> getStoredData();
                case 7 -> exit();
                default -> System.out.println("Wrong Command");
            }
        }
    }

    /**
     * adds a new team to the league
     */
    private void addTeam(){
        if(league.size() == maxNumberOfClubs) {
            System.out.println("Can't add more clubs to league");
            return;
        }
        
        System.out.println("Insert Club Name: ");
        String line = scanner.nextLine();
        FootballClub club = new FootballClub(capitalize(line));

        if(league.contains(club)){
            System.out.println("This club is already in the league");
            return;
        }
        league.add(club);
    }

    /**
     * deletes a team from the league
     */
    private void deleteTeam() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
         for(FootballClub club : league) {
             if(club.getName().equalsIgnoreCase(line)){
                 league.remove(club);
                 System.out.println("Club "+ club.getName()+" removed");
                 return;
             }
         }
         System.out.println("No such club in league");
    }

    /**
     * shows the raw data for all the teams in the league
     */
    private void displayRawData() {
        for(FootballClub club : league) {
            System.out.println(club.toString());
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * shows a list of different options for the user to choose from
     */
    private void displayStatistics() {
        System.out.println("""
        ---------------------------------------------
        1: Get Individual Club Statistics
        2: Get Premier League Leaderboard
        3: Get Leaderboard sorted by most goals per match
        4: Get best player from a club
        5: Back to main menu
        ---------------------------------------------
        """);
        int command = 0;
        try {
            command = Integer.parseInt(scanner.nextLine());
        } catch (Exception ignored) {}

        switch (command) {
            case 1 -> individualClubStats();
            case 2 -> displayLeagueScoreboard();
            case 3 -> MostGoalsPerMatch();

            case 4 -> getBestPlayer();
            case 5 -> System.out.println("Returning to menu...");
            default -> System.out.println("Wrong Command");
        }
    }

    /**
     * Prints out individual statistics for a given club
     *
     */
    private void individualClubStats() {
        System.out.println("Insert club name: ");
        String clubName = scanner.nextLine();
        boolean clubExists = false;
        for (FootballClub club : league) {
            if (club.getName().equalsIgnoreCase(clubName)) {
                clubExists = true;
                break;
            }
        }
        if (!clubExists) {
            System.out.println("No such club in league");
            displayStatistics();
            return;
        }
        for (FootballClub club : league) {
            if (club.getName().equalsIgnoreCase(clubName)) {
                System.out.println(club.toString());
                return;
            }
        }
    }

    private void MostGoalsPerMatch() {
        Collections.sort(league, new FootballClubGoalsComparator());
        int counter = 0;
        for(FootballClub club : league) {
            counter++;
            float goalsPerMatch = (float) club.getScoredGoalsCount()/club.getMatchesPlayed();
            System.out.println(counter + ". " + club.getName() + ": " + club.getScoredGoalsCount() + " total goals and "  + goalsPerMatch + " goals per match");
        }
    }

    private void getBestPlayer(){
        System.out.println("Insert club name: ");
        String clubName = scanner.nextLine();
        boolean clubExists = false;
        for (FootballClub club : league) {
            if (club.getName().equalsIgnoreCase(clubName)) {
                clubExists = true;
                break;
            }
        }
        if (!clubExists) {
            System.out.println("No such club in league");
            displayStatistics();
            return;
        }
        for (FootballClub club : league) {
            if (club.getName().equalsIgnoreCase(clubName)) {
                club.getBestPlayer();
            }
        }
    }

    private void displayLeagueScoreboard() {
        
        Collections.sort(league, new FootballClubPointsComparator());
        for(FootballClub club : league) {
            System.out.println("Club: " + club.getName()+" Points: "+ club.getPoints()+" goal difference: "+ (club.getScoredGoalsCount()-club.getReceivedGoalsCount()));
        }
    }
    
    private void addPlayedMatch(){

        System.out.println("Enter Home Team: ");
        String line = scanner.nextLine();
        FootballClub home = null;
        for(FootballClub club : league){
            if(club.getName().equalsIgnoreCase(line)){
                home = club;
            }
        }
        if (home == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Enter Away Team: ");
        line = scanner.nextLine();
        FootballClub away = null;
        for(FootballClub club : league){
            if(club.getName().equals(capitalize(line)))
                away = club;
        }
        if (away == null) {
            System.out.println("No such club in league");
            return;
        }
        if (home.equals(away)) {
            System.out.println("You can't play against yourself");
            return;
        }
        System.out.println("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try {
            homeGoals = Integer.parseInt(line);
        } catch (Exception ignored) {}
        if (homeGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }
        System.out.println("Enter away team goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try {
            awayGoals = Integer.parseInt(line);
        } catch (Exception ignored) {}
        if (awayGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }
        Match match = new Match();
        match.setHomeTeam(home);
        match.setAwayTeam(away);
        match.setHomeTeamScore(homeGoals);
        match.setAwayTeamScore(awayGoals);
        matches.add(match);
        home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
        away.setScoredGoalsCount(away.getScoredGoalsCount()+awayGoals);
        home.setReceivedGoalsCount(home.getReceivedGoalsCount()+awayGoals);
        away.setReceivedGoalsCount(away.getReceivedGoalsCount()+homeGoals);
        if (homeGoals > awayGoals) {
            home.addWin();
            away.addDefeat();
        }
        else if (homeGoals < awayGoals) {
            away.addWin();
            home.addDefeat();
        }
        else {
            home.addDraw();
            away.addDraw();
        }
    }

    private void getStoredData(){
        BufferedReader reader = null;
        try {
            //save league into a txt file
            reader = new BufferedReader(new FileReader(Main.getData()));
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null){//arraylist  will store each line of the world.txt file
                lines.add(line);
            }
            reader.close();
            List<String> clubs = lines.subList(0, lines.indexOf(""));
            List<String> match = lines.subList(lines.indexOf("") + 1, lines.size());



            for(String s : clubs){
                String[] parts = s.split(",");
                league.add(new FootballClub(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
            }
            for (String s : match){
                String[] parts = s.split(",");
                Match m = new Match();
                for(FootballClub club : league){
                    if(club.getName().equalsIgnoreCase(parts[0])){
                        m.setHomeTeam(club);
                    }
                    if(club.getName().equalsIgnoreCase(parts[1])){
                        m.setAwayTeam(club);
                    }
                }
                m.setHomeTeamScore(Integer.parseInt(parts[2]));
                m.setAwayTeamScore(Integer.parseInt(parts[3]));
                matches.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("The world file does not exist!");
            return;
        }
    }


    private void exit(){
        PrintWriter writer = null;
        try {
            //save league into a txt file
            writer = new PrintWriter(new FileWriter(Main.getData()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("The world file does not exist!");
            System.exit(1);
        }
        for (FootballClub club : league) {
            writer.println(club.getName() + "," + club.getWinCount() + "," + club.getDrawCount() + "," + club.getDefeatCount() + "," + club.getScoredGoalsCount() + "," + club.getReceivedGoalsCount());
        }
        writer.println("");
        for (Match match : matches) {
            writer.println(match.getTeamA().getName() + "," + match.getTeamB().getName() + "," + match.getTeamAScore() + "," + match.getTeamBScore());
        }

        writer.flush();
        writer.close();
        System.out.print("\nBye!");
        System.exit(0);
    }
} 
    
    
    
    

