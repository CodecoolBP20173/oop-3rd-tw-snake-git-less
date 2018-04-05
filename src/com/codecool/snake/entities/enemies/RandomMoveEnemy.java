package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class RandomMoveEnemy  extends Enemy implements Animatable, Interactable {
    private int age = 0;

    public RandomMoveEnemy(Pane pane, Double X, Double Y) {
        super(pane, X, Y);
        setImage(Globals.randomEnemy);
        damage = 10;
        speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    public void step() {
        Random random = new Random();
        bounceMove();
        this.age++;
        if(this.age % 250 == 0) {
            this.direction = random.nextDouble() * 360;
            heading = Utils.directionToVector(direction, speed);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        if (this.age == 3500) {
            destroy();
        }
    }

    @Override
    public void apply(SnakeHead snakeHead){
        snakeHead.changeHealth(-damage);
        destroy();
    }

    @Override
    public  String getMessage(){return "life taken away: 10";}
}
