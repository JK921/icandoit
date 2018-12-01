import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
  죄수 2명을 탈옥시키는 방법은 3가지가 있습니다.
 1) 죄수 1이 죄수2를 데리고 바깥으로 나가는 경우
 2) 죄수2가 죄수1을 데리고 바깥으로 나가는 경우
 3) 외부의 조력자가 죄수1, 2를 찾으러 잠입하는 경우

    위 3가지 경우가 동시에 실행된다면?
    3명은 각자 문을 열면서 탐색을 할 것이고 어느 정점에서 만나게 될 것입니다.
    그리고 우리는 그 시점에 탈옥이 완료되었다고 볼 수 있을 것입니다.
    
    어느 정점에서 만나게 될지 모르니 맵의 모든 정점을 조사해야할테고
    각 정점마다 3명이 문을 몇개 열고 왔는지 합산을 해줍니다. 그리고 그 중 가장 최소값이 우리가 원하는 답이 될 것입니다.
    단, 만나는 지점이 문인 경우 -2를 해줘야 합니다.(3명이 동시에 문을 열지 않아도 된다.)
    
    정리하면, 위 3가지 BFS를 돌려 3개의 dist[][]를 완성시킨 후 sum을 해줍니다.
    각 정점마다 조사를 하면서 최소값을 구합니다. (단, 문의 위치인 경우 -2)
 */

public class Solution9376 {
    static final int[] nx = { 0, -1, 0, 1 };
    static final int[] ny = { -1, 0, 1, 0 };

    static int[][] map = new int[105][105];
    static int h, w;

    public static void main(String[] args) throws IOException {
        int t;

        System.setIn(new FileInputStream("./input/9376.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            Node2 helper = new Node2(0, 0);
            Node2 prison1 = null;
            Node2 prison2 = null;

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            for (int i = 1; i < h - 1; i++) {
                String s = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {

                    char c = s.charAt(j);
                    switch (c) {

                    case '.':
                    case '*':
                    case '#':
                        map[i][j] = c;
                        break;
                    case '$':
                        map[i][j] = c;
                        if (prison1 == null) {
                            prison1 = new Node2(i, j);
                        } else {
                            prison2 = new Node2(i, j);
                        }
                        break;
                    }
                }
            }

            for (int j = 0; j < w; j++) {
                map[0][j] = map[h - 1][j] = '.';
            }

            // solve
            int[][] dist1 = bfs(helper);
            int[][] dist2 = bfs(prison1);
            int[][] dist3 = bfs(prison2);

            int ans = h * w;
            int tempCost = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*')
                        continue;

                    tempCost = dist1[i][j] + dist2[i][j] + dist3[i][j];
                    if (map[i][j] == '#')
                        tempCost -= 2;

                    ans = ans > tempCost ? tempCost : ans;
                }
            }

            System.out.println(ans);
        }

    }

    public static int[][] bfs(Node2 src) {
        int[][] dist = new int[h][w];

        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<Node2> queue = new LinkedList<Node2>();
        queue.add(src);
        dist[src.x][src.y] = 0;

        while (!queue.isEmpty()) {
            Node2 u = queue.remove();

            for (int i = 0; i < 4; i++) {
                int xx = u.x + nx[i];
                int yy = u.y + ny[i];

                if (xx < 0 || xx >= h || yy < 0 || yy >= w)
                    continue;
                if (map[xx][yy] == '*')
                    continue;

                if (map[xx][yy] == '.' || map[xx][yy] == '$') {
                    if (dist[xx][yy] == -1 || dist[xx][yy] > dist[u.x][u.y]) {
                        dist[xx][yy] = dist[u.x][u.y];
                        queue.add(new Node2(xx, yy));
                    }
                } else if (map[xx][yy] == '#') {
                    if (dist[xx][yy] == -1 || dist[xx][yy] > dist[u.x][u.y] + 1) {
                        dist[xx][yy] = dist[u.x][u.y] + 1;
                        queue.add(new Node2(xx, yy));
                    }
                }
            }
        }

        return dist;
    }
}

class Node2 {
    int x;
    int y;

    Node2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}