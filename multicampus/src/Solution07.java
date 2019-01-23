import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ���� N�� �������� ������ ǥ���ϰ��� �� ��, ����ؾ� �ϴ� ���� ���� �ּ� ������ ����ϴ� ���α׷��� �ۼ��Ͻÿ�. ���� ���, ����
 * 45�� �������� ������ ǥ���ϰ��� �� �� �ʿ��� ���� ���� �ּ� ������ 2���̸�, �̴� ������ ����.
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

        for (int i = 1; i < 1000; i++) {
            dp[i * i] = 1;
        }

        for (int n = 2; n <= N; n++) {
            if (dp[n] == 0) {
                dp[n] = n;
                for (int i = 1; i < n; i++) {
                    dp[n] = Math.min(dp[n], dp[i] + dp[n - i]);
                }
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}
