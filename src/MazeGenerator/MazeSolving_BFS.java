package MazeGenerator;

import java.util.*;

/**
 * Created by DucLe on 11/24/16.
 */
public class MazeSolving_BFS {
    private Cell[][] arrCells;
    private Wall[][] arrWalls_ver;
    private Wall[][] arrWalls_hor;
    private int sizeMaze;
    private HashMap<Cell, Cell> dictParents;

    public MazeSolving_BFS(Cell[][] arrCells, Wall[][] arrWalls_ver, Wall[][] arrWalls_hor, int sizeMaze) {
        this.arrCells = arrCells;
        this.arrWalls_ver = arrWalls_ver;
        this.arrWalls_hor = arrWalls_hor;
        this.sizeMaze = sizeMaze;
        this.dictParents = new HashMap<Cell, Cell>();
    }

    public ArrayList<Cell> solve_BFS() {
        ArrayDeque<Cell> seen = new ArrayDeque<Cell>();
        ArrayList<Cell> path = new ArrayList<Cell>();

        seen.add(arrCells[sizeMaze - 1][sizeMaze - 1]);
        arrCells[sizeMaze - 1][sizeMaze - 1].setVisited(true);
        while (!seen.isEmpty()) {
//            System.out.println("Signal");
            Cell curr_Cell = seen.poll();
            if (curr_Cell.equals(arrCells[0][0])) {
//                Return the path by calling traversing parents
                return backtrace(curr_Cell);
            } else {
//                find neighbor that can be reached (no wall between)
//                System.out.println(curr_Cell);
                ArrayList<Cell> neighbors = findNeighbors(curr_Cell);
//                System.out.println(neighbors);
//                System.out.println(arrWalls_ver[19][18]);
                Iterator<Cell> iter = neighbors.iterator();
                while (iter.hasNext()) {
                    Cell curr_Neighbor = iter.next();
                    if (!curr_Neighbor.isVisited() && !seen.contains(curr_Neighbor)) {
                        curr_Neighbor.setVisited(true);
                        seen.add(curr_Neighbor);
                        dictParents.put(curr_Neighbor, curr_Cell);
                    }
                }
            }
        }
        return path;
    }

    protected ArrayList<Cell> backtrace(Cell lastCell) {
        Cell currentCell = lastCell;
        ArrayList<Cell> path = new ArrayList<Cell>();
        path.add(currentCell);
        while (!currentCell.equals(arrCells[sizeMaze - 1][sizeMaze - 1])) {
            path.add(dictParents.get(currentCell));
            currentCell = dictParents.get(currentCell);
        }
        Collections.reverse(path);
        return path;
    }

    protected ArrayList<Cell> findNeighbors(Cell curr_Cell) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        if (curr_Cell.getRow() - 1 >= 0) {
            if (arrWalls_hor[curr_Cell.getRow() - 1][curr_Cell.getCol()].isDeleted()) {
                neighbors.add(arrCells[curr_Cell.getRow() - 1][curr_Cell.getCol()]);
            }
        }
        if (curr_Cell.getRow() + 1 < sizeMaze) {
            if (arrWalls_hor[curr_Cell.getRow()][curr_Cell.getCol()].isDeleted()) {
                neighbors.add(arrCells[curr_Cell.getRow() + 1][curr_Cell.getCol()]);
            }
        }
        if (curr_Cell.getCol() + 1 < sizeMaze) {
            if (arrWalls_ver[curr_Cell.getRow()][curr_Cell.getCol()].isDeleted()) {
                neighbors.add(arrCells[curr_Cell.getRow()][curr_Cell.getCol() + 1]);
            }
        }
        if (curr_Cell.getCol() - 1 >= 0) {
            if (arrWalls_ver[curr_Cell.getRow()][curr_Cell.getCol() - 1].isDeleted()) {
                neighbors.add(arrCells[curr_Cell.getRow()][curr_Cell.getCol() - 1]);
            }
        }
        return neighbors;
    }

    protected HashMap<Cell, Cell> getDictParents() {
        return dictParents;
    }
}
