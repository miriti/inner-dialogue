/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Functions {
    
    public static Color randomColor(boolean monochrome) {
        Random rnd = new Random();
        float r, g, b;
        
        if (monochrome) {
            r = g = b = rnd.nextFloat();
        } else {
            r = rnd.nextFloat();
            g = rnd.nextFloat();
            b = rnd.nextFloat();
        }
        
        return new Color(r, g, b);
    }
    
    public static Color randomColor() {
        return randomColor(false);
    }
}
