package MazeGenerator;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by DucLe on 11/24/16.
 */
public class MazeSolving_BFS {
    private Cell[][] arrCells;
    private int sizeMaze;
    private ArrayList<Wall> wallList;

    public MazeSolving_BFS(Cell[][] arrCells, ArrayList<Wall> wallList, int sizeMaze) {
        this.arrCells = arrCells;
        this.wallList = wallList;
        this.sizeMaze = sizeMaze;
    }

    public ArrayList<Cell> solve_BFS() {
        ArrayDeque<Cell> visited = new ArrayDeque<Cell>();
        ArrayList<Cell> path = new ArrayList<Cell>();
        visited.add(arrCells[sizeMaze-1][sizeMaze-1]);
        while (!visited.isEmpty()) {
            Cell curr_Cell = visited.poll();
            if (curr_Cell.equals(arrCells[0][0])) {
//                Return the path by calling traversing parents
            } else {
//                find neighbor that can be reached (no wall between)
            }
        }

        return path;
    }
}
