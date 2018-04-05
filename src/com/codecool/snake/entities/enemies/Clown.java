package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class Clown extends Enemy implements Animatable, Interactable {
    private int age = 0;

    public Clown(Pane pane, double X, double Y) {
        super(pane, X, Y);
        setImage(Globals.enemyClown);
        damage = 15;
        speed = 1.8f;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        double X = 0;
        double Y = 0;
        if (this.isOutOfBounds()) {
            int wall = this.whichWallTouched();
            System.out.println(wall);
            switch (wall) {
                case 0: Y = 700;
                        X = getX();
                        break;
                case 90: X = 0;
                         Y = getY();
                         break;
                case 180: Y = 0;
                          X = getX();
                          break;
                case 270: X = 1000;
                          Y = getY();
                          break;
            }
        } else {
            X = getX();
            Y = getY();
        }
        setX(X + heading.getX());
        setY(Y + heading.getY());
        this.age++;
        if (this.age == 1500) {
            destroy();
        }
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Clown attack!";
    }
}
