import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ö���� ����� ���� ������ ���� ������ �Ϸ� �Ѵ�. �� ���� ������ ��Ģ�� ������ ����.
 * 
 * ö���� ����� �����ư��� ������ ��������. �� ó������ ö���� ������ ��������. ������ ������ ������, �ּ� 1������ �ִ� 3������ ������
 * ������ �� �ִ�. ������ ������ ���� ����� ����. ���� ��� 5���� ������ �ִٰ� ����. ���⼭ ö���� 1���� ������ ��������, ����
 * 3���� ������ ������ ��, ö���� ���������� 1���� ������ �������ٰ� �����ϸ� �� ������ ���ڴ� ö���� �ȴ�. ����, ���ڰ� ��� ������
 * ���������Ŀ� ���� ���а� �޶��� �� �ִ�. ö���� ����� �̱�� ���ؼ� <b>�ּ��� ���ؼ� ������ �÷��� �Ѵ�.</b> n���� ������
 * �������� ������ �����Ѵٰ� �� ��, ö���� �� ������ �̱� �� �ִٸ� YES, �׷��� �ʴٸ� NO�� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * 
 */
public class Solution03 {
    public static int N;
    public static boolean[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/03.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new boolean[N + 1];
        Arrays.fill(dp, true);

        for (int n = 4; n <= N; n++) {
            // �� 1, 2, 3 ���� �� ���� ö���� �̰�ٸ�, ���� 1, 2, 3 ���� ���� ���� �� ������ ���� �̱� �� �ۿ� ����.
            dp[n] = !(dp[n - 3] && dp[n - 2] && dp[n - 1]);
        }

        System.out.println(dp[N] ? "Yes" : "No");

        br.close();
    }
}
