package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.HealthRestorePowerUp;
import com.codecool.snake.entities.powerups.PhasePowerUp;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private Pane pane;
    public static int snakeLength;
    public boolean isphase = false;
    private int phaseTimer = 0;

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
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
        if (!isphase){
            if (isOutOfBounds() || health <= 0) {
                System.out.println("Game Over");
                Globals.gameLoop.stop();
                JOptionPane.showMessageDialog(null, "       Game Over! \n Your length was: " + SnakeHead.snakeLength);
            }
        } else {
            phase();
            this.phaseTimer++;
            if (this.phaseTimer == 600) {
                System.out.println("phase time is over");
                isphase = false;
                phaseTimer =0;
            }

        }


        // check for game over condition

        createPowerups();
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

    public void createPowerups() {
        int randomNumber = Utils.createRandomNumber(1, 400);
        if (randomNumber == 2 && Globals.healthRestorePowerUp == null) {
            Globals.healthRestorePowerUp = new HealthRestorePowerUp(pane);
        }
        if (randomNumber == 3 && Globals.phasePowerUp == null) {
            Globals.phasePowerUp = new PhasePowerUp(pane);
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
}
