package KnapsackProblem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DucLe on 11/28/16.
 */
public class Knapsack_solving {
    private HashMap<Integer, ArrayList<Integer>> itemDicts;
    private int capacity;
    private int[][] knapsack_table;
    private int value_max;

    public Knapsack_solving(HashMap<Integer, ArrayList<Integer>> itemDicts, int capacity) {
        this.itemDicts = itemDicts;
        this.capacity = capacity;
        knapsack_table = new int[itemDicts.size() + 1][capacity + 1];
        value_max = solving();
    }

    protected int solving() {
        for (int row = 0; row <= itemDicts.size(); row++) {
            for (int col = 0; col <= capacity; col++) {
                if (row == 0 || col == 0) {
                    knapsack_table[row][col] = 0;
                } else {
                    int weightItem = itemDicts.get(row).get(0);
                    int valueItem = itemDicts.get(row).get(1);
                    if (col - weightItem >= 0) {
                        knapsack_table[row][col] =
                                Integer.max(knapsack_table[row - 1][col],
                                        valueItem + knapsack_table[row - 1][col - weightItem]);
                    } else {
                        knapsack_table[row][col] = knapsack_table[row-1][col];
                    }
                }
            }
        }
        return knapsack_table[itemDicts.size()][capacity];
    }

    public int[][] getKnapsack_table() {
        return knapsack_table;
    }

    public int getValue_max() {
        return value_max;
    }
}
