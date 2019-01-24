import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 숫자 N을 제곱수의 합으로 표현하고자 할 때, 사용해야 하는 제곱 수의 최소 개수를 출력하는 프로그램을 작성하시오. 예를 들어, 숫자
 * 45를 제곱수의 합으로 표현하고자 할 때 필요한 제곱 수의 최소 개수는 2개이며, 이는 다음과 같다.
 * 
 * 45 = 3^2 + 6^2
 * 
 */
public class Solution07 {
    public static int N;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/07.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[1000001];

        for (int n = 1; n <= N; n++) {
            dp[n] = n;
            for (int i = 2; i * i <= n; i++) {
                // 구하려는 수 보다 작은 제곱수 활용
                dp[n] = Math.min(dp[n], dp[n - (i * i)] + 1);
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}
