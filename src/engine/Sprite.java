package engine;

import game.tiles.Tile;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Michael
 */
public class Sprite implements Renderable {

    private boolean animated = false;
    private int frameWidth = 0;
    private int frameHeight = 0;
    private int animFps = 0;
    private int animTime = 0;
    private int currentFrame = 0;
    private int totalFrames = 0;
    private int frameMin = 0;
    private int frameMax = 0;
    private boolean playing = false;
    protected ArrayList<Sprite> children = null;
    protected float x = 0;
    protected float y = 0;
    protected float shiftX = 0;
    protected float shiftY = 0;
    protected Sprite parent;
    protected Image spriteImage = null;
    protected boolean deleteFlag = false;

    protected void setAnimationRegion(int frmStart, int frmEnd) {
        frameMin = frmStart;
        frameMax = frmEnd;
        currentFrame = frameMin;
    }

    protected void initAnimation(int newFrameWidth, int newFrameHeight, int fps) {
        if (spriteImage != null) {
            if ((spriteImage.getWidth(null) % newFrameWidth == 0) && (spriteImage.getHeight(null) % newFrameHeight == 0)) {
                frameWidth = newFrameWidth;
                frameHeight = newFrameHeight;
                frameMax = totalFrames = (spriteImage.getWidth(null) / newFrameWidth) * (spriteImage.getHeight(null) / newFrameHeight);
                frameMin = 0;
                animated = true;
                animFps = fps;
            } else {
                throw new Error("Invalid frame size");
            }
        } else {
            throw new Error("There is no image to animate");
        }
    }

    public void playAnimation() {
        playing = true;
    }

    public void stopAnimation() {
        playing = false;
        currentFrame = frameMin;
    }

    public void pauseAnimtion() {
        playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void Delete() {
        deleteFlag = true;
    }

    public boolean doDelete() {
        return deleteFlag;
    }

    public float getShiftX() {
        return shiftX;
    }

    public void setShiftX(float shiftX) {
        this.shiftX = shiftX;
    }

    public float getShiftY() {
        return shiftY;
    }

    public void setShiftY(float shiftY) {
        this.shiftY = shiftY;
    }

    public Image getImage() {
        return spriteImage;
    }

    public int getWidth() {
        if (getImage() != null) {
            return getImage().getWidth(null);
        }
        return 0;
    }

    public int getHeight() {
        if (getImage() != null) {
            return getImage().getHeight(null);
        }
        return 0;

    }

    public void setX(float x) {
        setPosition(x, this.y);
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        setPosition(this.x, y);
    }

    public float getY() {
        return y;
    }

    public float getGlobalX() {
        if (parent != null) {
            return parent.getGlobalX() + getX();
        } else {
            return getX();
        }
    }

    public float getGlobalY() {
        if (parent != null) {
            return parent.getGlobalY() + getY();
        } else {
            return getY();
        }
    }

    public void setPartent(Sprite parent) {
        this.parent = parent;
    }

    public Sprite getParent() {
        return this.parent;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public Sprite addChild(Sprite child) {
        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(child);
        child.setPartent(this);
        return child;
    }

    public Sprite removeChild(Sprite child) {
        if (children != null) {
            int ind = children.indexOf(child);
            if (ind != -1) {
                child.setPartent(null);
                children.remove(ind);
            }
        }
        return child;
    }

    @Override
    public void render(Graphics surface) {
        if (children != null) {
            for (Iterator sp = children.iterator(); sp.hasNext();) {
                Sprite nextChild = (Sprite) sp.next();
                nextChild.render(surface);
            }
        }

        if (getImage() != null) {
            int rendX = (int) getGlobalX() + (int) shiftX;
            int rendY = (int) getGlobalY() + (int) shiftY;
            if (animated) {
                int frmX = currentFrame;
                int frmY = 0;
                surface.drawImage(getImage(), rendX, rendY, rendX + frameWidth, rendY + frameHeight, frmX * frameWidth, frmY * frameHeight, frmX * frameWidth + frameWidth, frmY * frameHeight + frameHeight, null);
            } else {
                surface.drawImage(getImage(), rendX, rendY, null);
            }
        }
    }

    @Override
    public void update(int deltaTime) {
        if (animated) {
            if (animTime >= (1000 / animFps)) {
                animTime = 0;
                currentFrame++;
                if (currentFrame > frameMax) {
                    currentFrame = frameMin;
                }
            } else {
                animTime += deltaTime;
            }
        }
        if (children != null) {
            for (int i = children.size() - 1; i >= 0; i--) {
                Sprite c = children.get(i);
                if (c.doDelete()) {
                    children.remove(c);
                } else {
                    c.update(deltaTime);
                }
            }
        }
    }
}
