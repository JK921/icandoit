import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���� 1, 2, 3�� �̿��Ͽ� ���� N�� ����� ����� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�. ���� ���, N�� 4�� ��쿡�� ������ 7����
 * ��찡 �����Ѵ�. ��, ����� ���� �ſ� ���� �� �����Ƿ�, ����� ���� 1,000,007 �� ���� �������� ����Ѵ�.
 */
public class Solution01 {
    public static int N;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        for (int n = 1; n <= N; n++) {
            if (n == 1 || n == 2) {
                dp[n] = n;
            } else if (n == 3) {
                dp[n] = 4;
            } else {
                int ans = dp[n - 1] + dp[n - 2] + dp[n - 3];
                dp[n] = ans % 1000007;
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}
