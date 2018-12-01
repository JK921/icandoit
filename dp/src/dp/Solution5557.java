package dp;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution5557 {
    public static int N;
    public static long[][] dp;
    public static int[] arr;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/5557.txt"));

        N = sc.nextInt();
        dp = new long[N + 1][21];
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        dp[1][arr[1]] = 1;

        for (int i = 2; i < N; i++) {
            for (int j = 0; j < 21; j++) {
                if (0 <= j + arr[i] && j + arr[i] <= 20) {
                    dp[i][j + arr[i]] += dp[i - 1][j];
                }
                if (0 <= j - arr[i] && j - arr[i] <= 20) {
                    dp[i][j - arr[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N - 1][arr[N]]);

        sc.close();
    }

}
