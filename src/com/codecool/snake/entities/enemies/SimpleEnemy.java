package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class SimpleEnemy extends Enemy implements Animatable, Interactable {
    private int age = 0;

    public SimpleEnemy(Pane pane, double X, double Y) {
        super(pane, X, Y);
        setImage(Globals.simpleEnemy);
        damage = 10;
        speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        bounceMove();
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        this.age++;
        if (this.age == 2500) {
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
        return "10 damage";
    }
}
