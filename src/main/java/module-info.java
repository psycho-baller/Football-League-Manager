module manager.football.group_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens manager.group_project to javafx.fxml;
    exports manager.group_project;
}
