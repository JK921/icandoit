import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution15684 {
    public static int N;
    public static int M;
    public static int H;

    public static boolean[][] visited;

    public static boolean isGoal(boolean[][] map) {
        for (int i = 1; i <= N; i++) {
            int xx = 1;
            int yy = i;

            while (xx <= H) {
                if (map[xx][yy]) {
                    yy += 1;
                } else if (1 <= yy - 1 && map[xx][yy - 1]) {
                    yy -= 1;
                }
                xx += 1;
            }
            if (yy != i) {
                return false;
            }
        }
        return true;
    }

    public static int dfs(int row, int count) {
        if (isGoal(visited)) {
            return count;
        }

        if (count == 3) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;

        for (int col = 1; col < N; col++) {
            if (!visited[row][col]) {
                if (1 <= col - 1 && visited[row][col - 1])
                    continue;
                if (col + 1 <= N && visited[row][col + 1])
                    continue;

                visited[row][col] = true;
                ans = Math.min(ans, dfs(row, count + 1));
                visited[row][col] = false;
            }
        }

        if (row + 1 <= H) {
            ans = Math.min(ans, dfs(row + 1, count));
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/15684.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        visited = new boolean[H + 1][N + 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            visited[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        int ans = dfs(1, 0);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

        br.close();
    }
}
