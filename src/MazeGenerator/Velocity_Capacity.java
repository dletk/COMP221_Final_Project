package MazeGenerator;

import acm.util.RandomGenerator;

/**
 * Created by DucLe on 12/8/16.
 */
public class Velocity_Capacity {
    // The default time to pass 1 cell without carrying any weight.
    private final int TIME_PER_CELL = 100;
    // Minimum time to escape the maze without carrying any weight.
    private int minimum_time;
    // Time allowed to escape the maze, after adding some extra seconds for the player to carry some weights.
    private int time_allowed_extra;
    private int level;

    /**
     * Constructor for velocity and capacity class
     *
     * @param level     the level of player.
     * @param path_size the length of the escaping path from the current maze.
     */
    public Velocity_Capacity(int level, int path_size) {
        this.level = level;
        minimum_time = TIME_PER_CELL * path_size;
        // Time allowed is based on level, and it is guarantee to be more than 5 seconds compare to minimum time.
        time_allowed_extra = new RandomGenerator().nextInt(minimum_time + 5000, minimum_time + 5000 + level * 1000);
    }

    /**
     * Method to generate the capacity based on time allowed
     *
     * @return the maximum weight that user can carry and escape safely.
     */
    public int generateCapacity() {
        return (time_allowed_extra - minimum_time) / 50;
    }

    /**
     * Method to get minimum time to escape from current maze
     *
     * @return minimum time to escape from current maze
     */
    public int getMinimum_time() {
        return minimum_time;
    }

    /**
     * Method to get the time allowed for user
     *
     * @return time allowed for user to escape
     */
    public int getTime_allowed_extra() {
        return time_allowed_extra;
    }

    /**
     * Method to get the default time to pass a cell
     *
     * @return default time to pass a cell
     */
    public int getTIME_PER_CELL() {
        return TIME_PER_CELL;
    }
}
