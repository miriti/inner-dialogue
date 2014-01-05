/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import game.hud.CommandInput;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class AppKeyListener implements KeyListener {

    private static boolean keys[];

    public AppKeyListener() {
        keys = new boolean[256];
    }

    public static boolean arrowsUp() {
        return (isDown(KeyEvent.VK_UP) || isDown(KeyEvent.VK_W));
    }

    public static boolean arrowsDown() {
        return (isDown(KeyEvent.VK_DOWN) || isDown(KeyEvent.VK_S));
    }

    public static boolean arrowsLeft() {
        return (isDown(KeyEvent.VK_LEFT) || isDown(KeyEvent.VK_A));
    }

    public static boolean arrowsRight() {
        return (isDown(KeyEvent.VK_RIGHT) || isDown(KeyEvent.VK_D));
    }

    /**
     *
     * @param keyCode
     * @return
     */
    public static boolean isDown(int keyCode) {
        return keys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        StateCtrl.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        StateCtrl.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        StateCtrl.keyReleased(e);
    }
}
