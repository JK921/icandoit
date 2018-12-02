import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ���� ū ���� �κ� ����
 * https://www.acmicpc.net/problem/11055
 * 
 * ���� A�� �־����� ��, �� ������ ���� �κ� ���� �߿��� ���� ���� ū ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * 
 * Ǯ��: ���� ��ȹ�� O(n2)
 *       memo[n] == ���� A�� 0 ~ n ������ ���� �κ� ���� �� ���� ū ���� ��
 */
public class Solution11055 {
    public static int N;
    public static int[] num;
    public static int[] memo;

    public static int dp(int n) {
        if (n == N) {
            return 0;
        }

        if (memo[n] > -1) {
            return memo[n];
        }

        int ans = num[n];
        for (int next = n + 1; next < N; next++) {
            if (num[n] < num[next]) {
                ans = Math.max(ans, dp(next) + num[n]);
            }
        }

        return memo[n] = ans;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/11055.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        num = new int[N];
        memo = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(memo, -1);

        int ans = -1;
        for (int n = 0; n < N; n++) {
            ans = Math.max(ans, dp(n));
        }
        System.out.println(ans);

        br.close();
    }
}
