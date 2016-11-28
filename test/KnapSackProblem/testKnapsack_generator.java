package KnapSackProblem;

import KnapsackProblem.Knapsack_generator;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by DucLe on 11/28/16.
 */
public class testKnapsack_generator {
    @Test
    public void testConstructor() {
        Knapsack_generator generator = new Knapsack_generator(5);
        assertNotNull(generator);
    }
}
