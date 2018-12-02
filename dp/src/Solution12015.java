import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 가장 긴 증가하는 부분 수열 2
 * https://www.acmicpc.net/problem/12015
 * 
 * 풀이: 동적 계획법 O(NlogK)
 *       입력이 10000000 이므로 동적계획법 + memoization을 사용하는 O(N2) 은 안됨
 *       dp[i] == 길이 i 인 부분수열들 중 제일 작은 마지막 값 
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
                // 이분 탐색: num 이 들어갈 자리 찾기
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
