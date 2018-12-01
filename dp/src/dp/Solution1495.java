package dp;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution1495 {
    public static int N;
    public static int S;
    public static int M;
    public static int[] V;
    public static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/1495.txt"));

        N = sc.nextInt();
        S = sc.nextInt();
        M = sc.nextInt();

        V = new int[N + 1];
        dp = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            V[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], false);
        }

        // 초기값 설정
        dp[0][S] = true;

        for (int i = 1; i <= N; i++) {
            for (int v = 0; v <= M; v++) {
                if (dp[i - 1][v]) {
                    if (v + V[i] <= M) {
                        dp[i][v + V[i]] = true;
                    }
                    if (0 <= v - V[i]) {
                        dp[i][v - V[i]] = true;
                    }
                }

            }
        }

        int ans = -1;
        for (int v = 0; v <= M; v++) {
            if (dp[N][v]) {
                ans = Math.max(ans, v);
            }
        }

        System.out.println(ans);

        sc.close();
    }
}
