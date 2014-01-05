/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author LAPTOP
 */
public class GameDataManager {
    
    private static HashMap<String, Image> imageResources = null;
    private static final String GAME_DATA_ROOT = "/res/gameData";
    private static GameDataManager instance;
    
    public static GameDataManager getInstance() {
        if (instance == null) {
            instance = new GameDataManager();
        }
        return instance;
    }

    /**
     *
     * @param path
     * @return
     */
    public Image loadImage(String path) {
        if (imageResources == null) {
            imageResources = new HashMap<>();
        }
        
        if (imageResources.containsKey(path)) {
            return imageResources.get(path);
        } else {
            
            BufferedImage loadedImage;
            String imageFileName = GAME_DATA_ROOT + "/" + path;
            System.out.println("Loading image resource: " + imageFileName);
            try {
                
                loadedImage = ImageIO.read(getClass().getResourceAsStream(imageFileName));
                imageResources.put(path, loadedImage);
                return loadedImage;
            } catch (IOException ex) {
                System.err.printf("Failed to load image from \"%s\"", imageFileName);
                return null;
            }
        }
    }
}
