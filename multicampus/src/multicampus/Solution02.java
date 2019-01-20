package multicampus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N x M 의 직사각형이 주어지며, 각 칸에는 정수가 들어있다. 이제 Q개의 질문에 대하여 답을 해야 하며, 각각의 질문은 (a, b)부터
 * (c, d)까지의 직사각형에 들어있는 정수의 합을 묻는다. 예를 들어, 다음과 같이 5 x 5 의 직사각형이 주어질 때, (1, 2) 부터
 * (3, 3) 까지의 직사각형에 들어있는 정수의 합은 26 이다.
 */
public class Solution02 {
    public static int N;
    public static int M;
    public static int Q;
    public static int[][] map;

    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/02.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                // calc dp
                int sum = Integer.parseInt(st.nextToken());
                if (n - 1 >= 0 && m - 1 >= 0) {
                    sum += dp[n - 1][m];
                    sum += dp[n][m - 1];
                    sum -= dp[n - 1][m - 1];
                } else if (n - 1 >= 0) {
                    sum += dp[n - 1][m];
                } else if (m - 1 >= 0) {
                    sum += dp[n][m - 1];
                }
                dp[n][m] = sum;
            }
        }

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int ans = dp[c][d];

            if (a - 1 >= 0 && b - 1 >= 0) {
                ans -= dp[a - 1][d];
                ans -= dp[c][b - 1];
                ans += dp[a - 1][b - 1];
            } else if (a - 1 >= 0) {
                ans -= dp[a - 1][d];
            } else if (b - 1 >= 0) {
                ans -= dp[c][b - 1];
            }

            System.out.println(ans);
        }

        br.close();
    }
}
