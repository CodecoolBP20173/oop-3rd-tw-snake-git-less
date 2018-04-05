package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Clown;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthRestorePowerUp;
import com.codecool.snake.entities.powerups.PhasePowerUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.control.Label;

public class Game extends Pane {

    Stage primaryStage = null;
    public static Label healthLabel = new Label("Health: 100");

    public Game() {
        initGame();
    }

    public void restart() {
        this.getChildren().clear();
        Globals.gameObjects.clear();
        Globals.newGameObjects.clear();
        Globals.oldGameObjects.clear();
        Globals.gameLoop.stop();
        this.start();
        initGame();
        buildUi(this.primaryStage);
    }

    void restartPopup(String infoMessage, String titleBar, Stage stage) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to restart the game?", "Restart!", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            this.restart();
        }
    }

    public void initGame() {
        new SnakeHead(this, 500, 500);

        new SimplePowerup(this, Utils.createRandomNumber(1, 1000), Utils.createRandomNumber(1, 700));

        new PhasePowerUp(this, Utils.createRandomNumber(1, 1000), Utils.createRandomNumber(1, 700));
    }


    public void buildUi(Stage primaryStage){
        if(this.primaryStage == null){
            this.primaryStage = primaryStage;
        }
        Button button = new Button("Restart");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.healthLabel.setText("Health: 100");
                restartPopup("GG", "GG", primaryStage);
            }
        });

        healthLabel.setLayoutX(5);
        healthLabel.setLayoutY(25);
        this.getChildren().add(healthLabel);
        this.getChildren().add(button);

    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}
