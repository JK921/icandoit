package dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1915 {
    public static int N;
    public static int M;
    public static char[][] map;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1915.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int min = Integer.MAX_VALUE;
                if (map[i][j] == '1') {
                    // ¿ÞÂÊ, À§, ¿ÞÂÊÀ§
                    if (0 <= j - 1 && 0 <= i - 1) {
                        min = Math.min(min, dp[i][j - 1]);
                        min = Math.min(min, dp[i - 1][j]);
                        min = Math.min(min, dp[i - 1][j - 1]);
                    }

                    if (min == Integer.MAX_VALUE) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = min + 1;
                    }

                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max * max);
        br.close();
    }
}
