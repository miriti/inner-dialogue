/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.hud;

import app.AppMain;
import engine.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class CharSaying extends Sprite {

    private static Font font = new Font("Arial", Font.PLAIN, 7);
    private int timeLeft;

    public CharSaying(String text, int time) {
        Graphics mG = AppMain.app.gameScreen.getGraphics();
        FontMetrics fM = mG.getFontMetrics(font);
        int tW = fM.stringWidth(text) * AppMain.pixelScale;
        int tH = fM.getHeight() * AppMain.pixelScale;

        this.spriteImage = new BufferedImage(tW, tH, BufferedImage.TYPE_INT_ARGB);
        Graphics tG = this.spriteImage.getGraphics();
        tG.setColor(Color.gray);
        tG.drawString(text, 6, 14);
        tG.setColor(Color.white);
        tG.drawString(text, 5, 13);

        mG.dispose();
        tG.dispose();

        timeLeft = time;
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
        timeLeft -= deltaTime;
        
        if (timeLeft <= 0) {
            deleteFlag = true;
        }
    }
}
