package MazeGenerator;

import acm.graphics.GRect;

import java.awt.*;
import java.util.HashSet;

/**
 * Created by DucLe on 11/22/16.
 */
public class Cell extends GRect {
    private boolean visited;
    private Color color;
    private HashSet<Cell> setContain;

    public Cell(double x, double y, double width, double height, HashSet<Cell> setContain) {
        super(x, y, width, height);
        this.setContain = setContain;
        this.setContain.add(this);
    }

    public void setSetContain(HashSet<Cell> setContain) {
        this.setContain = setContain;
    }

    public HashSet<Cell> getSetContain() {
        return setContain;
    }

    /**
     * The method to fill the cell with the given color
     * @param fill set to "true" for filling the cell
     * @param color the filled color
     */
    public void setFilled(boolean fill, Color color) {
        super.setFillColor(color);
        super.setFilled(fill);
    }

}
