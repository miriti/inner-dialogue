/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.hud;

import app.AppMain;
import engine.Functions;
import engine.Sprite;
import game.CommandInterpreter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class CommandInput extends Sprite {

    private final static int WND_WIDTH = 200;
    private final static int WND_HEIGHT = 50;
    private String inputString = "";
    private final Font font = new Font("Tahoma", Font.BOLD, 10);
    private static CommandInput _instance;
    private Color clearColor = new Color(0, 0, 0, 255);
    private Color fontColor = new Color(255, 0, 0, 255);

    public CommandInput() {
        this.spriteImage = new BufferedImage(WND_WIDTH, WND_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        setShiftX(-WND_WIDTH / 2);
        setShiftY(-WND_HEIGHT);
        _instance = this;
    }

    public void inputChar(char c) {
        if ((int) c == 8) {
            if (inputString.length() > 0) {
                inputString = inputString.substring(0, inputString.length() - 1);
            }
        } else {
            if ((int) c == 10) {
                if (inputString.length() > 0) {
                    CommandInterpreter.exec(inputString);
                    inputString = "";
                }
            } else {
                inputString += c;
            }
        }
    }

    @Override
    public void render(Graphics surface) {
        Graphics g = this.spriteImage.getGraphics();

        g.setColor(clearColor);
        g.fillRect(0, 0, WND_WIDTH, WND_HEIGHT);
        g.setColor(Color.white);
        g.drawRect(0, 0, WND_WIDTH - 1, WND_HEIGHT - 1);
        g.setColor(fontColor);
        g.setFont(font);

        String strToDraw = inputString;
        if (AppMain.getBlinkState(300)) {
            strToDraw += "_";
        }
        g.drawString(strToDraw, 3, 10);

        g.dispose();

        super.render(surface);
    }

    public static CommandInput getInstance() {
        if (_instance == null) {
            _instance = new CommandInput();
        }

        return _instance;
    }
}
