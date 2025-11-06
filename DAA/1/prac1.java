import java.util.Scanner;

public class prac1 {


    static int fibonacciIterative(int n) {
        if (n <= 0) {
            System.out.println("Invalid input");
            return -1;
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }

        int first = 0, second = 1, next = 0;
        for (int i = 3; i <= n; i++) {
            next = first + second;
            first = second;
            second = next;
        }
        return next;
    }

    
    static int fibonacciRecursive(int n) {
        if (n <= 0) {
            return -1; 
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value of n (nth Fibonacci number): ");
        int n = sc.nextInt();

        long startTimeIterative = System.nanoTime();
        System.out.println("Fibonacci Number (Iterative): " + fibonacciIterative(n));
        long endTimeIterative = System.nanoTime();
        System.out.println("Iterative Execution Time: " + (endTimeIterative - startTimeIterative) + " ns");

        long startTimeRecursive = System.nanoTime();
        System.out.println("Fibonacci Number (Recursive): " + fibonacciRecursive(n));
        long endTimeRecursive = System.nanoTime();
        System.out.println("Recursive Execution Time: " + (endTimeRecursive - startTimeRecursive) + " ns");

        sc.close();
    }
}
