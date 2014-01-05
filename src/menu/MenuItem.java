/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

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
public class MenuItem extends Sprite {

    private String title;
    private final Font font = new Font("Tahoma", Font.BOLD, 24);
    private boolean selected;

    public MenuItem(String itemTitle) {
        title = itemTitle;
        Graphics g = AppMain.app.getGraphics();
        FontMetrics fm = g.getFontMetrics(font);

        spriteImage = new BufferedImage(fm.stringWidth(title), fm.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics surface) {
        super.render(surface);

        Graphics g = spriteImage.getGraphics();
        g.setFont(font);
        g.setColor(Color.gray);
        g.drawString(title, 1, 21);
        if (isSelected()) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.white);
        }
        g.drawString(title, 0, 20);
    }
}
