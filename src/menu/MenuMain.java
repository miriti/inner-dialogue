/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import app.AppMain;
import app.StateCtrl;
import engine.KeyInteractive;
import engine.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class MenuMain extends Sprite implements KeyInteractive {
    
    private static final Point logoPos = new Point(5, 35);
    private static final int shadowShift = 2;
    private Font menuFont = new Font("Tahoma", Font.BOLD, 36);
    private BackEffect backEffect = new BackEffect();
    private MenuItem[] items;
    private int selectIndex = 0;
    
    public MenuMain() {
        addChild(backEffect);
        items = new MenuItem[2];
        items[0] = new MenuItem("Start");
        items[0].setSelected(true);
        items[1] = new MenuItem("Quit");
        
        for (int i = 0; i < 2; i++) {
            items[i].setPosition((AppMain.getScreenWidth() - items[i].getWidth()) / 2, 75 + i * (int) (items[i].getHeight() * 1.7));
            addChild(items[i]);
        }
    }
    
    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
    }
    
    @Override
    public void render(Graphics surface) {
        super.render(surface);
        
        surface.setFont(menuFont);
        surface.setColor(Color.gray);
        surface.drawString(AppMain.GAME_NAME, logoPos.x + shadowShift, logoPos.y + shadowShift);
        surface.setColor(Color.white);
        surface.drawString(AppMain.GAME_NAME, logoPos.x, logoPos.y);
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            selectIndex--;
            if (selectIndex < 0) {
                selectIndex = items.length - 1;
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            selectIndex++;
            if (selectIndex > items.length - 1) {
                selectIndex = 0;
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (selectIndex) {
                case 0:
                    StateCtrl.setState(StateCtrl.STATE_GAME);
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
        
        for (int i = 0; i < items.length; i++) {
            items[i].setSelected(false);
        }
        
        items[selectIndex].setSelected(true);
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
