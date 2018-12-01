package queue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Tree {
    int x;
    int y;
    boolean v;
    int count;

    Tree(int x, int y, boolean v, int count) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.count = count;
    }
}

public class Solution1938 {
    public static int N;

    public static char[][] map;
    public static boolean[][][] visited;

    public static Tree start;
    public static Tree end;

    public static int[] nx = { 0, 0, 1, -1 };
    public static int[] ny = { 1, -1, 0, 0 };

    public static int[] tx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    public static int[] ty = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static int bfs() {
        Queue<Tree> queue = new LinkedList<Tree>();
        queue.add(start);
        visited[start.x][start.y][start.v ? 1 : 0] = true;

        while (!queue.isEmpty()) {
            Tree tree = queue.remove();

            int x = tree.x;
            int y = tree.y;
            boolean v = tree.v;
            int count = tree.count;

            if (x == end.x && y == end.y && v == end.v) {
                return count;
            }

            // LRUD
            for (int i = 0; i < 4; i++) {
                int xx = x + nx[i];
                int yy = y + ny[i];

                if (!v && 0 <= xx && xx < N && 0 <= yy - 1 && yy + 1 < N && !visited[xx][yy][v ? 1 : 0]
                        && map[xx][yy - 1] == '0' && map[xx][yy] == '0' && map[xx][yy + 1] == '0') {
                    // 가로인 경우
                    visited[xx][yy][v ? 1 : 0] = true;
                    queue.add(new Tree(xx, yy, v, count + 1));
                } else if (v && 0 <= xx - 1 && xx + 1 < N && 0 <= yy && yy < N && !visited[xx][yy][v ? 1 : 0]
                        && map[xx - 1][yy] == '0' && map[xx][yy] == '0' && map[xx + 1][yy] == '0') {
                    // 세로인 경우
                    visited[xx][yy][v ? 1 : 0] = true;
                    queue.add(new Tree(xx, yy, v, count + 1));
                }
            }

            // T
            if (!visited[x][y][v ? 0 : 1]) {
                boolean transable = true;
                for (int i = 0; i < 8; i++) {
                    int xx = x + tx[i];
                    int yy = y + ty[i];

                    if (!(0 <= xx && xx < N && 0 <= yy && yy < N && map[xx][yy] == '0')) {
                        transable = false;
                        break;
                    }
                }

                if (transable) {
                    visited[x][y][v ? 0 : 1] = true;
                    queue.add(new Tree(x, y, !v, count + 1));
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1938.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N][2];

        int bx = -1, ex = -1;
        boolean bb = false, eb = false;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    if (bb == true) {
                        start = new Tree(i, j, bx != i, 0);
                        bb = false;
                    } else {
                        bb = true;
                        bx = i;
                    }
                    map[i][j] = '0';
                } else if (map[i][j] == 'E') {
                    if (eb == true) {
                        end = new Tree(i, j, ex != i, 0);
                        eb = false;
                    } else {
                        eb = true;
                        ex = i;
                    }
                    map[i][j] = '0';
                }
            }
        }

        System.out.println(bfs());

        br.close();
    }

}
