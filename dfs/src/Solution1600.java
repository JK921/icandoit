import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int k;
    int count;

    Point(int x, int y, int k, int count) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.count = count;
    }
}

class Solution1600 {
    public static int K;
    public static int W;
    public static int H;

    public static int[][] map;
    public static boolean[][][] visited;

    public static int[] mx = { 0, 0, 1, -1 };
    public static int[] my = { 1, -1, 0, 0 };

    public static int[] hx = { 2, 2, 1, 1, -1, -1, -2, -2 };
    public static int[] hy = { 1, -1, 2, -2, 2, -2, 1, -1 };


    public static int bfs() {
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.remove();
            
            if (p.x == H - 1 && p.y == W - 1) {
                return p.count;
            }
            
            // 원숭이처럼 움직인 경우
            for (int m = 0; m < 4; m++) {
                int xx = p.x + mx[m];
                int yy = p.y + my[m];
                
                if (0 <= xx && xx < H && 0 <= yy && yy < W && map[xx][yy] != 1 && !visited[xx][yy][p.k]) {
                    visited[xx][yy][p.k] = true;
                    q.add(new Point(xx, yy, p.k, p.count + 1));
                }
            }

            if (p.k < K) {
                // 말처럼 움직인 경우
                for (int h = 0; h < 8; h++) {
                    int xx = p.x + hx[h];
                    int yy = p.y + hy[h];
                    int kk = p.k + 1;

                    if (0 <= xx && xx < H && 0 <= yy && yy < W && map[xx][yy] != 1 && !visited[xx][yy][kk]) {
                        visited[xx][yy][kk] = true;
                        q.add(new Point(xx, yy, kk, p.count + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1600.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        visited = new boolean[201][201][31];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < K; k++) {
            visited[0][0][k] = true;
        }

        if (W == 1 && H == 1) {
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }

        br.close();

    }
}