import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/2157
 * DP ¹®Á¦
 * */
public class Solution2157 {
    public static int N;
    public static int M;
    public static int K;
    public static int INF = 987654321;

    public static int[][] map;
    public static int[][] memo;

    public static int dp(int n, int count) {
        if (n == N) {
            return 0;
        }

        if (count == M) {
            return -INF;
        }

        if (memo[n][count] > -1) {
            return memo[n][count];
        }

        int ans = -INF;
        for (int next = n + 1; next <= N; next++) {
            if (map[n][next] > 0) {
                ans = Math.max(ans, dp(next, count + 1) + map[n][next]);
            }
        }
        return memo[n][count] = ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/2157.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[301][301];
        memo = new int[301][301];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int begin = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            map[begin][end] = Math.max(map[begin][end], score);
        }

        System.out.println(dp(1, 1));

        br.close();
    }
}
