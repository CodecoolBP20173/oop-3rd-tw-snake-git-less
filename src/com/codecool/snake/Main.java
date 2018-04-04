package com.codecool.snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    void cleanup() {
        // stop animations reset model ect.
    }


    public void restart(Stage stage) {
        cleanup();
        start(stage);
    }


    void restartPopup(String infoMessage, String titleBar, Stage stage) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to restart the game?", "Restart!", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            restart(stage);
        }

    }


    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Restart");
        Game game = new Game();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restartPopup("GG", "GG", primaryStage);
            }
        });

        game.getChildren().add(button);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }

}
