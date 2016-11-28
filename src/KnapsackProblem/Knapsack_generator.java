package KnapsackProblem;

import acm.util.RandomGenerator;

import java.util.HashMap;

/**
 * Created by DucLe on 11/28/16.
 */
public class Knapsack_generator {
    private int num_items;
    private int level;
    private int capacity;
    private HashMap<Integer, Integer> items_dict;

    public Knapsack_generator(int level) {
        this.level = level;
        this.num_items = generate_num_items(level);
        generate_items(num_items);
    }

    private int generate_num_items(int level) {
        return level * 2 + 2;
    }

    private HashMap<Integer, Integer> generate_items(int num_items) {
        RandomGenerator random_generator = new RandomGenerator();

        capacity = random_generator.nextInt(50, 100);
        int average_Weight = capacity / (num_items - 2);
        items_dict = new HashMap<>();
        for (int i = 0; i < num_items; i++) {
            items_dict.put(1, random_generator.nextInt(1, average_Weight));
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

    public HashMap<Integer, Integer> getItems_dict() {
        return items_dict;
    }
}
