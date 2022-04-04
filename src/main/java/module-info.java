module manager.football.group_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens manager.group_project to javafx.fxml;
    exports manager.group_project;
}