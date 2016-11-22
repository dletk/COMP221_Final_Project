package MazeGenerator;

import acm.graphics.GCompound;
import acm.graphics.GLine;

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
    private GLine[][] arrLines_ver, arrLines_hor;
    //    The arrays to hold all the cells of the grid
    private Cell[][] arrCells;
    //    The ArrayList of all walls
    private ArrayList<GLine> wallList;
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
        arrLines_ver = new GLine[size][size - 1];
//      We need to have size-1 row of horizontal wall, each row has size walls
        arrLines_hor = new GLine[size - 1][size];
        arrCells = new Cell[size][size];
        wallList = new ArrayList<>();
        generateWalls();
    }

    private void generateWalls() {
//      This loop will loop through the grid row by row, so x will be changed, y will be same
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (j < size - 1) {
                    arrLines_ver[i][j] = new GLine(x + cellSize + cellSize * j, y + cellSize * i, x + cellSize + cellSize * j, y + cellSize * i + cellSize);
                    add(arrLines_ver[i][j]);
                    wallList.add(arrLines_ver[i][j]);
                }
                if (i < size - 1) {
                    arrLines_hor[i][j] = new GLine(x + cellSize * j, y + cellSize + cellSize * i, x + cellSize * j + cellSize, y + cellSize + cellSize * i);
                    add(arrLines_hor[i][j]);
                    wallList.add(arrLines_hor[i][j]);
                }

                arrCells[i][j] = new Cell(x + j * cellSize, y + i * cellSize, cellSize, cellSize, new HashSet<>());
//                arrCells[i][j].setFilled(true, Color.GREEN);
                add(arrCells[i][j]);

            }
        }
    }

    public void generateMaze() {
        Collections.shuffle(wallList);
        Iterator iter = wallList.iterator();
        GLine current_wall;
        Cell cell1, cell2;
        while (iter.hasNext()) {
            current_wall = (GLine) iter.next();
            if (checkWall(current_wall) == 1) {
                int j = (int) (current_wall.getStartPoint().getX() - x - cellSize) / cellSize;
                int i = (int) (current_wall.getStartPoint().getY() - y) / cellSize;
                cell1 = arrCells[i][j];
                cell2 = arrCells[i][j + 1];
            } else {
                int j = (int) (current_wall.getStartPoint().getX() - x) / cellSize;
                int i = (int) (current_wall.getStartPoint().getY() - y - cellSize) / cellSize;
                cell1 = arrCells[i][j];
                cell2 = arrCells[i + 1][j];
            }
            if (!cell1.getSetContain().contains(cell2) && !cell2.getSetContain().contains(cell1)) {
                pause(50);
                current_wall.sendToFront();
                current_wall.setColor(Color.WHITE);
//                HashSet<Cell> current_set = cell1.getSetContain();
//                current_set.addAll(cell2.getSetContain());
//                cell2.setSetContain(current_set);
//                cell1.setSetContain(current_set);
                joinSet(cell1, cell2);
            }
        }

//        for (int i = 0; i < arrCells.length; i++) {
//            for (int j = 0; j < arrCells.length; j++) {
//                System.out.println(arrCells[i][j].getSetContain());
//            }
//        }

//        System.out.println("Done");
//        HashSet<Cell> current = arrCells[1][2].getSetContain();
//        current.add(arrCells[2][3]);
//        current.add(arrCells[4][5]);
//        arrCells[2][3].setSetContain(current);
//        HashSet<Cell> cu2 = arrCells[2][3].getSetContain();
//        cu2.add(arrCells[3][3]);
//        System.out.println(arrCells[1][2].getSetContain());
//        System.out.println(arrCells[2][3].getSetContain());
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
     * Method to check whether a wall is vertical or horizontal
     *
     * @param aWall the input wall
     * @return 1 if the wall is vertical, 0 if the wall is horizontal
     */
    private int checkWall(GLine aWall) {
        if (aWall.getStartPoint().getX() == aWall.getEndPoint().getX()) {
            return 1;
        } else {
            return 0;
        }
    }
}
