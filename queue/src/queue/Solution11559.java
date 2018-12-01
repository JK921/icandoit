package queue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Puyo {
    int x;
    int y;

    Puyo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution11559 {
    public static char[][] map;
    public static boolean[][] visited;

    public static ArrayList<Puyo> cleared;

    public static int[] nx = { 0, 0, 1, -1 };
    public static int[] ny = { 1, -1, 0, 0 };

    public static char[][] down(char[][] cloned) {
        for (int j = 0; j < 6; j++) {
            char[] col = new char[12];
            Arrays.fill(col, '.');
            int idx = 0;

            for (int i = 11; 0 <= i; i--) {
                if (cloned[i][j] != '.') {
                    col[idx++] = cloned[i][j];
                }
                cloned[i][j] = '.';
            }
            for (int i = 0; i < idx; i++) {
                cloned[11 - i][j] = col[i];
            }
        }

        return cloned;
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        cleared.add(new Puyo(x, y));

        for (int n = 0; n < 4; n++) {
            int xx = x + nx[n];
            int yy = y + ny[n];

            if (0 <= xx && xx < 12 && 0 <= yy && yy < 6 && map[x][y] == map[xx][yy] && !visited[xx][yy]) {
                dfs(xx, yy);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/11559.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];
        visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int ans = 0;

        while (true) {
            visited = new boolean[12][6];
            boolean breaking = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        cleared = new ArrayList<Puyo>();
                        dfs(i, j);

                        if (cleared.size() >= 4) {
                            breaking = true;
                            for (int c = 0; c < cleared.size(); c++) {
                                Puyo puyo = cleared.get(c);
                                map[puyo.x][puyo.y] = '.';
                            }
                        }
                    }
                }
            }

            if (!breaking) {
                break;
            }

            ans++;
            map = down(map);
        }

        System.out.println(ans);

        br.close();

    }
}
