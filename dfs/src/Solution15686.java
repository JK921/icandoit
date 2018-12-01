import java.io.FileInputStream;
import java.util.Scanner;

public class Solution15686 {
    public static int N;
    public static int M;

    public static int[][] map;

    public static int[] cstmX;
    public static int[] cstmY;
    public static int cstmN;

    public static int[] chicX;
    public static int[] chicY;
    public static int chicN;

    public static int[][] dist;
    public static int[][] memo;

    public static boolean[] closed;

    public static int getChicDist() {
        int sum = 0;
        for (int i = 0; i < cstmN; i++) {
            int ans = Integer.MAX_VALUE;
            for (int j = 0; j < chicN; j++) {
                if (closed[j] == false) {
                    ans = Math.min(ans, dist[i][j]);
                }
            }
            sum += ans;
        }

        return sum;
    }

    // 치킨집을 하나씩 폐업시키면서 최소 치킨거리 비교
    public static int dfs(int m, int state) {
        if (m == M) {
            return getChicDist();
        }

        if (memo[m][state] > 0) {
            return memo[m][state];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < chicN; i++) {
            if (closed[i] == false) {
                closed[i] = true;
                ans = Math.min(ans, dfs(m - 1, state & ~(1 << i)));
                closed[i] = false;
            }
        }
        return memo[m][state] = ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/15686.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];

        cstmX = new int[2 * N];
        cstmY = new int[2 * N];
        cstmN = 0;

        chicX = new int[14];
        chicY = new int[14];
        chicN = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    cstmX[cstmN] = i;
                    cstmY[cstmN] = j;
                    cstmN++;
                } else if (map[i][j] == 2) {
                    chicX[chicN] = i;
                    chicY[chicN] = j;
                    chicN++;
                }
            }
        }

        closed = new boolean[chicN];
        dist = new int[cstmN][chicN];
        memo = new int[chicN + 1][1 << (chicN + 1) - 1];

        for (int i = 0; i < cstmN; i++) {
            for (int j = 0; j < chicN; j++) {
                dist[i][j] = Math.abs(cstmX[i] - chicX[j]) + Math.abs(cstmY[i] - chicY[j]);
            }
        }

        int ans = dfs(chicN, (1 << chicN) - 1);
        System.out.println(ans);

        sc.close();

    }

}
