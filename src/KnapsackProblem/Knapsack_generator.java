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

    public Knapsack_generator(int level) {
        this.level = level;
        this.num_items = generate_num_items(level);
        generate_items(num_items);
    }

    protected int generate_num_items(int level) {
        return level * 2 + 2;
    }

    protected HashMap<Integer, ArrayList<Integer>> generate_items(int num_items) {
        RandomGenerator random_generator = new RandomGenerator();

        capacity = random_generator.nextInt(50, 100);
        items_dict = new HashMap<>();
        for (int i = 0; i < num_items; i++) {
            ArrayList<Integer> newItem = new ArrayList<Integer>();
            newItem.add(random_generator.nextInt(capacity / 5, capacity / 2));
            newItem.add(random_generator.nextInt(20, 80));
            items_dict.put(i + 1, newItem);
        }
        return items_dict;
    }

    public int getNum_items() {
        return num_items;
    }

    public int getLevel() {
        return level;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<Integer, ArrayList<Integer>> getItems_dict() {
        return items_dict;
    }
}
