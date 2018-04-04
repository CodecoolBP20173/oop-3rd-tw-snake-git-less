package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    Game game = new Game();

    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();

        game.buildUi(primaryStage);
        game.start();

    }

}
