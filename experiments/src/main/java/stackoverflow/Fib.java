package stackoverflow;

/**
 * @author v.chibrikov
 */
public class Fib {
    public static void main(String[] args) {
        Fib fib = new Fib();
    }

    public Fib() {
        int end = 9;
        long[] nums = new long[2];
        printFib(0, end, nums);
    }

    private void printFib(int i, int end, long[] nums) {
        while (i < end) {
            if (i == 0 || i == 1) {
                nums[i] = 1;
                System.out.println("1");
            } else {
                long fib;
                fib = 0;
                fib += (nums[0] + nums[1]);
                nums[0] = nums[1];
                nums[1] = fib;
                System.out.println(fib);

            }
            i++;
            printFib(i, end, nums);
        }
    }
}



