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

    private static final int SIZE_CELLS = 20;
    private static final int SIZE_MAZE = 20;
    private static final double UPPER_LEFT_X = 300;
    private static final double UPPER_LEFT_Y = 100;

    private Grid aMaze;
    private MazeSolving_BFS solver;
    private Velocity_Capacity time_controller;
    private Knapsack_generator knapsack_problem;
    private Knapsack_solving knapsack_solving;
    private int level;

    public void init() {
        aMaze = new Grid(SIZE_CELLS, SIZE_MAZE, UPPER_LEFT_X, UPPER_LEFT_Y);
        add(aMaze);
//        Make the outer edge for the maze
        add(new GRect(UPPER_LEFT_X, UPPER_LEFT_Y, SIZE_CELLS * SIZE_MAZE, SIZE_CELLS * SIZE_MAZE));
    }

    public void run() {
//        waitForClick();
//        aMaze.generateMaze();
//        solver = new MazeSolving_BFS(aMaze.getArrCells(), aMaze.getArrWalls_ver(), aMaze.getArrWalls_hor(), SIZE_MAZE);
        level = 1;
        gamePlay();


//        System.out.println(path.size());
    }

    private void gamePlay() {
        System.out.println("Welcome to Mazelo Riddle");
        System.out.println("Your level is: " + level);
        boolean winning = true;
        while (winning) {
            ArrayList<Cell> path = initGame();
            Iterator<Cell> iter = path.iterator();

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
                    System.out.println("You choose item #"+in_value);
                    itemChoose.add(Integer.parseInt(in_value));
                }
            }
            boolean win = checkWin(itemChoose, path);
//            int numStar = checkStars();
            System.out.println("Click on the screen to see the result.");
            waitForClick();
            while (iter.hasNext()) {
                Cell aCell = iter.next();
                aCell.setFilled(true, Color.YELLOW);
                pause(time_controller.getTIME_PER_CELL());
            }
            if (win) {
                System.out.println("You won!");
                level += 1;
            } else {
                System.out.println("You lost!");
            }
            System.out.println(knapsack_solving.getValue_max());
            clearPath(path);
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

    private boolean checkWin(ArrayList<Integer> itemsChoose, ArrayList<Cell> path) {
        Iterator<Integer> iter = itemsChoose.iterator();
        int weight = 0;
        int value = 0;
        while (iter.hasNext()) {
            int item = iter.next();
            weight += knapsack_problem.getItems_dict().get(item).get(0);
            value += knapsack_problem.getItems_dict().get(item).get(1);
        }
        if (weight * 50 + time_controller.getMinimum_time() > time_controller.getTime_allowed_extra()) {
            return false;
        } else {
            return true;
        }
    }

    private void checkStars() {

    }

    private void clearPath(ArrayList<Cell> path) {
        Iterator<Cell> iter = path.iterator();
        while (iter.hasNext()) {
            Cell aCell = iter.next();
            aCell.setFilled(false);
        }
    }

}
