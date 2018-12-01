import java.io.FileInputStream;
import java.util.Scanner;

/*
 * ��Ʈ�ι̳밡 4���� ���簢������ �̷���� �����Ƿ�
 * ���ӵ� 4���� ���簢������ ���� �� �ִ� ����� ��Ʈ�ι̳�� �� �Ѱ��� ǥ�� ���� ��
 * DFS�� depth 4���� visit �ϸ鼭 �ִ밪�� ã���� ��
 * ��,��,��,�� ��Ʈ�ι̳��� ���� DFS�� �Ұ����ϹǷ� ���� ���ؾ� ��
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
        // ���� ��� ���� (����, ������, ��, �Ʒ�)
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

        // �Ǹ��
        if (x >= 1 && y >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x - 1][y] + board[x][y - 1] + board[x][y + 1]);
        // �̸��
        if (x < N - 1 && y >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x + 1][y] + board[x][y - 1] + board[x][y + 1]);
        // �����
        if (x < N - 1 && x >= 1 && y < M - 1)
            ans = Math.max(ans, board[x][y] + board[x + 1][y] + board[x - 1][y] + board[x][y + 1]);
        // �ø��
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

        // �Է�
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // board ��ĭ�� ��ȸ�ϸ� �ִ밪 ã��
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
