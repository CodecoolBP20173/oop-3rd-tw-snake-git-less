package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Random;

public class HealthRestorePowerUp extends GameEntity implements Interactable, Animatable {
    private int age = 0;

    public HealthRestorePowerUp(Pane pane, double X, double Y) {
        super(pane);
        setImage(Globals.powerupMedkit);
        pane.getChildren().add(this);
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

    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(100);
        destroy();
    }

    @Override
    public void step() {
        this.age++;
        if (this.age == 900) {
            destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        Globals.healthRestorePowerUp = null;
    }

    @Override
    public String getMessage() {
        return "Now you are healthy as fuck!";
    }
}
