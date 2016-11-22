package MazeGenerator;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Created by DucLe on 11/21/16.
 */
public class MazeGenerator extends GraphicsProgram {

    private static final int SIZE_CELLS = 20;
    private static final int SIZE_MAZE = 20;
    private static final double UPPER_LEFT_X = 300;
    private static final double UPPER_LEFT_Y = 100;

    private Grid aMaze;

    public void init() {
        aMaze = new Grid(SIZE_CELLS, SIZE_MAZE, UPPER_LEFT_X, UPPER_LEFT_Y);
        add(aMaze);
//        Make the outer edge for the maze
        add(new GRect(UPPER_LEFT_X, UPPER_LEFT_Y, SIZE_CELLS*SIZE_MAZE, SIZE_CELLS*SIZE_MAZE));
    }

    public void run() {
        waitForClick();
        aMaze.generateMaze();
    }
}
