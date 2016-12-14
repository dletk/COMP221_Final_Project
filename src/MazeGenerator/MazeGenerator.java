package MazeGenerator;

import KnapsackProblem.Knapsack_generator;
import KnapsackProblem.Knapsack_solving;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by DucLe on 11/21/16.
 */
public class MazeGenerator extends GraphicsProgram {
    // The size of cell in the maze
    private static final int SIZE_CELLS = 20;
    // The size of the maze in term of rows and columns (a square)
    private static final int SIZE_MAZE = 20;
    // The coordinate on the canvas of the upper left corner
    private static final double UPPER_LEFT_X = 300;
    private static final double UPPER_LEFT_Y = 100;

    private Grid aMaze;
    private MazeSolving_BFS solver;
    private Velocity_Capacity time_controller;
    private Knapsack_generator knapsack_problem;
    private Knapsack_solving knapsack_solving;
    private int level;
    private double userTimeTravel;

    /**
     * Init function to prepare the canvas before the game begin
     */
    public void init() {
        aMaze = new Grid(SIZE_CELLS, SIZE_MAZE, UPPER_LEFT_X, UPPER_LEFT_Y);
        add(aMaze);
//        Make the outer edge for the maze
        add(new GRect(UPPER_LEFT_X, UPPER_LEFT_Y, SIZE_CELLS * SIZE_MAZE, SIZE_CELLS * SIZE_MAZE));
    }

    // The run (main) function of GraphicsProgram
    public void run() {
        // Initialize the game with level 1
        level = 1;
        gamePlay();


    }

    private void gamePlay() {
        System.out.println("Welcome to Mazelo Riddle");
        boolean winning = true;
        while (winning) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Your level is: " + level);
            ArrayList<Cell> path = initGame();
            System.out.println(knapsack_problem.getItems_dict());
            System.out.println("Your time to escape is: " + time_controller.getTime_allowed_extra() + "ms");
            System.out.println("Please enter an item you want to add to the back and hit Return, or enter DONE to finish:");
            Scanner scanner = new Scanner(System.in);
            ArrayList<Integer> itemChoose = new ArrayList<>();
            while (true) {
                String in_value = scanner.next();
                if (in_value.toLowerCase().equals("done")) {
                    if (itemChoose.isEmpty()) {
                        System.out.println("You need to choose at least 1 item.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("You choose item #" + in_value);
                    itemChoose.add(Integer.parseInt(in_value));
                }
            }
            boolean win = checkWin(itemChoose);
            System.out.println("Click on the screen to see the result.");
            waitForClick();
            if (win) {
                coloringPathTrue(path);
                System.out.println("You won!");
                level += 1;
            } else {
                coloringPathFalse(path);
                System.out.println("You lost!");
                winning = false;
            }
            System.out.println("Your answer is: " + itemChoose.toString());
            System.out.println("The right answer is " + knapsack_solving.getSetTaken().toString());
            System.out.println("The maximum value is: " + knapsack_solving.getValue_max());
            //Click to continue to next level
            waitForClick();
            // Clear the path of the last maze
            clearPath(path);
            // Initialize a new maze
            init();
        }
    }

    /**
     * Method to init a new game play and solver
     */
    private ArrayList<Cell> initGame() {
        waitForClick();
        aMaze.generateMaze();
        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), SIZE_MAZE);

        ArrayList<Cell> path = solver.solve_BFS();

        time_controller = new Velocity_Capacity(level, path.size());
        knapsack_problem = new Knapsack_generator(level, time_controller.generateCapacity());
        knapsack_solving = new Knapsack_solving(knapsack_problem.getItems_dict(), time_controller.generateCapacity());
        return path;
    }

    /**
     * Method to color the path if the player has the right answer
     *
     * @param path the list of cells on the escaping path in order from player position to exit
     */
    private void coloringPathTrue(ArrayList<Cell> path) {
        Iterator<Cell> iter = path.iterator();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setFilled(true, Color.YELLOW);
            pause(time_controller.getTIME_PER_CELL());
        }
    }

    /**
     * Method to color the path and stop in the middle if the player has the wrong answer.
     *
     * @param path the list of cells on the escaping path in order from player position to exit
     */
    private void coloringPathFalse(ArrayList<Cell> path) {
        // Find the ratio between the time allowed and the time player needed
        double ratio = time_controller.getTime_allowed_extra() / userTimeTravel;
        // Color the suitable number of cells on the path according to the ratio of time
        int numCellsFilled = (int) Math.floor(ratio * path.size());
        Iterator<Cell> iter = path.iterator();
        while (numCellsFilled != 0) {
            Cell aCell = iter.next();
            aCell.setFilled(true, Color.YELLOW);
            pause(time_controller.getTIME_PER_CELL());
            numCellsFilled--;
        }
    }

    /**
     * Method to check whether the choice of player is the winning or losing combination.
     *
     * @param itemsChoose the list of items that user chose from the given knapsack problem
     * @return true if the player has winning move, false otherwise.
     */
    private boolean checkWin(ArrayList<Integer> itemsChoose) {
        Iterator<Integer> iter = itemsChoose.iterator();
        int weight = 0;
        int value = 0;
        while (iter.hasNext()) {
            int item = iter.next();
            weight += knapsack_problem.getItems_dict().get(item).get(0);
            value += knapsack_problem.getItems_dict().get(item).get(1);
        }
        if (weight >= (int) Math.floor(time_controller.generateCapacity()) || value != knapsack_solving.getValue_max()) {
            userTimeTravel = weight * 50 + time_controller.getMinimum_time();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to clear the escaping path of the last level
     *
     * @param path the current escaping path
     */
    private void clearPath(ArrayList<Cell> path) {
        Iterator<Cell> iter = path.iterator();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setFilled(false);
        }
    }
}
