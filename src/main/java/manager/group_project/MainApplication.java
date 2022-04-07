package manager.group_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.PremierLeagueManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainApplication extends Application {
    public static boolean hasArguments = false;
    public static String data;
    static int numOfClubs = 10; // default max number of clubs

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 630);
        stage.setTitle("Football Manager");
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            hasArguments = true;
            data = "src\\main\\resources\\" + args[0];
            numOfClubs = Integer.parseInt(args[1]);
            PremierLeagueManager plm = new PremierLeagueManager(numOfClubs);
            BufferedReader reader = null;
            try {
                //save league into a csv file
                reader = new BufferedReader(new FileReader(data));
                loadData(reader);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.print("The world file does not exist!");
                return;
            }
        }
        launch();
    }

    public static List<String> clubs;
    static List<String> match;
    static void loadData(BufferedReader reader) throws IOException {
        String line;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {//arraylist  will store each line of the data.csv file
            lines.add(line);
        }
        reader.close();
        clubs = lines.subList(0, lines.indexOf(""));
        match = lines.subList(lines.indexOf("") + 1, lines.size());
    }
}
