package MazeGenerator;

import acm.graphics.GLine;

/**
 * Created by DucLe on 11/23/16.
 */
public class Wall extends GLine {
    private int row, col;
    private boolean isHorizontal;
    private boolean isDeleted;

    public Wall(double x0, double y0, double x1, double y1, int row, int col, boolean isHorizontal) {
        super(x0, y0, x1, y1);
        this.col = col;
        this.row = row;
        this.isHorizontal = isHorizontal;
        this.isDeleted = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
