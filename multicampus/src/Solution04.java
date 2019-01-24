import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 카드가 주어지고, 각각은 자연수의 점수를 가진다. 철수는 이제 이 카드를 가져감으로써 카드에 적혀있는 수 만큼의 점수를 얻는다.
 * 단, 카드를 가져갈 때 한가지 규칙이 있는데, 이는 연속하여 3개의 카드는 가져갈 수 없다는 것이다. 예를 들어, 6개의 카드 “1 3 5
 * 2 7 3”가 주어질 경우, 3+5+7+3 = 18 만큼의 점수를 얻는 것이 최대이다. N개의 카드가 주어질 때, 얻을 수 있는 점수의
 * 최댓값을 출력하는 프로그램을 작성하시오.
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
            // cards[n]을 선택하지 않는 경우
            dp[n] = Math.max(dp[n], dp[n - 1]);

            // cards[n]을 선택하는 경우
            dp[n] = Math.max(dp[n - 2] + cards[n], dp[n - 3] + cards[n - 1] + cards[n]);

        }

        System.out.println(dp[N]);

        br.close();
    }
}
