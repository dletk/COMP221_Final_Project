package MazeGenerator;

import MazeGenerator.Cell;
import MazeGenerator.Grid;
import MazeGenerator.MazeSolving_BFS;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by DucLe on 11/25/16.
 */
public class testMazeSolving_BFS {
    private Grid aMaze = new Grid(20,30,50,50);
    private MazeSolving_BFS solver;

    @Test
    public void testConstructor() {
        assertNotNull(new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), 30));
    }

    @Test
    public void testFindNeighbors() {
        aMaze.generateMaze();
        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), 30);
        ArrayList<Cell> neighbor = new ArrayList<>();
        neighbor.add(aMaze.getArrCells()[2][3]);
        neighbor.add(aMaze.getArrCells()[4][3]);
        neighbor.add(aMaze.getArrCells()[3][4]);
        neighbor.add(aMaze.getArrCells()[3][2]);
//        To run this test, comment out the timing pause(50) in generateMaze first
        assertNotNull(solver.findNeighbors(aMaze.getArrCells()[3][3]));
        System.out.println(neighbor);
    }

    @Test
    public void testDictParent() {
        aMaze.generateMaze();
        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), 30);
        assertNotEquals("dictParents empty", new HashMap<Cell, Cell>(), solver.getDictParents());
        System.out.println(solver.getDictParents());
    }
}
