import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution14502 {
    public static int N;
    public static int M;

    public static int[][] lab;
    public static int[][] wall3lab;

    public static Queue<Node> virus_origin;
    public static Queue<Node> virus;

    public static int safe_origin;
    public static int safe;

    public static int[] dx = { 0, 0, -1, 1 };
    public static int[] dy = { -1, 1, 0, 0 };

    // BFS
    public static void spreadVirus() {
        virus = new LinkedList<Node>();
        Iterator<Node> vs = virus_origin.iterator();
        while (vs.hasNext()) {
            virus.add(vs.next());
        }

        while (!virus.isEmpty()) {
            Node node = virus.remove();

            // 왼쪽, 오른쪽, 위, 아래
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (wall3lab[nx][ny] == 0) {
                        safe--;
                        wall3lab[nx][ny] = 2;
                        virus.add(new Node(nx, ny));
                    }
                }
            }

        }
    }

    public static void cloneLab() {
        wall3lab = new int[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                wall3lab[n][m] = lab[n][m];
            }
        }
        safe = safe_origin - 3;
    }

    public static int getSafeArea() {
        int ans = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (wall3lab[n][m] == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int buildFireWall(int count) {
        if (count == 0) {
            // 3개의 벽 세운 상태 복사
            cloneLab();
            // 바이러스 퍼트리며 안전영역 세기
            spreadVirus();
            return safe;
        }

        // DFS 로 안전영역 최대값 찾기
        int safe = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (lab[n][m] == 0) {
                    lab[n][m] = 1;
                    safe = Math.max(safe, buildFireWall(count - 1));
                    lab[n][m] = 0;
                }
            }
        }
        return safe;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/14502.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        lab = new int[N][M];
        virus_origin = new LinkedList<Node>();
        safe_origin = 0;

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                lab[n][m] = sc.nextInt();

                if (lab[n][m] == 2) {
                    virus_origin.add(new Node(n, m));
                } else if (lab[n][m] == 0) {
                    safe_origin++;
                }
            }
        }

        int ans = buildFireWall(3);
        System.out.println(ans);

        sc.close();
    }

}
