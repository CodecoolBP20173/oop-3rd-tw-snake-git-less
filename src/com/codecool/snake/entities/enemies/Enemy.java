package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {

    protected Point2D heading;
    protected int damage;
    protected double direction;
    protected float speed;

    public Enemy(Pane pane, double X, double Y) {
        super(pane);
        Random rnd = new Random();
        double coordinateX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double coordinateY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        if (coordinateX >= X -50 && coordinateX <= X + 50) {
            coordinateX = GameEntity.adjustCoordinate(coordinateX, true);
        }
        if (coordinateY >= Y -50 && coordinateY <= Y + 50) {
            coordinateY = GameEntity.adjustCoordinate(coordinateY, false);
        }
        setX(coordinateX);
        setY(coordinateY);
        pane.getChildren().add(this);
        this.direction = rnd.nextDouble() * 360;
        setRotate(this.direction);
    }

}
