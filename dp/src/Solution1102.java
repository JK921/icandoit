import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution1102 {
    public static int N;
    public static int[][] dp;
    public static int[][] cost;
    public static int P;

    public static int minCost(int yCnt, int ySet) {
        // ���� ��ǥ ������ ���������� �׸� ã��
        if (yCnt >= P) {
            return 0;
        }

        // memo �� ������ ����
        if (dp[yCnt][ySet] >= 0) {
            return dp[yCnt][ySet];
        }

        // ��û ū ���� �ʱ�ȭ
        dp[yCnt][ySet] = 1000000000;

        for (int i = 0; i < N; i++) {
            if ((ySet & (1 << i)) == (1 << i)) { // i�� ��������
                for (int j = 0; j < N; j++) {
                    if (i == j || (ySet & (1 << j)) == (1 << j)) // j�� ���������� ��ŵ
                        continue;

                    // �������� ���� �����Ҹ� ã�� ���� state ���
                    dp[yCnt][ySet] = Math.min(dp[yCnt][ySet], minCost(yCnt + 1, ySet | (1 << j)) + cost[i][j]);
                }
            }
        }

        return dp[yCnt][ySet];
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/1102.txt"));

        N = sc.nextInt();

        dp = new int[N + 1][1 << 16];
        cost = new int[N][N];

        // init
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        char[] chars = sc.next().toCharArray();
        P = sc.nextInt();

        int ySet = 0;
        int yCnt = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'Y') {
                ySet |= (1 << i);
                yCnt++;
            }
        }

        int ans = minCost(yCnt, ySet);

        System.out.println(ans == 1000000000 ? -1 : ans);
        sc.close();
    }

}
