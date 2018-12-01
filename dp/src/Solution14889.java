import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution14889 {
    public static int MAX = 1024 * 1024;
    public static int N;
    public static int[][] synergy;
    public static int[] dp;
    public static boolean[] visited;
    public static int[][] memo;

    // 모든 조합을 계산한 dp 값을 참고하여 두 팀의 diff 가 최소가 되는 조합을 찾는다
    public static int dfs(int count, int n) {
        if (count == N / 2) {
            int away = ((1 << N) - 1) & ~n;
            int diff = Math.abs(dp[away] - dp[n]);
            return diff;
        }

        if (memo[count][n] > -1) {
            return memo[count][n];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                min = Math.min(min, dfs(count + 1, n + (1 << (i - 1))));
                visited[i] = false;
            }
        }

        return memo[count][n] = min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/14889.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        synergy = new int[N + 1][N + 1];
        dp = new int[MAX];
        visited = new boolean[N + 1];
        memo = new int[N + 1][MAX];

        Arrays.fill(memo[0], -1);
        for (int i = 1; i <= N; i++) {
            Arrays.fill(memo[i], -1);
            for (int j = 1; j <= N; j++) {
                synergy[i][j] = sc.nextInt();
            }
        }

        // 선수 조합을 비트로 표현하여 dp에 시너지 값 계산
        // 1, 2, 3 번 조합은 111 즉, 7 => dp[7] = xx
        // 1, 5, 6 번 조합은 110001 즉, 49 => dp[49] = xx
        // 3, 4, 6 번 조합은 101100 즉, 44 => dp[44] = xx
        for (int n = 3; n < (1 << N); n++) {
            int shift = 0;
            while (n >> shift != 1) {
                shift++;
            }

            int remains = n & ~(1 << shift);

            int sum = dp[remains];
            int idx = 1;
            while (remains > 0) {
                if (remains % 2 == 1) {
                    sum += synergy[idx][shift + 1];
                    sum += synergy[shift + 1][idx];
                }
                idx++;
                remains = remains >> 1;
            }
            dp[n] = sum;
        }

        int ans = dfs(0, 0);
        System.out.println(ans);

        sc.close();
    }
}
