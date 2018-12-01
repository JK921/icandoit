package permutation;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution14888 {
    public static int N;

    public static int[] arr;
    public static char[] opers;

    public static int[] perm;
    public static int perm_size;

    public static void swap(int a, int b) {
        int tmp = perm[a];
        perm[a] = perm[b];
        perm[b] = tmp;
    }

    public static int next_permutation() {
        int left = 0, right = 0;
        for (int i = perm_size - 1; i > 1; i--) {
            if (perm[i - 1] < perm[i]) {
                left = i - 1;
                break;
            }
        }

        for (int i = perm_size - 1; i > 0; i--) {
            if (perm[i] > perm[left]) {
                right = i;
                break;
            }
        }

        if (left == right) {
            return -1;
        }

        swap(left, right);

        left = left + 1;
        right = perm_size - 1;
        while (left < right) {
            swap(left, right);
            left++;
            right--;
        }

        return 1;
    }

    public static long calc() {
        long oprd1 = arr[0];
        long oprd2 = 0;

        for (int i = 0; i < N - 1; i++) {
            char op = opers[perm[i] - 1];
            oprd2 = arr[i + 1];

            switch (op) {
            case '+':
                oprd1 = oprd1 + oprd2;
                break;
            case '-':
                oprd1 = oprd1 - oprd2;
                break;
            case '*':
                oprd1 = oprd1 * oprd2;
                break;
            case '/':
                oprd1 = oprd1 / oprd2;
                break;
            }
        }
        return oprd1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/14888.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];
        opers = new char[N - 1];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        perm_size = 0;
        char[] op = { '+', '-', '*', '/' };

        for (int i = 0; i < 4; i++) {
            int cnt = sc.nextInt();
            for (int n = 0; n < cnt; n++) {
                opers[perm_size++] = op[i];
            }
        }

        perm = new int[perm_size];
        for (int i = 0; i < perm_size; i++) {
            perm[i] = i + 1;
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        do {
            long ans = calc();
            min = Math.min(min, ans);
            max = Math.max(max, ans);
        } while (next_permutation() > 0);

        System.out.println(max);
        System.out.println(min);
        sc.close();
    }

}
