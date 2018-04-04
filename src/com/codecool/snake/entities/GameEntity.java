package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
                getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    protected int whichWallTouched() {
        /** Gives back the direction of the touched wall in int, like:
         * Top - 0
         * Right - 90
         * Bottom - 180
         * Left - 270
         * No wall touched - 404 */
        if (getX() > Globals.WINDOW_WIDTH) {
            //Right
            return 90;
        } else if (getX() < 0) {
            //Left
            return 270;
        } else if (getY() > Globals.WINDOW_HEIGHT) {
            //Down
            return 180;
        } else if (getY() < 0) {
            //Up
            return 0;
        }
        //No wall touched
        return 404;
    }
}
