package queue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution14442 {
    public static int N;
    public static int M;
    public static int K;
    public static char[][] map;
    public static boolean[][][] visited;

    public static int[] nx = { 0, 0, -1, 1 };
    public static int[] ny = { 1, -1, 0, 0 };

    public static int bfs() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(0, 0, K, 1));

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            int x = node.x;
            int y = node.y;
            int k = node.k;
            int count = node.count;

            if (x == N - 1 && y == M - 1) {
                return count;
            }

            for (int i = 0; i < 4; i++) {
                int xx = node.x + nx[i];
                int yy = node.y + ny[i];
                if (0 <= xx && xx < N && 0 <= yy && yy < M && !visited[xx][yy][k]) {

                    if (map[xx][yy] == '1' && k > 0) {
                        visited[xx][yy][k] = true;
                        queue.add(new Node(xx, yy, k - 1, count + 1));
                    } else if (map[xx][yy] == '0') {
                        visited[xx][yy][k] = true;
                        queue.add(new Node(xx, yy, k, count + 1));
                    }
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/14442.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        visited = new boolean[N][M][11];
        for (int i = 0; i < 11; i++) {
            visited[0][0][i] = true;
        }

        System.out.println(bfs());
        br.close();
    }
}

