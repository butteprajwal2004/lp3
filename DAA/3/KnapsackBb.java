import java.util.*;

class Node {
    int level, profit, weight;
    double bound;

    Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }
}

public class KnapsackBb {

    // Function to calculate upper bound
    public static double bound(Node u, int n, int W, int[] wt, int[] val) {
        if (u.weight >= W) return 0;
        double profitBound = u.profit;
        int j = u.level + 1;
        int totWeight = u.weight;

        while (j < n && totWeight + wt[j] <= W) {
            totWeight += wt[j];
            profitBound += val[j];
            j++;
        }

        if (j < n) profitBound += (W - totWeight) * ((double) val[j] / wt[j]);

        return profitBound;
    }

    public static int knapsack(int W, int[] wt, int[] val, int n) {
        Queue<Node> q = new LinkedList<>();
        Node u = new Node(-1, 0, 0, 0);
        Node v = new Node(-1, 0, 0, 0);
        u.bound = bound(u, n, W, wt, val);
        q.add(u);

        int maxProfit = 0;

        while (!q.isEmpty()) {
            u = q.poll();
            if (u.bound > maxProfit) {
                // Take the next item
                v.level = u.level + 1;
                v.weight = u.weight + wt[v.level];
                v.profit = u.profit + val[v.level];

                if (v.weight <= W && v.profit > maxProfit) maxProfit = v.profit;

                v.bound = bound(v, n, W, wt, val);
                if (v.bound > maxProfit) q.add(new Node(v.level, v.profit, v.weight, v.bound));

                // Don't take the next item
                v.weight = u.weight;
                v.profit = u.profit;
                v.bound = bound(v, n, W, wt, val);
                if (v.bound > maxProfit) q.add(new Node(v.level, v.profit, v.weight, v.bound));
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter values of items:");
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) wt[i] = sc.nextInt();

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        int maxValue = knapsack(W, wt, val, n);
        System.out.println("\nMaximum value using Branch & Bound = " + maxValue);

        sc.close();
    }
}
