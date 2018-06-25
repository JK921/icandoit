import java.io.FileInputStream;
import java.util.Scanner;

public class Solution10973 {
    static int N;

    public static int[] swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
        return arr;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./10973.txt"));

        N = sc.nextInt();

        // initialize
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int left = N;
        int right = N;

        // find left
        for (int i = N - 1; 0 <= i - 1; i--) {
            if (arr[i - 1] > arr[i]) {
                left = i - 1;
                break;
            }
        }

        if (left == N) {
            System.out.println(-1);
        } else {
            // find right
            for (int j = N - 1; left + 1 <= j; j--) {
                if (arr[j] < arr[left]) {
                    right = j;
                    break;
                }
            }

            swap(arr, left, right);

            // reverse
            left = left + 1;
            right = N - 1;
            while (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }

            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
        }


        sc.close();
    }
}
