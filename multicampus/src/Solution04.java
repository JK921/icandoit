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
    public static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/04.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        cards = new int[N + 1];
        dp = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            cards[n] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        dp[1] = cards[1];

        if (2 <= N) {
            dp[2] = dp[1] + cards[2];
        }

        for (int n = 3; n <= N; n++) {
            // cards[n]�� �������� �ʴ� ���
            long selected = Math.max(dp[n], dp[n - 1]);
            // cards[n]�� �����ϴ� ���
            long deselected = Math.max(dp[n - 2] + cards[n], dp[n - 3] + cards[n - 1] + cards[n]);
            
            dp[n] = Math.max(selected, deselected);
        }

        System.out.println(dp[N]);

        br.close();
    }
}
