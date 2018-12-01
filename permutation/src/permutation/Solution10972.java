package permutation;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution10972 {
    static int N;
    static int[] arr;

    public static void swap(int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static boolean next_permutation() {
        int i = N - 1, j = N - 1;

        int left = N;
        int right = N;

        while (0 <= i - 1) {
            if (arr[i - 1] < arr[i]) {
                left = i - 1;
                break;
            }
            i -= 1;
        }

        if (i == 0) {
            return false;
        }

        while (left < j) {
            if (arr[left] < arr[j]) {
                right = j;
                break;
            }
            j -= 1;
        }

        swap(left, right);

        left = i;
        right = N - 1;

        while (left < right) {
            swap(left, right);
            left += 1;
            right -= 1;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/10972.txt"));

        N = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (next_permutation()) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("");
        } else {
            System.out.println("-1");
        }

        sc.close();
    }
}
