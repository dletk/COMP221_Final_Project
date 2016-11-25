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
    private int row, col;
    private HashSet<Cell> setContain;

    public Cell(double x, double y, double width, double height, int row, int col, HashSet<Cell> setContain) {
        super(x, y, width, height);
        this.row = row;
        this.col = col;
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
     *
     * @param fill  set to "true" for filling the cell
     * @param color the filled color
     */
    public void setFilled(boolean fill, Color color) {
        super.setFillColor(color);
        super.setFilled(fill);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
