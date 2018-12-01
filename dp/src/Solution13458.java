import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution13458 {
    public static int N;
    public static int[] appliers;
    public static int B;
    public static int C;

    public static long[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/13458.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        appliers = new int[N];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < N; i++) {
            appliers[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, appliers[i]);
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long sum = 0;
        for (int ap = 0; ap < N; ap++) {
            int student = appliers[ap] - B;

            sum += 1;
            if (student > 0) {
                sum += student / C;
                sum += student % C > 0 ? 1 : 0;
            }
        }

        System.out.println(sum);

        br.close();

    }

}
