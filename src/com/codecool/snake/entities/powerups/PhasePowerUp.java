package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;

public class PhasePowerUp extends GameEntity implements Interactable, Animatable {
    private int age = 0;

    public PhasePowerUp(Pane pane, double X, double Y) {
        super(pane);
        setImage(Globals.powerupPhase);
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
        snakeHead.isphase = true;
        Game.powerUpLabel.setVisible(true);
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
        Globals.phasePowerUp = null;
    }

    @Override
    public String getMessage() {
        return "Now you are a ghost, spoooky!";
    }
}
