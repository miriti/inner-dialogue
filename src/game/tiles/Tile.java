/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tiles;

import engine.GameDataManager;
import engine.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Michael
 */
public class Tile extends Sprite {

    public static final int TILE_SIZE = 16;
    protected Point tileSetPos = null;
    protected int wSpan = 1;
    protected int hSpan = 1;
    private static Image tileSet = null;
    private int fovVal = 0;

    public Tile() {
    }

    public void setFovVal(int val) {
        if (val > 255) {
            fovVal = 255;
        } else if (val < 0) {
            fovVal = 0;
        } else {
            fovVal = val;
        }
    }

    public void addFovVal(int val) {
        if (val > fovVal) {
            if (val > 255) {
                fovVal = 255;
            } else {
                fovVal = val;
            }
        }
    }

    public void renderTileAt(Graphics surface, int atX, int atY) {
        if (tileSet == null) {
            tileSet = GameDataManager.getInstance().loadImage("tileset.png");
        }

        if (tileSetPos == null) {
            if (spriteImage != null) {
                surface.drawImage(getImage(), atX, atY, null);
            }
        } else {
            if (fovVal > 0) {
                surface.drawImage(tileSet,
                        atX - (wSpan - 1) * Tile.TILE_SIZE,
                        atY - (hSpan - 1) * Tile.TILE_SIZE,
                        atX + TILE_SIZE * wSpan,
                        (atY - (hSpan - 1) * Tile.TILE_SIZE) + TILE_SIZE * hSpan,
                        tileSetPos.x * TILE_SIZE,
                        tileSetPos.y * TILE_SIZE,
                        tileSetPos.x * TILE_SIZE + TILE_SIZE*wSpan,
                        tileSetPos.y * TILE_SIZE + TILE_SIZE*hSpan,
                        null);
                surface.setColor(new Color(0, 0, 0, 255 - fovVal));
                surface.drawRect(atX, atY, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public static Tile colorFactory(int colr) {
        switch (colr) {
            case 0x00000000:
                return null;
            case 0xffc0c0c0:
                return new TileRock();
            case 0xff00ff00:
                return new TileGrass();
            case 0xff007F46:
                return new TileTree();
            case 0xff0000ff:
                return new TileWater();
            case 0xff000000:
                return new TileGround();
        }

        return null;
    }

    public boolean isPassible() {
        return !(this instanceof TileSolid);
    }

    public static boolean canPass(Tile t) {
        return (t == null) || (t.isPassible());
    }

    @Override
    public void render(Graphics surface) {
        super.render(surface);
    }
}