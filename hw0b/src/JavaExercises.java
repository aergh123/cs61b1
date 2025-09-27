import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaExercises {

    /**
     * Returns an array [1, 2, 3, 4, 5, 6]
     */
    public static int[] makeDice() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        // TODO: Fill in this function.
        return arr;
    }

    /**
     * Returns the order depending on the customer.
     * If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     * If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     * In any other case, return an empty String[] of size 3.
     */
    public static String[] takeOrder(String customer) {
        List<String> cus1 = new ArrayList<>();
        String[] food1 = {"beyti", "pizza", "hamburger", "tea"};
        String[] food2 = {"sushi", "pasta", "avocado", "coffee"};
        String[] food3 = {"", "", ""};

        cus1.add("Ergun");
        cus1.add("Erik");
        if (cus1.contains(customer)) {
            if (customer.equals("Ergun")) {
                return food1;
            }
            return food2;
        }

        // TODO: Fill in this function.
        return food3;
    }

    /**
     * Returns the positive difference between the maximum element and minimum element of the given array.
     * Assumes array is nonempty.
     */
    public static int findMinMax(int[] array) {
        int a = array[0];
        int b = array[0];
        int n = array.length;
        for (int i = 0; i < n; i++) {
            if (a < array[i]) {
                a = array[i];
            } else if (b > array[i]) {
                b = array[i];
            }
        }
        return a - b;
        // TODO: Fill in this function.
    }

    /**
     * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
     * Hailstone sequence is described as:
     * - Pick a positive integer n as the start
     * - If n is even, divide n by 2
     * - If n is odd, multiply n by 3 and add 1
     * - Continue this process until n is 1
     */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        list.add(x);
        while (x != 1) {
            if (x % 2 == 0) {
                x = x / 2;
            } else {
                x = x * 3 + 1;
            }
            list.add(x);
            // TODO: Fill in this function;
        }

        return list;
    }
}