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

        for (int n = 1; n <= N; n++) {
            dp[n] = n;
            for (int i = 2; i * i <= n; i++) {
                // ���Ϸ��� �� ���� ���� ������ Ȱ��
                dp[n] = Math.min(dp[n], dp[n - (i * i)] + 1);
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}
