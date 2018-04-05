package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Clown;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthRestorePowerUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.PhasePowerUp;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.util.Arrays;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 4;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private Pane pane;
    public static int snakeLength;
    public boolean isphase = false;
    private int phaseTimer = 0;
    private double coordinateX;
    private double coordinateY;
    private static Interactable[] firstSnakeBody = new Interactable[4];
    public static int bodyCounter = 0;
    public static boolean gotYourTail = false;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        this.pane = pane;
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        snakeLength = 0;

        addPart(4);
    }

    public void step() {
        this.coordinateX = getX();
        this.coordinateY = getY();
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    if (bodyCounter < 4) {
                        firstSnakeBody[bodyCounter] = interactable;
                        bodyCounter++;
                    }
                    if (!Arrays.asList(firstSnakeBody).contains(interactable)) {
                        interactable.apply(this);
                        System.out.println(interactable.getMessage());
                    }
                }
            }
        }
        if (!isphase){
            if (isOutOfBounds() || health <= 0 || gotYourTail) {
                Game.sound.stopMusic();
                System.out.println("Game Over");
                Globals.gameLoop.stop();
                JOptionPane.showMessageDialog(null, "       Game Over! \n Your length was: " + SnakeHead.snakeLength);
            }
        } else {
            phase();
            this.phaseTimer++;
            int time = ((600-phaseTimer)/60) + 1;
            Game.powerUpLabel.setText("Phase time: " + time);
            if (this.phaseTimer == 600) {
                Game.powerUpLabel.setVisible(false);
                System.out.println("phase time is over");
                isphase = false;
                phaseTimer =0;
                gotYourTail = false;
            }

        }


        // check for game over condition

        createPowerups(coordinateX, coordinateY);
        createEnemies(coordinateX, coordinateY);
    }

    public void phase(){
        if (getX() > Globals.WINDOW_WIDTH){
            setX(0);
            setY(getY());

        }

        if (getX() < 0) {
            setX(Globals.WINDOW_WIDTH);
            setY(getY());

        }
        if (getY() > Globals.WINDOW_HEIGHT){
            setX(getX());
            setY(0);


        }
        if (getY() <0) {
            setX(getX());
            setY(Globals.WINDOW_HEIGHT);

        }

    }

    public void createPowerups(double X, double Y) {
        int randomNumber = Utils.createRandomNumber(1, 1000);
        if ((randomNumber == 2 || randomNumber == 3) && Globals.healthRestorePowerUp == null) {
            Globals.healthRestorePowerUp = new HealthRestorePowerUp(pane, X, Y);
        }
        if (randomNumber > 85 && randomNumber < 90) {
            new SimplePowerup(pane, X, Y);
        }
        if (randomNumber == 3 && Globals.phasePowerUp == null) {
            Globals.phasePowerUp = new PhasePowerUp(pane, X, Y);
        }
    }

    public void createEnemies(double X, double Y) {
        int randomNumber = Utils.createRandomNumber(1, 1000);
        if (randomNumber > 37 && randomNumber < 40) {
            new Clown(pane, X, Y);
        }
        if (randomNumber == 2) {
            new SimpleEnemy(pane, X, Y);
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
            snakeLength++;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        if (health > 100) {
            health = 100;
        }
        Game.healthLabel.setText("Health: " + health);
        System.out.println(health);
    }

    public void changeSpeed(float diff) {
        speed += diff;
    }
}
