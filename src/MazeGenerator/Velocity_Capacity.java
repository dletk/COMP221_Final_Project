package MazeGenerator;

import acm.util.RandomGenerator;

/**
 * Created by DucLe on 12/8/16.
 */
public class Velocity_Capacity {
    private final int TIME_PER_CELL = 100;
    private int minimum_time;
    private int time_allowed_extra;
    private int level;

    public Velocity_Capacity(int level, int path_size) {
        this.level = level;
        minimum_time = TIME_PER_CELL*path_size;
        time_allowed_extra = new RandomGenerator().nextInt(minimum_time+5000, minimum_time+5000+level*1000);
    }

    public int generateCapacity() {
        return (time_allowed_extra-minimum_time)/50;
    }

    public int getMinimum_time() {
        return minimum_time;
    }

    public int getTime_allowed_extra() {
        return time_allowed_extra;
    }

    public int getTIME_PER_CELL() {
        return TIME_PER_CELL;
    }
}
