# Football League Manager
Football league manager is a football league simulator where u can add clubs, matches, and look at cool data from these matches

## [Video Demonstration](https://www.youtube.com/watch?v=VJlGCv_ZLFA)


https://user-images.githubusercontent.com/81759594/185756827-ea99183c-8d05-4faf-ab0d-9b47f30efb44.mov


[youtube video](https://www.youtube.com/watch?v=VJlGCv_ZLFA)

## Features

- On the left side of the UI, we have the addition of data (Adding football clubs, and a played match)

- In the middle, we can render 2 different Leaderboards, one that ranks the clubs based on points and one that ranks them based on goals/match (sorted using [Comparators](https://github.com/psycho-baller/Football-League-Manager/tree/master/src/main/java/Comparators)). We can alternate between each render using the button under the leaderboards

- The right side of the UI is for retrieving data, by default, the match log will be rendered, but when the `view raw data` button is pressed, we would see all the data of all the clubs in the league. And lastly, if we want to view personalized stats for a Club in the League, we can do that by writing down the club name and clicking `view club stats` (that's being rendered in the above image)

- In addition, you can save and load previously saved League progress. Data is stored using the [CSV](https://en.wikipedia.org/wiki/Comma-separated_values) format. You can find an example of how the data is stored [here](https://github.com/psycho-baller/Football-League-Manager/blob/master/src/main/resources/data.csv)

## How to run the League Manager
- First you will have to make sure you have a JDK installed in your machine (at least version 16). You can install the latest version [here](https://www.java.com/en/download/)
- You will also need to download javaFX, You can install the latest version [here](https://gluonhq.com/products/javafx/)
- after fulfilling the requirements, you can run the program by simply going to your command line and writing either:
    - If you want to run the project with a loaded csv file with the league data: 
        - `java --module-path "C:\<your>\<JavaFX>\<Folder>\lib" --add-modules javafx.controls,javafx.fxml -jar group_project.jar <instert the csv or txt file with the data in> <insert the maximum number of clubs the league can have>`
    - If you want to run the project with no saved data (you can load data inside the GUI):
        - `java --module-path "C:\<your>\<JavaFX>\<Folder>\lib" --add-modules javafx.controls,javafx.fxml -jar group_project.jar`
