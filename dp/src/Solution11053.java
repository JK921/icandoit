import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution11053 {
    public static int N;
    public static int[] num;
    public static int[] memo;

    public static int dp(int n) {
        if (n == N) {
            return 1;
        }

        if (memo[n] > -1) {
            return memo[n];
        }

        int ans = 1;
        for (int next = n + 1; next < N; next++) {
            if (num[n] < num[next]) {
                ans = Math.max(ans, dp(next) + 1);
            }
        }

        return memo[n] = ans;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/11053.txt"));
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
        for(int n=0; n<N; n++) {
            ans = Math.max(ans, dp(n));
        }
        System.out.println(ans);

        br.close();
    }
}
