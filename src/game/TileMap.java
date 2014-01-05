/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import app.AppMain;
import engine.GameDataManager;
import engine.Sprite;
import game.tiles.Tile;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Michael
 */
public class TileMap extends Sprite {
    
    private Tile[][] map;
    private final int mapWidth;
    private final int mapHeight;
    private final Player player;
    
    public TileMap() {
        this("map.png");
    }
    
    public Tile getCell(int atX, int atY) {
        if ((atX >= 0) && (atX < mapWidth) && (atY >= 0) && (atY < mapHeight)) {
            return map[atX][atY];
        } else {
            return null;
        }
    }
    
    public Tile[][] getMap() {
        return map;
    }
    
    public int getMapWidth() {
        return mapWidth;
    }
    
    public int getMapHeight() {
        return mapHeight;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public TileMap(String mapFile) {
        BufferedImage dataImage = (BufferedImage) GameDataManager.getInstance().loadImage("maps/" + mapFile);
        mapWidth = dataImage.getWidth(null);
        mapHeight = dataImage.getHeight(null);
        map = new Tile[mapWidth][mapHeight];
        
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                map[i][j] = Tile.colorFactory(dataImage.getRGB(i, j));
            }
        }
        
        player = new Player();
        player.setCurrentCell(new Point(2, 2));
        player.setMap(this);
        addChild(player);
    }
    
    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
        setPosition(AppMain.getScreenWidth() / 2 - player.getX(), AppMain.getScreenHeight() / 2 - player.getY());
    }
    
    @Override
    public void render(Graphics surface) {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (map[i][j] != null) {
                    int atX = (int) this.getX() + (i * Tile.TILE_SIZE);
                    int atY = (int) this.getY() + (j * Tile.TILE_SIZE);
                    map[i][j].renderTileAt(surface, atX, atY);
                }
            }
        }
        
        super.render(surface);
    }
}
