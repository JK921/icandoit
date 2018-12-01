import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution12100 {
    public static int N;
    public static int[][] map;

    public static char[] next = { 'L', 'R', 'U', 'D' };
//    public static ArrayList<Character> route = new ArrayList<Character>();

    public static int[][] clone(int[][] source) {
        int[][] target = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                target[i][j] = source[i][j];
            }
        }
        return target;
    }

    public static int[][] moveTo(int[][] board, char direct) {
        int[][] newBoard = clone(board);

        switch (direct) {
        case 'L': // 왼쪽
            for (int i = 0; i < N; i++) {
                int[] row = new int[N];
                int idx = 0;

                // 한쪽으로 몰기
                for (int j = 0; j < N; j++) {
                    if (newBoard[i][j] > 0) {
                        row[idx++] = newBoard[i][j];
                    }
                }
                newBoard[i] = row;
                row = new int[N];

                // 합치기
                idx = 0;
                for (int j = 0; j < N; j++) {
                    if (j + 1 < N && newBoard[i][j] > 0 && newBoard[i][j] == newBoard[i][j + 1]) {
                        row[idx++] = newBoard[i][j] * 2;
                        j++;
                    } else {
                        row[idx++] = newBoard[i][j];
                    }
                }

                newBoard[i] = row;
            }
            break;
        case 'R': // 오른쪽
            for (int i = 0; i < N; i++) {
                int[] row = new int[N];
                int idx = N - 1;

                // 한쪽으로 몰기
                for (int j = N - 1; 0 <= j; j--) {
                    if (newBoard[i][j] > 0 || newBoard[i][j] > 0) {
                        row[idx--] = newBoard[i][j];
                    }
                }
                newBoard[i] = row;
                row = new int[N];

                // 합치기
                idx = N - 1;
                for (int j = N - 1; 0 <= j; j--) {
                    if (0 <= j - 1 && newBoard[i][j] > 0 && newBoard[i][j] == newBoard[i][j - 1]) {
                        row[idx--] = newBoard[i][j] * 2;
                        j--;
                    } else {
                        row[idx--] = newBoard[i][j];
                    }
                }

                newBoard[i] = row;
            }
            break;
        case 'U': // 위
            for (int j = 0; j < N; j++) {
                int[] col = new int[N];
                int idx = 0;

                // 한쪽으로 몰기
                for (int i = 0; i < N; i++) {
                    if (newBoard[i][j] > 0) {
                        col[idx++] = newBoard[i][j];
                    }
                }
                for (int i = 0; i < N; i++) {
                    newBoard[i][j] = col[i];
                }
                col = new int[N];

                // 합치기
                idx = 0;
                for (int i = 0; i < N; i++) {
                    if (i + 1 < N && newBoard[i][j] > 0 && newBoard[i][j] == newBoard[i + 1][j]) {
                        col[idx++] = newBoard[i][j] * 2;
                        i++;
                    } else {
                        col[idx++] = newBoard[i][j];
                    }
                }

                for (int i = 0; i < N; i++) {
                    newBoard[i][j] = col[i];
                }
            }
            break;
        case 'D': // 아래
            for (int j = 0; j < N; j++) {
                int[] col = new int[N];
                int idx = N - 1;

                // 한쪽으로 몰기
                for (int i = N - 1; 0 <= i; i--) {
                    if (newBoard[i][j] > 0) {
                        col[idx--] = newBoard[i][j];
                    }
                }
                for (int i = 0; i < N; i++) {
                    newBoard[i][j] = col[i];
                }
                col = new int[N];

                // 합치기
                idx = N - 1;
                for (int i = N - 1; 0 <= i; i--) {
                    if (0 <= i - 1 && newBoard[i][j] > 0 && newBoard[i][j] == newBoard[i - 1][j]) {
                        col[idx--] = newBoard[i][j] * 2;
                        i--;
                    } else {
                        col[idx--] = newBoard[i][j];
                    }
                }

                for (int i = 0; i < N; i++) {
                    newBoard[i][j] = col[i];
                }
            }
            break;
        }

        return newBoard;
    }

    public static int dfs(int[][] board, int count) {
        if (count == 5) {
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ans = Math.max(ans, board[i][j]);
                }
            }
//            System.out.println(route.get(0) + " " + route.get(1) + " " + route.get(2) + " " + route.get(3) + " "
//                    + route.get(4) + ": " + ans);
            return ans;
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int[][] cloned = clone(board);
            int[][] moved = moveTo(cloned, next[i]);
//            route.add(next[i]);
            ans = Math.max(ans, dfs(moved, count + 1));
//            route.remove(count);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/12100.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = dfs(clone(map), 0);
        System.out.println(ans);

        br.close();
    }
}
