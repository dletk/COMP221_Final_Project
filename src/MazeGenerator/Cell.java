package MazeGenerator;

import acm.graphics.GRect;

import java.awt.*;
import java.util.HashSet;

/**
 * Created by DucLe on 11/22/16.
 */
public class Cell extends GRect {
    // Variable to mark the cell as visited, default false
    private boolean visited;
    // The row and col position of the cell on the maze
    private int row, col;
    // The set containing the cell.
    private HashSet<Cell> setContain;

    /**
     * Constructor for a cell in the maze
     *
     * @param x          x-coordinate of the top left corner
     * @param y          y-coordinate of the top left corner
     * @param width      the width of the cell in pixels
     * @param height     the height of the cell in pixels
     * @param row        the row in the maze the cell belonging to
     * @param col        the col in the maze the cell belonging to
     * @param setContain the set containing the cell for Kruskal algorithm
     */
    public Cell(double x, double y, double width, double height, int row, int col, HashSet<Cell> setContain) {
        super(x, y, width, height);
        this.row = row;
        this.col = col;
        this.setContain = setContain;
        this.setContain.add(this);
        this.visited = false;
    }

    /**
     * Set the containing set of the cell
     * @param setContain
     */
    public void setSetContain(HashSet<Cell> setContain) {
        this.setContain = setContain;
    }

    /**
     * Get the containing set of the cell
     * @return
     */
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

    /**
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     *
     * @return
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return
     */
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "visited=" + visited +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
