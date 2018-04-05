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

public class PhasePowerUp extends GameEntity implements Interactable, Animatable {
    private int age = 0;

    public PhasePowerUp(Pane pane) {
        super(pane);
        setImage(Globals.powerupPhase);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        for (int i = 0; i < pane.getChildren().size(); i++) {
            //System.out.println(pane.getChildren().get(i));
        }
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.isphase = true;
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
