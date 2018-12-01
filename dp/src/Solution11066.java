import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution11066 {
    public static int T;
    public static int K;
    public static int[][] dp;
    public static int[] sum;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/11066.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            dp = new int[K + 1][K + 1];
            sum = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for (int k = 1; k <= K; k++) {
                sum[k] = sum[k - 1] + Integer.parseInt(st.nextToken());
            }

            for (int d = 1; d < K; d++) {
                for (int nx = 1; nx + d <= K; nx++) {
                    int ny = nx + d;
                    dp[nx][ny] = Integer.MAX_VALUE;
                    for (int mid = nx; mid < ny; mid++) {
                        dp[nx][ny] = Math.min(dp[nx][ny], dp[nx][mid] + dp[mid + 1][ny] + sum[ny] - sum[nx - 1]);
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
        br.close();
    }
}
