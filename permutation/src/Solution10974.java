import java.io.FileInputStream;
import java.util.Scanner;

public class Solution10974 {
    static int N;

    public static int[] swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
        return arr;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./10974.txt"));

        N = sc.nextInt();

        // initialize
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        while (true) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("");

            int left = N;
            int right = N - 1;

            // find left
            for (int i = N - 1; 0 <= i - 1; i--) {
                if (arr[i - 1] < arr[i]) {
                    left = i - 1;
                    break; // 첫번째로 발견된 오름차순!!
                }
            }

            if (left == N) { // 오름차순이 하나도 발견되지 않음
                break;
            }

            // find right
            for (int j = N - 1; left < j; j--) {
                if (arr[left] < arr[j]) {
                    right = j;
                    break; // 첫번째로 발견된 arr[left] 보다 큰 수 !!
                }
            }

            // swap left, right
            swap(arr, left, right);

            left++;
            right = N - 1;
            // reverse left + 1 ~ last
            while (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        sc.close();
    }
}
