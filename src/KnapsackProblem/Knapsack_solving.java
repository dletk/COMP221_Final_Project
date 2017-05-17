package KnapsackProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by DucLe on 11/28/16.
 */
public class Knapsack_solving {
    private HashMap<Integer, ArrayList<Integer>> itemDicts;
    private int capacity;
    private int[][] knapsack_table;
    private int value_max;
    private HashSet<Integer> setTaken;

    /**
     * The constructor for the knapsack solver. Given a HashMap of items with weights and values, and a capacity, this
     * class generate the knapsack table by dynamic programming and find the optimal solution
     *
     * @param itemDicts HashMap of items
     * @param capacity  the capacity of the knapsack problem
     */
    public Knapsack_solving(HashMap<Integer, ArrayList<Integer>> itemDicts, int capacity) {
        this.itemDicts = itemDicts;
        this.capacity = capacity;
        setTaken = new HashSet<>();
        knapsack_table = new int[itemDicts.size() + 1][capacity + 1];
        value_max = solving();
    }

    /**
     * Method to solve the knapsack problem with the given items and capacity.
     * @return the maximum value can be chosen from the set of items
     */
    protected int solving() {
        // Using dynamic programming to construct the knapsack_table value
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
                        knapsack_table[row][col] = knapsack_table[row - 1][col];
                    }
                }
            }
        }
        // From the knapsack table, back trace to find which items were chosen
        backtrace();
        return knapsack_table[itemDicts.size()][capacity];
    }

    /**
     * Getter for the knapsack table
     * @return knapsack_table
     */
    public int[][] getKnapsack_table() {
        return knapsack_table;
    }

    /**
     * Get the maximum value answer of the knapsack problem
     *
     * @return value_max
     */
    public int getValue_max() {
        return value_max;
    }

    /**
     * Get the set of items chosen in the solution for knapsack problem.
     *
     * @return setTaken
     */
    public HashSet<Integer> getSetTaken() {
        return setTaken;
    }

    /**
     * Method to backtrace the knapsack_table and set the value for set of items taken
     */
    private void backtrace() {
        int row = itemDicts.size();
        int col = capacity;
        while (row >= 0 && col >= 0) {
            // Check to make sure we are not out of index
            if (col - itemDicts.get(row).get(0) >= 0) {
                // Check if the value at the current position include the value of current items, if yes, then the current item was chosen
                if (knapsack_table[row][col] == knapsack_table[row - 1][col - itemDicts.get(row).get(0)] + itemDicts.get(row).get(1)) {
                    setTaken.add(row);
                    col -= itemDicts.get(row).get(0);
                }
                row -= 1;
            } else {
                break;
            }
        }
    }
}
