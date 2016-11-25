package MazeGenerator;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by DucLe on 11/21/16.
 */
public class MazeGenerator extends GraphicsProgram {

    private static final int SIZE_CELLS = 20;
    private static final int SIZE_MAZE = 20;
    private static final double UPPER_LEFT_X = 300;
    private static final double UPPER_LEFT_Y = 100;

    private Grid aMaze;
    private MazeSolving_BFS solver;

    public void init() {
        aMaze = new Grid(SIZE_CELLS, SIZE_MAZE, UPPER_LEFT_X, UPPER_LEFT_Y);
        add(aMaze);
//        Make the outer edge for the maze
        add(new GRect(UPPER_LEFT_X, UPPER_LEFT_Y, SIZE_CELLS*SIZE_MAZE, SIZE_CELLS*SIZE_MAZE));
    }

    public void run() {
        waitForClick();
        aMaze.generateMaze();
        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), SIZE_MAZE);
        ArrayList<Cell> path = solver.solve_BFS();
//        System.out.println(solver.solve_BFS());
        Iterator<Cell> iter = path.iterator();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setFilled(true, Color.YELLOW);
            pause(50);
        }
        System.out.println("Done");
    }
}
