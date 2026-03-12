package com.example.csc311_cardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override

    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file that defines the game screen layout
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/csc311_cardgame/main.fxml"));
        Scene scene = new Scene(loader.load(), 620, 420);

        // Apply the CSS stylesheet for styling
        scene.getStylesheets().add(
                getClass().getResource("/com/example/csc311_cardgame/styles.css").toExternalForm());
        primaryStage.setTitle("Card 24 Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
