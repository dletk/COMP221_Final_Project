package KnapsackProblem;

import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by DucLe on 11/28/16.
 */
public class testKnapsack_generator {
    @Ignore
    public void testConstructor() {
        Knapsack_generator generator = new Knapsack_generator(5, 5);
        assertNotNull(generator);
    }

    @Ignore
    public void testGenerate_num_items() {
        Knapsack_generator generator = new Knapsack_generator(5,5);
        assertEquals(12, generator.generate_num_items(5));
    }

    @Ignore
    public void testGenerate_items() {
        Knapsack_generator generator = new Knapsack_generator(5,5);
        assertNotEquals(new HashMap<Integer, Integer>(), generator.generate_items(8));
        System.out.println(generator.generate_items(8));
        System.out.println(generator.getCapacity());
    }
}
