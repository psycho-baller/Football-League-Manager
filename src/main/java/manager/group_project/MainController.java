package manager.group_project;

import Comparators.FootballClubGoalsComparator;
import Comparators.FootballClubPointsComparator;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manager.FootballClub;
import manager.Match;
import manager.PremierLeagueManager;

import javafx.fxml.FXML;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static manager.group_project.Main.*;

/*
 * @authors Rami Maalouf, Max kaczmarek
 * @TUT T02, T01
 * @date 2022-4-7
 */
public class MainController implements Initializable {

    private PremierLeagueManager plm;

    @FXML
    private TextField addClubTextfield;


    @FXML
    private TextField awayClubNameTextfield;

    @FXML
    private TextField awayGoalsTextfield;


    @FXML
    private TextField homeClubNameTextfield;

    @FXML
    private TextField homeGoalsTextfield;

    @FXML
    private TextArea leaderboardArea;

    @FXML
    private TextArea matchLogsArea;

    @FXML
    private Label status;

    @FXML
    private TextField viewClubStatsTextfield;

    @FXML
    private Button addMatch;
    @FXML
    private Button removeClub;
    @FXML
    private Button viewClubStats;
    @FXML
    private Button viewRawData;
    @FXML
    private Button goalsPerGame;

    private boolean sortedByGoals;

    /**
     * this is our about page
     * @param event
     */
    @FXML
    void aboutAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Message");
        alert.setContentText("Authors: Rami Maalouf, Max kaczmarek\nEmails: rami.rami@ucalgary.ca, maximilian.kaczmarek@ucalgary.ca\nVersion: v1.0\nThis project is a soccer league manager (simulator)");
        alert.show();
    }

    /**
     * adds a new match to the league
     * @param event
     */
    @FXML
    void addMatch(MouseEvent event) {
        if (awayClubNameTextfield.getText().isEmpty() || homeClubNameTextfield.getText().isEmpty() || awayGoalsTextfield.getText().isEmpty() || homeGoalsTextfield.getText().isEmpty()) {
            status.setText("Please fill all the fields!");
        } else {

            String homeClubName = homeClubNameTextfield.getText();
            FootballClub home = null;
            for (FootballClub club : plm.getLeague()) {
                if (club.getName().equalsIgnoreCase(homeClubName)) {
                    home = club;
                }
            }
            //if the home club is not in the league
            if (home == null) {
                status.setText("Home club does not exist in the league");
                status.setTextFill(Color.RED);
                return;
            }
            String awayClubName = awayClubNameTextfield.getText();
            FootballClub away = null;
            for (FootballClub club : plm.getLeague()) {
                if (club.getName().equals(capitalize(awayClubName)))
                    away = club;
            }
            //if the away club is not in the league
            if (away == null){
                status.setText("Home club does not exist in the league");
                return;
            }
            if (home.equals(away)) {
                status.setText("You can't play against yourself");
                return;
            }
            int homeGoals = -1;
            try {
                homeGoals = Integer.parseInt(homeGoalsTextfield.getText());
            } catch (Exception ignored) {
                status.setText("Home goals must be a number");
                return;
            }
            if (homeGoals == -1) {
                status.setText("You have to enter number of goals");
                return;
            }
            int awayGoals = -1;
            try {
                awayGoals = Integer.parseInt(awayGoalsTextfield.getText());
            } catch (Exception ignored) {
                status.setText("Away goals must be a number");
                return;
            }
            if (awayGoals == -1) {
                status.setText("You have to enter number of goals");
                return;
            }
            // after checking the validity of the inputed data, we add the match to the league
            Match match = new Match();
            match.setHomeClub(home);
            match.setAwayClub(away);
            match.setHomeGoals(homeGoals);
            match.setAwayGoals(awayGoals);
            plm.addMatch(match);
            home.setScoredGoalsCount(home.getScoredGoalsCount() + homeGoals);
            away.setScoredGoalsCount(away.getScoredGoalsCount() + awayGoals);
            home.setReceivedGoalsCount(home.getReceivedGoalsCount() + awayGoals);
            away.setReceivedGoalsCount(away.getReceivedGoalsCount() + homeGoals);
            if (homeGoals > awayGoals) {
                home.addWin();
                away.addDefeat();
            } else if (homeGoals < awayGoals) {
                away.addWin();
                home.addDefeat();
            } else {
                home.addDraw();
                away.addDraw();
            }
        }
        updateMatchLog();
        updateLeaderboard();
        status.setText("Match added successfully!");
        status.setTextFill(Color.LIGHTCORAL);
    }

    @FXML
    void closeApp(ActionEvent event) {
        System.exit(0);
    }

    /**
     * gets the comparator and sorts the arraylist by goals per game then prints it to the leaderboard textarea
     */
    void sortByGoalsPerGame(){
        Collections.sort(plm.getLeague(), new FootballClubGoalsComparator()); // sorts the arraylist of clubs by goals per game
        StringBuilder sb = new StringBuilder();
        sb.append("Rank\t").append("Club\t").append("Points\t").append("Goals/match\t").append("Wins\t").append("Losses\n");
        int rank = 1;
        for (FootballClub club : plm.getLeague()) {
            float goalsPerMatch = 0f;
            if (club.getMatchesPlayed() != 0) {
                goalsPerMatch = (float) club.getScoredGoalsCount() / club.getMatchesPlayed();
                goalsPerMatch = (float) (Math.round(goalsPerMatch * 100.0) / 100.0);
            }
            sb.append(rank).append("\t").append(club.getName()).append("\t").append(club.getPoints()).append("\t").append(goalsPerMatch).append("\t\t").append(club.getWinCount()).append("\t").append(club.getDefeatCount()).append("\n");
            rank++;
        }
        leaderboardArea.setText(sb.toString());
        leaderboardArea.setFont(Font.font("Courier New", FontWeight.BOLD, 13));
    }


    /**
     * runs then the sort/unsort button is pressed
     * this button acts like a switch the way the arraylist is sorted
     * @param event
     */
    @FXML
    void goalsPerGame(MouseEvent event) {
        if (sortedByGoals) { // if sorted by goals per game, sort by points
            sortedByGoals = false;
            updateLeaderboard();
        }else{ // if sorted by points, sort by goals per game
            sortedByGoals = true;
            sortByGoalsPerGame();
        }
    }

    /**
     * checks if file exists, and is readable
     * @param fileWorld
     */
    private void checkFiles(File fileWorld) {
        //Check world file
        if (!fileWorld.exists() || !fileWorld.isFile() || !fileWorld.canRead()) {
            status.setText("Error loading world!");
            status.setTextFill(Color.RED);
        }
    }

    /**
     * loads the world from a file
     * @param event
     */
    @FXML
    void loadWorld(ActionEvent event) {
        plm = new PremierLeagueManager(numOfClubs);
        try {
            FileChooser fc = new FileChooser();
            // only allows .txt files and CSV files to be selected
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comma Separated Values files", "*.csv"));


            fc.setInitialDirectory(new File("src/main/resources"));
            fc.setInitialFileName("data.csv");
            File fileWorld = fc.showSaveDialog(new Stage());
            checkFiles(fileWorld);
            //save league into a csv file
            BufferedReader reader = new BufferedReader(new FileReader(fileWorld));
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {//arraylist  will store each line of the data.csv file
                lines.add(line);
            }
            reader.close();
            List<String> clubs = lines.subList(0, lines.indexOf(""));
            List<String> match = lines.subList(lines.indexOf("") + 1, lines.size());


            //gets all the info from the data.csv file and adds it to the league
            for (String s : clubs) {
                String[] parts = s.split(",");
                FootballClub club =  new FootballClub(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                plm.addClub(club); //adds the club to the league
            }
            for (String s : match) {
                String[] parts = s.split(",");
                Match m = new Match();
                for (FootballClub club : plm.getLeague()) {
                    if (club.getName().equalsIgnoreCase(parts[0])) {
                        m.setHomeClub(club); //sets the home team name
                    }
                    if (club.getName().equalsIgnoreCase(parts[1])) {
                        m.setAwayClub(club); //sets the away team name
                    }
                }
                m.setHomeGoals(Integer.parseInt(parts[2])); //sets the home team score for the match
                m.setAwayGoals(Integer.parseInt(parts[3])); //sets the away team score for the match
                plm.addMatch(m); //adds that match to the league
            }
            status.setText("League loaded successfully!");
            updateLeaderboard();
            updateMatchLog();

            if (plm.getLeague().size() != 0) { //if there are clubs in the league, enable all the buttons
                addMatch.setDisable(false);
                removeClub.setDisable(false);
                viewClubStats.setDisable(false);
                viewRawData.setDisable(false);
                goalsPerGame.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status.setText("Error loading league!");
            status.setTextFill(Color.RED);
        }


    }

    /**
     * removes a club from the league
     * @param event
     */
    @FXML
    void removeClub(MouseEvent event) {
        FootballClub club = new FootballClub(capitalize(addClubTextfield.getText()));
        if (plm.getLeague().contains(club)) {   //checks if the club exists in the league
            plm.removeClub(club);
            status.setText("Club removed!");
            updateLeaderboard();
        } else {
            status.setText("This club is not in the league");
            status.setTextFill(Color.RED);
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /**
     * adds a club to the league
     * @param event
     */
    @FXML
    void addClub(MouseEvent event) {
        //checks if the league is full
        if (plm.getLeague().size() == plm.getMaxNumberOfClubs()) {
            status.setText("Can't add more clubs to league");
            status.setTextFill(Color.RED);
            return;
        }
        if(isNumeric(addClubTextfield.getText())){
            status.setText("Club name can't be a number");
            status.setTextFill(Color.RED);
            return;
        }
        FootballClub club = new FootballClub(capitalize(addClubTextfield.getText()));

        if (plm.getLeague().contains(club)) {   //checks if the club already exists
            status.setText("This club is already in the league");
            status.setTextFill(Color.RED);
        } else {
            //if club doesn't exist, it adds the club to the league
            plm.addClub(club);
            status.setText("Club added");
            addMatch.setDisable(false);
            removeClub.setDisable(false);
            viewClubStats.setDisable(false);
            viewRawData.setDisable(false);
            goalsPerGame.setDisable(false);
            updateLeaderboard();
        }
    }


    @FXML
    void saveAsTxt(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("src/main/resources")); // set the initial directory
        fc.setInitialFileName("data.csv"); //set initial file name
        File file = fc.showSaveDialog(new Stage());
        checkFiles(file);
        save(file);
    }

    @FXML
    void saveTxt(ActionEvent event) {
        File file = new File("src/main/resources/data.csv");
        checkFiles(file);
        save(file);
    }
    void save(File file){
        if (file == null) { // if the user cancels the file chooser
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File not saved");
            alert.show();
            status.setText("Could not save file");
            status.setTextFill(Color.RED);
            return;
        }
        PrintWriter writer = null;
        try {
            //save league into a txt file
            writer = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
            status.setText("The league file does not exist!");
            status.setTextFill(Color.RED);
        }
        for (FootballClub club : plm.getLeague()) {
            writer.println(club.getName() + "," + club.getWinCount() + "," + club.getDrawCount() + "," + club.getDefeatCount() + "," + club.getScoredGoalsCount() + "," + club.getReceivedGoalsCount());
        }
        writer.println("");
        for (Match match : plm.getMatches()) {
            writer.println(match.getTeamA().getName() + "," + match.getTeamB().getName() + "," + match.getTeamAScore() + "," + match.getTeamBScore());
        }
        writer.flush();
        writer.close();
        status.setText("League saved!");
    }

    /**
     * updates the match history log
     */
    void updateMatchLog(){
        StringBuilder sb = new StringBuilder();
        for(Match match : plm.getMatches()){
            sb.append(match.toString()).append("\n");
        }
        matchLogsArea.setText(sb.toString());
        matchLogsArea.setFont(Font.font("Courier New", FontWeight.BOLD, 29));
    }

    /**
     * updates the leaderboard
     */
    void updateLeaderboard() {
        Collections.sort(plm.getLeague(), new FootballClubPointsComparator());
        StringBuilder sb = new StringBuilder();
        sb.append("Rank\t").append("Club\t").append("Points\t").append("Wins\t").append("Draws\t").append("Losses\t").append("Goal Difference\n");
        int rank = 1;
        for (FootballClub club : plm.getLeague()) {
            sb.append(rank).append("\t").append(club.getName()).append("\t").append(club.getPoints()).append("\t").append(club.getWinCount()).append("\t").append(club.getDrawCount()).append("\t").append(club.getDefeatCount()).append("\t").append(club.getScoredGoalsCount() - club.getReceivedGoalsCount()).append("\n");
            rank++;
        }
        leaderboardArea.setText(sb.toString());
        leaderboardArea.setFont(Font.font("Courier New", FontWeight.BOLD, 13));
    }

    @FXML
    void viewClubStats(MouseEvent event) {
        for (FootballClub club : plm.getLeague()) {
            if (club.getName().equalsIgnoreCase(viewClubStatsTextfield.getText())) {
                matchLogsArea.setText(club.toString());
                matchLogsArea.setFont(Font.font("", FontWeight.BOLD, 21));
                status.setText("club stats shown");
                return;
            }
        }
        status.setText("This club is not in the league");
        status.setTextFill(Color.RED);
    }

    @FXML
    void viewRawData(MouseEvent event) {
        StringBuilder sb = new StringBuilder();
        for (FootballClub club : plm.getLeague()) {
            sb.append(club.toString()).append("\n");
            sb.append("--------------------").append("\n");
        }
        matchLogsArea.setText(sb.toString());
        matchLogsArea.setFont(Font.font("", FontWeight.SEMI_BOLD, 25));
        status.setText("Raw data shown");
    }

    /**
     * first thing that runs when the program starts
     * @param url
     * @param resourceBundle
     */
    @Override @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plm = new PremierLeagueManager(numOfClubs);
        //gets the hasArguments from the main class if there are any arguments then we get the data from the file which was given as an argument
        if(hasArguments){
            for (String s : clubs) {
                String[] parts = s.split(",");
                FootballClub club =  new FootballClub(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                plm.addClub(club);
            }
            for (String s : match) {
                String[] parts = s.split(",");
                Match m = new Match();
                for (FootballClub club : plm.getLeague()) {
                    if (club.getName().equalsIgnoreCase(parts[0])) {
                        m.setHomeClub(club);
                    }
                    if (club.getName().equalsIgnoreCase(parts[1])) {
                        m.setAwayClub(club);
                    }
                }
                m.setHomeGoals(Integer.parseInt(parts[2]));
                m.setAwayGoals(Integer.parseInt(parts[3]));
                // gets all the info from the data and adds it to the match
                plm.addMatch(m);
            }
            //updates the match history log and leaderboard
            updateLeaderboard();
            updateMatchLog();
        }
        if (plm.getLeague().size() == 0) {
            status.setText("No clubs in the league");
            addMatch.setDisable(true);
            removeClub.setDisable(true);
            viewClubStats.setDisable(true);
            viewRawData.setDisable(true);
            goalsPerGame.setDisable(true);
        }
        //sets the textfields only to be readable
        leaderboardArea.setEditable(false);
        leaderboardArea.setMouseTransparent(true);
        leaderboardArea.setFocusTraversable(false);
        matchLogsArea.setEditable(false);
        matchLogsArea.setMouseTransparent(false);
        matchLogsArea.setFocusTraversable(false);
        status.setMouseTransparent(true);
        status.setFocusTraversable(false);
    }
}
