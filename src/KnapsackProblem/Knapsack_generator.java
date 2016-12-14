package KnapsackProblem;

import acm.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DucLe on 11/28/16.
 */
public class Knapsack_generator {
    private int num_items;
    private int level;
    private int capacity;
    private HashMap<Integer, ArrayList<Integer>> items_dict;

    /**
     * The constructor for knapsack problem generator.
     *
     * @param level    level of player to generate the suitable difficulty
     * @param capacity the capacity of the knapsack
     */
    public Knapsack_generator(int level, int capacity) {
        this.level = level;
        this.capacity = capacity;
        this.num_items = generate_num_items(level);
        generate_items(num_items);
    }

    /**
     * Method to generate the suitable number of items for the knapsack problem based on difficulty
     *
     * @param level level of player
     * @return the number of items suited to difficulty level
     */
    protected int generate_num_items(int level) {
        return level * 2 + 2;
    }

    /**
     * Method to randomly generate the items values and weights for the knapsack problem
     *
     * @param num_items the number of items
     * @return a HashMap of integer as key as index of items, value is a list with first element is weight and 2nd element is value of items
     */
    protected HashMap<Integer, ArrayList<Integer>> generate_items(int num_items) {
        RandomGenerator random_generator = new RandomGenerator();
        items_dict = new HashMap<>();
        for (int i = 1; i <= num_items; i++) {
            ArrayList<Integer> newItem = new ArrayList<Integer>();
            // Control the weight of the items, not less than 1/5 of capacity but not larger than 1/2 capacity.
            newItem.add(random_generator.nextInt(capacity / 5, capacity / 2));//Weight
            newItem.add(random_generator.nextInt(20, 80));//Value
            items_dict.put(i, newItem);
        }
        return items_dict;
    }

    /**
     * Getter for number of items
     *
     * @return num_items
     */
    public int getNum_items() {
        return num_items;
    }

    /**
     * Getter for level
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter for capacity
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter for HashMap of items
     *
     * @return items_dict
     */
    public HashMap<Integer, ArrayList<Integer>> getItems_dict() {
        return items_dict;
    }
}
