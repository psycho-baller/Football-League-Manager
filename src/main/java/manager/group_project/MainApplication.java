package manager.group_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.PremierLeagueManager;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {
    public static String data;
    static int numOfClubs = 10; // default number of clubs

//    public static File getData() {
//        File file = new File(data);
//        if (!file.exists() || !file.isFile() || !file.canWrite()) {
//            System.err.printf("The data file %s does not exist!%n", file.getAbsoluteFile());
//            System.exit(1);
//        }
//        return file;
//    }
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
            data = args[0];
            //TODO: get data from args
            numOfClubs = Integer.parseInt(args[1]);
        }

        launch();
    }
}
