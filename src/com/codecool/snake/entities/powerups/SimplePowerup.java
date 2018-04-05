package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    public SimplePowerup(Pane pane, double X, double Y) {
        super(pane);
        setImage(Globals.burger);
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
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        //snakeHead.changeSpeed(0.1f);
        snakeHead.addPart(4);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
