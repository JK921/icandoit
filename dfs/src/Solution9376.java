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
  �˼� 2���� Ż����Ű�� ����� 3������ �ֽ��ϴ�.
 1) �˼� 1�� �˼�2�� ������ �ٱ����� ������ ���
 2) �˼�2�� �˼�1�� ������ �ٱ����� ������ ���
 3) �ܺ��� �����ڰ� �˼�1, 2�� ã���� �����ϴ� ���

    �� 3���� ��찡 ���ÿ� ����ȴٸ�?
    3���� ���� ���� ���鼭 Ž���� �� ���̰� ��� �������� ������ �� ���Դϴ�.
    �׸��� �츮�� �� ������ Ż���� �Ϸ�Ǿ��ٰ� �� �� ���� ���Դϴ�.
    
    ��� �������� ������ ���� �𸣴� ���� ��� ������ �����ؾ����װ�
    �� �������� 3���� ���� � ���� �Դ��� �ջ��� ���ݴϴ�. �׸��� �� �� ���� �ּҰ��� �츮�� ���ϴ� ���� �� ���Դϴ�.
    ��, ������ ������ ���� ��� -2�� ����� �մϴ�.(3���� ���ÿ� ���� ���� �ʾƵ� �ȴ�.)
    
    �����ϸ�, �� 3���� BFS�� ���� 3���� dist[][]�� �ϼ���Ų �� sum�� ���ݴϴ�.
    �� �������� ���縦 �ϸ鼭 �ּҰ��� ���մϴ�. (��, ���� ��ġ�� ��� -2)
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