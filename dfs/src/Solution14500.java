import java.io.FileInputStream;
import java.util.Scanner;

/*
 * 테트로미노가 4개의 정사각형으로 이루어져 있으므로
 * 연속된 4개의 정사각형으로 만들 수 있는 모양은 테트로미노들 중 한개로 표현 가능 함
 * DFS로 depth 4번만 visit 하면서 최대값을 찾으면 됨
 * ㅗ,ㅜ,ㅏ,ㅓ 테트로미노의 경우는 DFS로 불가능하므로 따로 구해야 함
 * */
public class Solution14500 {
    public static int N;
    public static int M;

    public static boolean[][] visited;
    public static int[][] board;

    public static int[] xx = { 0, 0, -1, 1 };
    public static int[] yy = { -1, 1, 0, 0 };

    public static int dfs(int x, int y, int count) {
        if (count >= 5) {
            return 0;
        }

        int ans = 0;
        // 다음 노드 선택 (왼쪽, 오른쪽, 위, 아래)
        for (int n = 0; n < 4; n++) {
            int nx = x + xx[n];
            int ny = y + yy[n];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                ans = Math.max(ans, dfs(nx, ny, count + 1) + board[x][y]);
                visited[nx][ny] = false;
            }
        }

        return ans;
    }

    public static int oau(int x, int y) {
        int ans = 0;

        // ㅗ모양
        if (x >= 1 && y >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x - 1][y] + board[x][y - 1] + board[x][y + 1]);
        // ㅜ모양
        if (x < N - 1 && y >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x + 1][y] + board[x][y - 1] + board[x][y + 1]);
        // ㅏ모양
        if (x < N - 1 && x >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x + 1][y] + board[x - 1][y] + board[x][y + 1]);
        // ㅓ모양
        if (x < N - 1 && x >= 1 && y >= 1)
            ans = Math.max(ans, board[x][y] + board[x + 1][y] + board[x - 1][y] + board[x][y - 1]);

        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/14500.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        board = new int[N][M];
        visited = new boolean[N][M];

        // 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // board 한칸씩 순회하며 최대값 찾음
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                ans = Math.max(ans, dfs(i, j, 1));
                ans = Math.max(ans, oau(i, j));
                visited[i][j] = false;
            }
        }

        System.out.println(ans);

        sc.close();
    }
}
