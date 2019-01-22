import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N���� ī�尡 �־�����, ������ �ڿ����� ������ ������. ö���� ���� �� ī�带 ���������ν� ī�忡 �����ִ� �� ��ŭ�� ������ ��´�.
 * ��, ī�带 ������ �� �Ѱ��� ��Ģ�� �ִµ�, �̴� �����Ͽ� 3���� ī��� ������ �� ���ٴ� ���̴�. ���� ���, 6���� ī�� ��1 3 5
 * 2 7 3���� �־��� ���, 3+5+7+3 = 18 ��ŭ�� ������ ��� ���� �ִ��̴�. N���� ī�尡 �־��� ��, ���� �� �ִ� ������
 * �ִ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 */
public class Solution04 {
    public static int N;
    public static int[] cards;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/04.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        cards = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            cards[n] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        dp[1] = cards[1];
        for (int n = 2; n <= N; n++) {
            if (n == 2) {
                dp[n] = dp[n - 1] + cards[n];
            }
            if (0 <= n - 2) {
                dp[n] = Math.max(dp[n], dp[n - 2] + cards[n]);
            }
            if (0 <= n - 3) {
                dp[n] = Math.max(dp[n], dp[n - 3] + cards[n - 1] + cards[n]);
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}
