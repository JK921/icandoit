import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2 x N 직사각형 모양의 칸이 있다. 이를 2 x 1 모양의 타일로 가득 채우려 한다. 가능한 경우의 수를 출력하는 프로그램을
 * 작성하시오. 단, 가능한 경우의 수가 매우 많을 수 있으므로, 그 경우의 수를 1,000,007 로 나눈 나머지를 출력한다.
 * 
 * 예를 들어, N = 3 일 경우에는 다음 세 가지의 가능한 경우가 존재한다.
 * 
 */
public class Solution06 {
    public static int N;
    public static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input/06.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new long[N + 1];

        dp[0] = dp[1] = 1;
        for (int n = 2; n <= N; n++) {
            dp[n] = (dp[n - 2] % 1000007) + (dp[n - 1] % 1000007);
        }

        System.out.println(dp[N] % 1000007);

        br.close();
    }
}
