/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import app.AppMain;
import engine.Sprite;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class BackEffect extends Sprite {

    private static final int BLOCK_SIZE = 20;
    private static Color backColor = new Color(0x222222);
    private float blockShiftX = 0;
    private float blockShiftY = 0;

    @Override
    public void render(Graphics surface) {
        super.render(surface);

        surface.setColor(backColor);
        for (int i = 0; i <= AppMain.getScreenWidth() / BLOCK_SIZE; i++) {
            for (int j = 0; j < AppMain.getScreenHeight() / BLOCK_SIZE; j++) {
                if ((i % 2 == 0) ^ (j % 2 == 0)) {
                    surface.fillRect((int) ((float) (i * BLOCK_SIZE) + blockShiftX), (int) ((float) (j * BLOCK_SIZE) + blockShiftY), BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
    }
}
