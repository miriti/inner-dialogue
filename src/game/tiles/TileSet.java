/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tiles;

import engine.GameDataManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class TileSet {

    private static TileSet instance = null;
    private BufferedImage tileSetImage;
    private BufferedImage[][] tileImages;
    private final int wTiles;
    private final int hTiles;

    public TileSet() {
        tileSetImage = (BufferedImage) GameDataManager.getInstance().loadImage("tileset.png");
        wTiles = tileSetImage.getWidth(null) / Tile.TILE_SIZE;
        hTiles = tileSetImage.getHeight(null) / Tile.TILE_SIZE;
    }
}
