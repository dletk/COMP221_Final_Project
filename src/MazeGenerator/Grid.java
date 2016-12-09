package MazeGenerator;

import acm.graphics.GCompound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by DucLe on 11/20/16.
 */
public class Grid extends GCompound {
    //    The arrays to contains the walls in vertical and horizontal directions
    private Wall[][] arrWalls_ver, arrWalls_hor;
    //    The arrays to hold all the cells of the grid
    private Cell[][] arrCells;
    //    The ArrayList of all walls
    private ArrayList<Wall> wallList;
    //    Size of each cell (length of wall), size of the grid (number of cells per edge), coordinate of the grid
    private int cellSize;
    private int size;
    private double x;
    private double y;

    /**
     * The constructor for Grid
     *
     * @param cellSize the size of each cell in grid, i.e., the length of a wall
     * @param size     the size of the the grid (number of cells per edge)
     * @param x        upper-left x-coordinate of the grid
     * @param y        upper-left y-coordinate of the grid
     */
    public Grid(int cellSize, int size, double x, double y) {
        this.cellSize = cellSize;
        this.size = size;
        this.x = x;
        this.y = y;
//      We need to have "size" row of vertical wall, each row has "size-1" walls
        arrWalls_ver = new Wall[size][size - 1];
//      We need to have size-1 row of horizontal wall, each row has size walls
        arrWalls_hor = new Wall[size - 1][size];
        arrCells = new Cell[size][size];
        wallList = new ArrayList<>();
        generateWalls();
    }

    private void generateWalls() {
//      This loop will loop through the grid row by row, so x will be changed, y will be same
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // TODO: It will be more efficient to create a wall class, extending GLine, and store information of vertical or horizontal, i and j....
                if (j < size - 1) {
                    arrWalls_ver[i][j] = new Wall(x + cellSize + cellSize * j, y + cellSize * i, x + cellSize + cellSize * j, y + cellSize * i + cellSize, i, j, false);
                    add(arrWalls_ver[i][j]);
                    wallList.add(arrWalls_ver[i][j]);
                }
                if (i < size - 1) {
                    arrWalls_hor[i][j] = new Wall(x + cellSize * j, y + cellSize + cellSize * i, x + cellSize * j + cellSize, y + cellSize + cellSize * i, i, j, true);
                    add(arrWalls_hor[i][j]);
                    wallList.add(arrWalls_hor[i][j]);
                }

                arrCells[i][j] = new Cell(x + j * cellSize, y + i * cellSize, cellSize, cellSize, i, j, new HashSet<>());
//                arrCells[i][j].setFilled(true, Color.CYAN);
                add(arrCells[i][j]);

            }
        }
    }

    public void generateMaze() {
        Collections.shuffle(wallList);
        Iterator<Wall> iter = wallList.iterator();
        Wall current_wall;
        Cell cell1, cell2;
        while (iter.hasNext()) {
            current_wall = iter.next();
            if (!current_wall.isHorizontal()) {
                cell1 = arrCells[current_wall.getRow()][current_wall.getCol()];
                cell2 = arrCells[current_wall.getRow()][current_wall.getCol() + 1];
            } else {
                cell1 = arrCells[current_wall.getRow()][current_wall.getCol()];
                cell2 = arrCells[current_wall.getRow() + 1][current_wall.getCol()];
            }
            if (!cell1.getSetContain().contains(cell2) && !cell2.getSetContain().contains(cell1)) {
//                pause(50);
                current_wall.sendToFront();
                current_wall.setColor(Color.WHITE);
                current_wall.setDeleted(true);
                joinSet(cell1, cell2);
            }
        }
        arrCells[size-1][size-1].setFilled(true, Color.CYAN);
        arrCells[0][0].setFilled(true, Color.YELLOW);
    }

    /**
     * This function will join the set of cell2 and cell1, and set all other cells in the same set with them to have the
     * same setContain also.
     */
    private void joinSet(Cell cell1, Cell cell2) {
        HashSet<Cell> current_set = cell1.getSetContain();
        current_set.addAll(cell2.getSetContain());
        Iterator<Cell> iter = current_set.iterator();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setSetContain(current_set);
        }
    }

    /**
     * Getter of the arrCell for the use of path finding
     *
     * @return arrCells
     */
    public Cell[][] getArrCells() {
        return arrCells;
    }

    public ArrayList<Wall> getWallList() {
        return wallList;
    }

    public Wall[][] getArrWalls_ver() {
        return arrWalls_ver;
    }

    public Wall[][] getArrWalls_hor() {
        return arrWalls_hor;
    }
}
