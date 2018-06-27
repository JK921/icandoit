import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    static int N;
    static int MAX;
    static int[] W;
    static int[] V;

    static int[][] memo;

    static int ks(int n, int w) {
        if (n == N || w == 0) {
            return 0;
        }

        if (memo[n][w] >= 0) {
            return memo[n][w];
        }

        int selected = 0;
        if (W[n] <= w) {
            selected = V[n] + ks(n + 1, w - W[n]);
        }
        int unselected = ks(n + 1, w);
        memo[n][w] = Math.max(selected, unselected);

        return memo[n][w];
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/knapsack.txt"));

        N = sc.nextInt();
        MAX = sc.nextInt();
        W = new int[N];
        V = new int[N];
        memo = new int[N][MAX + 1];

        for (int i = 0; i < N; i++) {
            W[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            V[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        int result = ks(0, MAX);
        System.out.println(result);

        sc.close();
    }
}
