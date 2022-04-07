## Football League Manager
This program creates, loads, saves, and edits football leagues for a simulation game called Monsters vs Heroes

## How to run the League Manager
- First you will have to make sure you have a JDK installed in your machine (at least version 16). You can install the latest version [here](https://www.java.com/en/download/)
- You will also need to download javaFX, You can install the latest version [here](https://gluonhq.com/products/javafx/)
- after fulfilling the requirements, you can run the program by simply going to your command line and writing either:
    - If you want to run the project with a loaded csv file with the league data: 
    - `java --module-path "C:\<your>\<JavaFX>\<Folder>\lib" --add-modules javafx.controls,javafx.fxml -jar group_project.jar <instert the csv or txt file with the data in> <insert the maximum number of clubs the league can have>`
    - If you want to run the project with no saved data (you can load data inside the GUI):
    - `java --module-path "C:\<your>\<JavaFX>\<Folder>\lib" --add-modules javafx.controls,javafx.fxml -jar group_project.jar`
