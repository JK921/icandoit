import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * ���� �� �����ϴ� �κ� ���� 2
 * https://www.acmicpc.net/problem/12015
 * 
 * Ǯ��: ���� ��ȹ�� O(NlogK)
 *       �Է��� 10000000 �̹Ƿ� ������ȹ�� + memoization�� ����ϴ� O(N2) �� �ȵ�
 *       dp[i] == ���� i �� �κм����� �� ���� ���� ������ �� 
 * */
public class Solution12015 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/12015.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];

        Arrays.fill(dp, 1000001);
        int lower_bound = 0;

        String[] nums = br.readLine().split(" ");
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(nums[n]);

            if (dp[lower_bound] < num) {
                dp[++lower_bound] = num;
            } else if (num < dp[lower_bound]) {
                // �̺� Ž��: num �� �� �ڸ� ã��
                int l = 0;
                int r = lower_bound;
                int mid = 0;
                while (l < r) {
                    mid = (l + r) / 2;
                    if (dp[mid] < num)
                        l = mid + 1;
                    else
                        r = mid;
                }
                dp[r] = num;
            }
        }

        System.out.println(lower_bound + 1);
        br.close();
    }
}
