/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import engine.GameDataManager;
import game.commands.Command;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Player extends Mob {

    private float mapX = 0;
    private float mapY = 0;
    private static Player _instance;

    public Player() {
        this.spriteImage = GameDataManager.getInstance().loadImage("sprites/player.png");
        initAnimation(16, 16, 10);
        setAnimationRegion(0, 0);
        fieldOfView = 2;
        _instance = this;
        setSpeaking(true);
    }

    public static Player getInstance() {
        return _instance;
    }

    @Override
    public void setNextCell(Point nextCell) {
        setAnimationRegion(1, 2);
        super.setNextCell(nextCell);
    }

    @Override
    protected void onCellReached() {
        setAnimationRegion(0, 0);
        super.onCellReached();
    }

    public void move(int[] tokens, String[] words) {
        this.stepsCount = Math.max(getMap().getMapWidth(), getMap().getMapHeight());

        if (tokens[0] == Command.T_MOVE_STEP_CMD) {
            this.stepsCount = 1;
        }

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == Command.T_NUMERIC) {
                this.stepsCount = CommandInterpreter.numericToInt(words[i]);
            }
            if (tokens[i] == Command.T_MOVE_DIRECTION) {
                direction = CommandInterpreter.translateDirection(words[i]);
            }
        }
        goTo(currentCell.x + direction.x, currentCell.y + direction.y);
    }

    public float getMapX() {
        return mapX;
    }

    public void setMapX(float mapX) {
        this.mapX = mapX;
    }

    public float getMapY() {
        return mapY;
    }

    public void setMapY(float mapY) {
        this.mapY = mapY;
    }

    @Override
    public void render(Graphics surface) {
        super.render(surface);
    }
}
