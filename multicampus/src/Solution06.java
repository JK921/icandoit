import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2 x N ���簢�� ����� ĭ�� �ִ�. �̸� 2 x 1 ����� Ÿ�Ϸ� ���� ä��� �Ѵ�. ������ ����� ���� ����ϴ� ���α׷���
 * �ۼ��Ͻÿ�. ��, ������ ����� ���� �ſ� ���� �� �����Ƿ�, �� ����� ���� 1,000,007 �� ���� �������� ����Ѵ�.
 * 
 * ���� ���, N = 3 �� ��쿡�� ���� �� ������ ������ ��찡 �����Ѵ�.
 * 
 */
public class Solution06 {
    public static int N;
    public static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/06.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new long[N + 1];

        dp[0] = dp[1] = 1;
        for (int n = 2; n <= N; n++) {
            dp[n] = (dp[n - 2] % 1000007) + (dp[n - 1] % 1000007);
        }

        System.out.println(dp[N] % 1000007);

        br.close();
    }
}
