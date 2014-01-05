package engine;

import java.awt.Graphics;

/**
 *
 * @author Michael
 */
public interface Renderable {

    void update(int deltaTime);

    void render(Graphics surface);
}
