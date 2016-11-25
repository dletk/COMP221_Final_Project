package MazeGenerator;

import acm.graphics.GLine;

/**
 * Created by DucLe on 11/23/16.
 */
public class Wall extends GLine {
    private int row, col;
    private boolean isHorizontal;
    private boolean isDeleted;

    /**
     * Constructor for a new Wall
     *
     * @param x0           x0 coordinate
     * @param y0           y0 coordinate
     * @param x1           ending x coordinate
     * @param y1           ending y cooridnate
     * @param row          row in maze
     * @param col          col in mze
     * @param isHorizontal true if the wall is horizontal, false if vertical
     */
    public Wall(double x0, double y0, double x1, double y1, int row, int col, boolean isHorizontal) {
        super(x0, y0, x1, y1);
        this.col = col;
        this.row = row;
        this.isHorizontal = isHorizontal;
        this.isDeleted = false;
    }

    /**
     * Getter for row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for column
     *
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter for isHorizontal
     *
     * @return true if wall is horizontal, false if vertical
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Getter for whether the wall is deleted (not in the maze)
     *
     * @return true if the wall is deleted, false if it is not
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Set the wall the be deleted
     *
     * @param deleted true to set the wall deleted.
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "row=" + row +
                ", col=" + col +
                ", isHorizontal=" + isHorizontal +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
