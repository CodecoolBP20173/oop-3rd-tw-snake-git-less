package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    double direction;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            int wallDirection = whichWallTouched();
            //up
            if (wallDirection == 0) {
                //left-to-right
                if (direction >= 0 && direction < 90) {
                    heading = Utils.directionToVector(180 - direction, 1);
                    direction = 180 - direction;
                }
                //right-to-left
                if (direction > 270) {
                    heading = Utils.directionToVector(360 - direction + 180, 1);
                    direction = 360 - direction + 180;
                }
            }
            //right
            if (wallDirection == 90) {
                //down-to-up
                if (direction > 0 && direction < 90) {
                    heading = Utils.directionToVector(360 - direction, 1);
                    direction = 360 - direction;
                }
                //up-to-down
                if (direction >= 90 && direction < 180) {
                    heading = Utils.directionToVector(360 - direction, 1);
                    direction = 360 - direction;
                }
            }
            //down
            if (wallDirection == 180) {
                //left-to-right
                if (direction > 90 && direction <= 180) {
                    heading = Utils.directionToVector(180 - direction, 1);
                    direction = 180 - direction;
                }
                //right-to-left
                if (direction > 180 && direction < 270) {
                    heading = Utils.directionToVector(360 - direction + 180, 1);
                    direction = 360 - direction + 180;
                }
            }
            //left
            if (wallDirection == 270) {
                //down-to-up
                if (direction > 270) {
                    heading = Utils.directionToVector(360 - direction, 1);
                    direction = 360 - direction;
                }
                //up-to-down
                if (direction > 180 && direction <= 270) {
                    heading = Utils.directionToVector(360 - direction, 1);
                    direction = 360 - direction;
                }
            }

        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
