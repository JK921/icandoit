import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 큰 증가 부분 수열
 * https://www.acmicpc.net/problem/11055
 * 
 * 수열 A가 주어졌을 때, 그 수열의 증가 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
 * 
 * 풀이: 동적 계획법 O(n2)
 *       memo[n] == 수열 A의 0 ~ n 까지의 증가 부분 수열 중 가장 큰 합의 값
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
