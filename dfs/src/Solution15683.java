import java.io.FileInputStream;
import java.util.Scanner;

public class Solution15683 {
    public static int N;
    public static int M;

    public static int[][] map;
    public static int[] cctvx;
    public static int[] cctvy;
    public static int cctvn;

    // public static boolean[][] visited;

    public static char[][] direct1 = { { 'r' }, { 'u' }, { 'l' }, { 'd' } };
    public static char[][] direct2 = { { 'l', 'r' }, { 'u', 'd' } };
    public static char[][] direct3 = { { 'u', 'r' }, { 'l', 'u' }, { 'l', 'd' }, { 'r', 'd' } };
    public static char[][] direct4 = { { 'u', 'l', 'r' }, { 'l', 'u', 'd' }, { 'l', 'r', 'd' }, { 'r', 'd', 'u' } };
    public static char[][] direct5 = { { 'l', 'u', 'r', 'd' } };

    public static boolean[][] clone(boolean[][] visited) {
        boolean[][] cloned = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cloned[i][j] = visited[i][j];
            }
        }
        return cloned;
    }

    public static int getBlind(boolean[][] visited) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == false && map[i][j] != 6) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static boolean[][] visit(int x, int y, char[] directs, boolean[][] visited) {
        for (int d = 0; d < directs.length; d++) {
            char direct = directs[d];
            switch (direct) {
            case 'r':
                for (int j = y; j < M; j++) {
                    if (map[x][j] == 6) {
                        break;
                    }
                    visited[x][j] = true;
                }
                break;
            case 'd':
                for (int i = x; i < N; i++) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    visited[i][y] = true;
                }
                break;
            case 'l':
                for (int j = y; j >= 0; j--) {
                    if (map[x][j] == 6) {
                        break;
                    }
                    visited[x][j] = true;
                }
                break;
            case 'u':
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    visited[i][y] = true;
                }
                break;
            }
        }

        return visited;
    }

    public static int dfs(int n, boolean[][] visited) {
        if (n == cctvn) {
            return getBlind(visited);
        }
        int x = cctvx[n];
        int y = cctvy[n];

        int cctv = map[x][y];

        int min = Integer.MAX_VALUE;
        if (cctv == 1) {
            for (int i = 0; i < 4; i++) {
                boolean[][] cloned = clone(visited);
                visit(x, y, direct1[i], cloned);
                min = Math.min(min, dfs(n + 1, cloned));
            }
        } else if (cctv == 2) {
            for (int i = 0; i < 2; i++) {
                boolean[][] cloned = clone(visited);
                visit(x, y, direct2[i], cloned);
                min = Math.min(min, dfs(n + 1, cloned));
            }
        } else if (cctv == 3) {
            for (int i = 0; i < 4; i++) {
                boolean[][] cloned = clone(visited);
                visit(x, y, direct3[i], cloned);
                min = Math.min(min, dfs(n + 1, cloned));
            }
        } else if (cctv == 4) {
            for (int i = 0; i < 4; i++) {
                boolean[][] cloned = clone(visited);
                visit(x, y, direct4[i], cloned);
                min = Math.min(min, dfs(n + 1, cloned));
            }
        } else if (cctv == 5) {
            boolean[][] cloned = clone(visited);
            visit(x, y, direct5[0], cloned);
            min = Math.min(min, dfs(n + 1, cloned));
        }

        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/15683.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        cctvx = new int[9];
        cctvy = new int[9];
        cctvn = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (0 < map[i][j] && map[i][j] < 6) {
                    cctvx[cctvn] = i;
                    cctvy[cctvn] = j;
                    cctvn++;
                }
            }
        }

        int ans = dfs(0, visited);
        System.out.println(ans);
        sc.close();
    }

}
