import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Robot {
    int x;
    int y;
    int d;
    int count;

    Robot(int x, int y, int d, int count) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.count = count;
    }
}

public class Solution1726 {
    public static int M;
    public static int N;

    public static int[][] map;
    public static boolean[][][] visited;

    public static Robot source;
    public static Robot target;

    public static int bfs(Robot robot) {
        Queue<Robot> queue = new LinkedList<Robot>();
        queue.add(robot);
        visited[robot.x][robot.y][robot.d] = true;

        while (!queue.isEmpty()) {
            Robot next = queue.remove();

            int xx = next.x;
            int yy = next.y;
            int dd = next.d;

            if (xx == target.x && yy == target.y && dd == target.d) {
                return next.count;
            }

            // ����
            for (int k = 1; k <= 3; k++) {
                if (next.d == 1) { // ��
                    xx = next.x - k;
                } else if (next.d == 2) { // ��
                    yy = next.y + k;
                } else if (next.d == 3) { // ��
                    xx = next.x + k;
                } else if (next.d == 4) { // ��
                    yy = next.y - k;
                }
                // 1 ~ 3 ĭ ���� �ѹ��� ���� ����
                if (1 <= xx && xx <= M && 1 <= yy && yy <= N) {
                    if (map[xx][yy] != 0) {
                        break; // �� break�� ������ 3ĭ ������ ���� �Ѿ �� �ִ�
                    } else if (!visited[xx][yy][dd]) {
                        visited[xx][yy][dd] = true;
                        queue.add(new Robot(xx, yy, dd, next.count + 1));
                    }

                }
            }

            // ���� ȸ�� - 1
            xx = next.x;
            yy = next.y;
            dd = next.d - 1 <= 0 ? 4 : next.d - 1;
            if (1 <= xx && xx <= M && 1 <= yy && yy <= N && map[xx][yy] == 0 && !visited[xx][yy][dd]) {
                visited[xx][yy][dd] = true;
                queue.add(new Robot(xx, yy, dd, next.count + 1));
            }

            // ������ ȸ�� +1
            xx = next.x;
            yy = next.y;
            dd = next.d + 1 > 4 ? next.d + 1 - 4 : next.d + 1;
            if (1 <= xx && xx <= M && 1 <= yy && yy <= N && map[xx][yy] == 0 && !visited[xx][yy][dd]) {
                visited[xx][yy][dd] = true;
                queue.add(new Robot(xx, yy, dd, next.count + 1));
            }
        }

        return 0;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1726.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M + 1][N + 1];
        visited = new boolean[M + 1][N + 1][5];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        d = d == 1 ? 2 : d == 2 ? 4 : d == 4 ? 1 : d;
        source = new Robot(x, y, d, 0);

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        d = d == 1 ? 2 : d == 2 ? 4 : d == 4 ? 1 : d;
        target = new Robot(x, y, d, 0);

        System.out.println(bfs(source));

        br.close();
    }
}
