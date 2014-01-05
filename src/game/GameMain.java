/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import app.AppMain;
import engine.KeyInteractive;
import engine.Sprite;
import game.hud.CommandInput;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Michael
 */
public class GameMain extends Sprite implements KeyInteractive {

    private static GameMain _instance = null;
    private TileMap _tileMap = null;
    private CommandInput cmdInput = new CommandInput();

    public GameMain() {
        addChild(getTileMap());
        addChild(cmdInput);
        cmdInput.setX(AppMain.getScreenWidth() / 2);
        cmdInput.setY(AppMain.getScreenHeight());
    }

    public TileMap getTileMap() {
        if (_tileMap == null) {
            _tileMap = new TileMap();
        }

        return _tileMap;
    }

    /**
     * Singleton
     *
     * @return
     */
    public static GameMain getInstance() {
        if (_instance == null) {
            _instance = new GameMain();
        }

        return _instance;
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics surface) {
        super.render(surface);
    }
    

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        cmdInput.inputChar(ke.getKeyChar());
    }
}
