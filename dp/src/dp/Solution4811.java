package dp;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution4811 {
    static long[][] pillArray;

    static long pillCnt(int full, int half) {
        if ((full == 1 && half == 0) || full == 0) {
            return pillArray[full][half] = 1;
        } else if (half < 0) {
            return 0;
        }

        if (pillArray[full][half] > 0) {
            return pillArray[full][half];
        }
        return pillArray[full][half] = pillCnt(full - 1, half + 1) + pillCnt(full, half - 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/4811.txt"));
        // int n = sc.nextInt();

        for (int i = 1; i < 31; i++) {
            pillArray = new long[i + 1][i + 1];
            System.out.println(i + " : " + pillCnt(i, 0));
        }

        sc.close();
    }
}
