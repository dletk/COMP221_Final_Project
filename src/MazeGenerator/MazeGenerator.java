package MazeGenerator;

import KnapsackProblem.Knapsack_generator;
import KnapsackProblem.Knapsack_solving;
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
    private Velocity_Capacity time_controller;
    private Knapsack_generator knapsack_problem;
    private Knapsack_solving knapsack_solving;

    public void init() {
        aMaze = new Grid(SIZE_CELLS, SIZE_MAZE, UPPER_LEFT_X, UPPER_LEFT_Y);
        add(aMaze);
//        Make the outer edge for the maze
        add(new GRect(UPPER_LEFT_X, UPPER_LEFT_Y, SIZE_CELLS * SIZE_MAZE, SIZE_CELLS * SIZE_MAZE));
    }

    public void run() {
        waitForClick();
        aMaze.generateMaze();
        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), SIZE_MAZE);
        ArrayList<Cell> path = solver.solve_BFS();
        Iterator<Cell> iter = path.iterator();
        time_controller = new Velocity_Capacity(2, path.size());
        knapsack_problem = new Knapsack_generator(2, time_controller.generateCapacity());
        knapsack_solving = new Knapsack_solving(knapsack_problem.getItems_dict(), time_controller.generateCapacity());
        System.out.println(knapsack_problem.getItems_dict());
        System.out.println(time_controller.getTime_allowed_extra());
        waitForClick();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setFilled(true, Color.YELLOW);
            pause(time_controller.getTIME_PER_CELL());
        }
        System.out.println(knapsack_solving.getValue_max());
        System.out.println("Done");
//        System.out.println(path.size());
    }
}
