import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 철수에게는 빨간색, 초록색, 파란색 세 개의 버튼이 있다. 버튼 하나를 누를 때 마다 특정 점수를 얻을 수 있으며, 철수는 1초에 한 번씩
 * 버튼을 누를 수 있다. 물론, 특정 시간에는 세 개의 버튼 중에서 한 개의 버튼만을 누를 수 있다. 각 시간마다 특정 버튼을 눌렀을 때
 * 얻는 점수는 모두 다를 수 있다. 예를 들어, 시간 1에 빨간색, 초록색, 파란색 버튼에 대한 점수가 3, 5, 7 로 주어질 수 있다.
 * 이 경우에는 파란색을 누르는 것이 점수를 가장 많이 얻을 수 있다. 물론, 시간 2에 각 버튼에 대한 점수는 또 다를 수 있다. 버튼을
 * 누를 때에는 한 가지 규칙이 있는데, 연속하여 색깔이 같은 버튼을 두 번 누를 수 없다는 것이다. 예를 들어, 시간 1에 초록색 버튼을
 * 눌렀다면, 시간 2에는 초록색 버튼을 누를 수 없다. 이와 같은 규칙으로 각 시간마다 버튼을 누른다고 할 때, 철수가 얻을 수 있는 점수의
 * 최댓값을 출력하는 프로그램을 작성하시오.
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
