import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2098 {
    public static int N;
    public static int[][] map;
    public static int[][] dp;

    public static int MAX = 16;
    public static int INF = 987654321;

    public static int dfs(int node, int visit) {
        if (visit == (1 << N) - 1) {
            if (map[node][0] != 0) {
                return map[node][0];
            }
            return INF;
        }

        if (dp[node][visit] != -1) {
            return dp[node][visit];
        }

        int ans = INF;
        for (int next = 0; next < N; next++) {
            if ((visit & (1 << next)) == 0 && map[node][next] > 0) {
                ans = Math.min(ans, dfs(next, visit + (1 << next)) + map[node][next]);
            }
        }

        return dp[node][visit] = ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/2098.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[MAX][MAX];
        dp = new int[MAX][1 << MAX];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 1));

        br.close();
    }
}
