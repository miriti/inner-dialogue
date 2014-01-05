package game;

import engine.Sprite;
import game.hud.CharSaying;
import game.tiles.Tile;
import java.awt.Point;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Mob extends Sprite {

    private TileMap map;
    private CharSaying currentSaying = null;
    protected Point nextCell = null;
    protected Point currentCell = null;
    protected Point direction = null;
    protected boolean moveTillSolid = false;
    protected int stepsCount = 9999;
    protected int fieldOfView = 4;
    private int moveTimeTotal;
    private int moveTimePassed;
    private boolean speaking = false;

    public Mob() {
    }

    public TileMap getMap() {
        return map;
    }
    
    
    public Point getNextCell() {
        return nextCell;
    }

    public void setNextCell(Point nextCell) {
        this.nextCell = nextCell;
    }

    public Point getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Point currentCell) {
        this.currentCell = currentCell;
        setPosition(currentCell.x * Tile.TILE_SIZE, currentCell.y * Tile.TILE_SIZE);
    }

    public boolean isSpeaking() {
        return speaking;
    }

    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    /**
     * Cell reached
     */
    protected void onCellReached() {
        if (nextCell != null) {
            setCurrentCell((Point) nextCell.clone());
            nextCell = null;

            stepsCount--;
            if (stepsCount > 0) {
                goTo(currentCell.x + direction.x, currentCell.y + direction.y);
            }
        }

        if (this instanceof Player) {
            for (int i = currentCell.x - fieldOfView; i < currentCell.x + fieldOfView; i++) {
                for (int j = currentCell.y - fieldOfView; j < currentCell.y + fieldOfView; j++) {
                    if ((i >= 0) && (i <= map.getMapWidth()) && (j >= 0) && (j < map.getMapHeight())) {
                        Tile t = map.getCell(i, j);
                        if (t != null) {
                            int d = (int) Math.round(Point.distance(i, j, currentCell.x, currentCell.y));
                            t.addFovVal(255 * (5 - d));
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param toX
     * @param toY
     * @param time
     */
    public void goTo(int toX, int toY, int time) {
        if (nextCell == null) {
            if (Tile.canPass(map.getCell(toX, toY))) {
                setNextCell(new Point(toX, toY));
                moveTimePassed = 0;
                moveTimeTotal = time;
            } else {
                say("I shall not pass");
            }
        }
    }

    /**
     *
     * @param toX
     * @param toY
     */
    public void goTo(int toX, int toY) {
        goTo(toX, toY, 500);
    }

    /**
     * Char say something
     *
     * @param text
     */
    public void say(String text) {
        if (speaking) {
            if (currentSaying != null) {
                currentSaying.Delete();
            }

            currentSaying = new CharSaying(text, text.length() * 75);
            parent.addChild(currentSaying);
        }
    }

    @Override
    public void setPosition(float newX, float newY) {
        super.setPosition(newX, newY);
    }

    /**
     *
     * @param deltaTime
     */
    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);

        if (nextCell != null) {
            if (moveTimePassed >= moveTimeTotal) {
                onCellReached();
            } else {
                float t = (float) moveTimePassed / (float) moveTimeTotal;
                int absCurrentX = currentCell.x * Tile.TILE_SIZE;
                int absCurrentY = currentCell.y * Tile.TILE_SIZE;
                int absNextX = nextCell.x * Tile.TILE_SIZE;
                int absNextY = nextCell.y * Tile.TILE_SIZE;

                setPosition(absCurrentX + (absNextX - absCurrentX) * t, absCurrentY + (absNextY - absCurrentY) * t);
                moveTimePassed += deltaTime;
            }
        }

        if ((currentSaying != null) && (!currentSaying.doDelete())) {
            currentSaying.setPosition(getX() - currentSaying.getWidth() / 2, getY() - currentSaying.getHeight());
        }
    }

    /**
     * Set tile map
     *
     * @param map
     */
    public void setMap(TileMap map) {
        this.map = map;
        if (currentCell == null) {
            currentCell = new Point(Math.round(getX() / Tile.TILE_SIZE), Math.round(getY() / Tile.TILE_SIZE));
        }
        onCellReached();
    }
}
