import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 숫자 1, 2, 3을 이용하여 숫자 N을 만드는 경우의 수를 출력하는 프로그램을 작성하시오. 예를 들어, N이 4일 경우에는 다음의 7가지
 * 경우가 존재한다. 단, 경우의 수가 매우 많을 수 있으므로, 경우의 수를 1,000,007 로 나눈 나머지를 출력한다.
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
