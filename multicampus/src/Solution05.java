import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ö�����Դ� ������, �ʷϻ�, �Ķ��� �� ���� ��ư�� �ִ�. ��ư �ϳ��� ���� �� ���� Ư�� ������ ���� �� ������, ö���� 1�ʿ� �� ����
 * ��ư�� ���� �� �ִ�. ����, Ư�� �ð����� �� ���� ��ư �߿��� �� ���� ��ư���� ���� �� �ִ�. �� �ð����� Ư�� ��ư�� ������ ��
 * ��� ������ ��� �ٸ� �� �ִ�. ���� ���, �ð� 1�� ������, �ʷϻ�, �Ķ��� ��ư�� ���� ������ 3, 5, 7 �� �־��� �� �ִ�.
 * �� ��쿡�� �Ķ����� ������ ���� ������ ���� ���� ���� �� �ִ�. ����, �ð� 2�� �� ��ư�� ���� ������ �� �ٸ� �� �ִ�. ��ư��
 * ���� ������ �� ���� ��Ģ�� �ִµ�, �����Ͽ� ������ ���� ��ư�� �� �� ���� �� ���ٴ� ���̴�. ���� ���, �ð� 1�� �ʷϻ� ��ư��
 * �����ٸ�, �ð� 2���� �ʷϻ� ��ư�� ���� �� ����. �̿� ���� ��Ģ���� �� �ð����� ��ư�� �����ٰ� �� ��, ö���� ���� �� �ִ� ������
 * �ִ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * 
 */
public class Solution05 {
    public static int N;

    public static int[][] button;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/05.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        button = new int[N][3];
        dp = new int[N][3];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            dp[n][0] = Integer.parseInt(st.nextToken());
            dp[n][1] = Integer.parseInt(st.nextToken());
            dp[n][2] = Integer.parseInt(st.nextToken());

            if (0 < n) {
                dp[n][0] = dp[n][0] + Math.max(dp[n - 1][1], dp[n - 1][2]);
                dp[n][1] = dp[n][1] + Math.max(dp[n - 1][0], dp[n - 1][2]);
                dp[n][2] = dp[n][2] + Math.max(dp[n - 1][0], dp[n - 1][1]);
            }
        }
        
        System.out.println(Math.max(Math.max(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));

        br.close();
    }

}
