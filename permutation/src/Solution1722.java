import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1722 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/1722.txt"));

        int N = sc.nextInt();
        int[] arr = new int[N + 1];
        long[] factorial = new long[N + 1];
        boolean[] used = new boolean[N + 1];

        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        boolean showPermutation = (1 & sc.nextInt()) == 1 ? true : false;

        if (showPermutation) {
            long where = sc.nextLong();

            for (int idx = 0; idx < N; idx++) {
                int length = N - (idx + 1);

                for (int num = 1; num <= N; num++) {
                    if (used[num])
                        continue;
                    if (factorial[length] < where) {
                        where -= factorial[length];
                    } else {
                        arr[idx] = num;
                        used[num] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }

        } else {
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            long result = 0;
            for (int idx = 0; idx < N - 1; idx++) {
                int num = arr[idx];
                int length = N - (idx + 1);
                used[num] = true;

                for (int cand = 1; cand < num; cand++) {
                    if (!used[cand]) {
                        result += factorial[length];
                    }
                }
            }
            System.out.println(result + 1);
        }
        sc.close();
    }
}
