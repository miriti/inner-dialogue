package app;

import engine.KeyInteractive;
import engine.Renderable;
import game.GameMain;
import java.awt.event.KeyEvent;
import menu.MenuMain;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class StateCtrl {

    public static final int STATE_MENU = 0;
    public static final int STATE_GAME = 1;
    private static GameMain gameState = new GameMain();
    private static MenuMain menuState = new MenuMain();
    private static Renderable currentState;

    public static Renderable getCurrentState() {
        return currentState;
    }

    public static void keyPressed(KeyEvent ke) {
        if (currentState instanceof KeyInteractive) {
            ((KeyInteractive) currentState).keyPressed(ke);
        }
    }

    public static void keyReleased(KeyEvent ke) {
        if (currentState instanceof KeyInteractive) {
            ((KeyInteractive) currentState).keyReleased(ke);
        }
    }

    public static void keyTyped(KeyEvent ke) {
        if (currentState instanceof KeyInteractive) {
            ((KeyInteractive) currentState).keyTyped(ke);
        }
    }

    public static void setState(int State) {
        switch (State) {
            case STATE_GAME:
                currentState = gameState;
                break;
            case STATE_MENU:
                currentState = menuState;
                break;
            default:
                throw new Error("Unknown state");
        }
    }
}
