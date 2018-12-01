import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1937 {
    public static int N;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] memo;

    public static int[] nx = { 0, 0, -1, 1 };
    public static int[] ny = { 1, -1, 0, 0 };

    // 판다가 이동할 수 있는 4방향을 DFS 로 찾는다
    // memoization 필수
    public static int dfs(int x, int y) {
        int ans = 0;

        if (memo[x][y] > -1) {
            return memo[x][y];
        }

        for (int n = 0; n < 4; n++) {
            int xx = x + nx[n];
            int yy = y + ny[n];

            if (0 <= xx && xx < N && 0 <= yy && yy < N && map[x][y] < map[xx][yy] && !visited[xx][yy]) {
                visited[xx][yy] = true;
                ans = Math.max(ans, 1 + dfs(xx, yy));
                visited[xx][yy] = false;
            }
        }
        return memo[x][y] = ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1937.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];
        memo = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                memo[i][j] = -1;
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                ans = Math.max(ans, 1 + dfs(x, y));
            }
        }

        System.out.println(ans);

        sc.close();
    }

}
