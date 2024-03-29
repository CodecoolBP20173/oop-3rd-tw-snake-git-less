package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.powerups.HealthRestorePowerUp;
import com.codecool.snake.entities.powerups.PhasePowerUp;
import javafx.scene.image.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("freddie.png");
    public static Image snakeBodyGreen = new Image("snake_body_green.png");
    public static Image snakeBodyRed = new Image("snake_body_red.png");
    public static Image simpleEnemy = new Image("bug.png");
    public static Image burger = new Image("burger.png");
    public static Image powerupMedkit = new Image("medkit.png");
    public static Image enemyClown = new Image("clown.png");
    public static Image powerupPhase = new Image("ghost.png");
    public static Image randomEnemy = new Image("homer.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    public static HealthRestorePowerUp healthRestorePowerUp = null;
    public static PhasePowerUp phasePowerUp = null;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
