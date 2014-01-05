/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.event.KeyEvent;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public interface KeyInteractive {

    void keyPressed(KeyEvent ke);

    void keyReleased(KeyEvent ke);

    void keyTyped(KeyEvent ke);
}
