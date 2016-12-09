package KnapsackProblem;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by DucLe on 12/8/16.
 */
public class testKnapsack_solving {
    @Test
    public void testConstructor() {
        assertNotNull(new Knapsack_solving(new HashMap<Integer, ArrayList<Integer>>(), 10));
    }

    @Test
    public void testSolving() {
        Knapsack_generator problem = new Knapsack_generator(5,5);
        Knapsack_solving solver = new Knapsack_solving(problem.getItems_dict(), problem.getCapacity());
        System.out.println(solver.getValue_max());
        System.out.println(problem.getCapacity());
        System.out.print(problem.getItems_dict());
    }
}
