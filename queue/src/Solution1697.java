

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1697 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileInputStream("./input/1697.txt"));

        int cur = sc.nextInt();
        int dest = sc.nextInt();

        boolean[] visited = new boolean[200000];
        int[] dist = new int[200000];

        Queue<Integer> queue = new LinkedList<Integer>();

        dist[cur] = 0;
        queue.add(cur);
        visited[cur] = true;

        while (!queue.isEmpty()) {
            cur = queue.remove();

            if (cur == dest) {
                break;
            }

            // -1
            if (0 <= cur - 1 && !visited[cur - 1]) {
                queue.add(cur - 1);
                dist[cur - 1] = dist[cur] + 1;
                visited[cur - 1] = true;
            }

            // +1
            if (cur + 1 < 200000 && !visited[cur + 1]) {
                queue.add(cur + 1);
                dist[cur + 1] = dist[cur] + 1;
                visited[cur + 1] = true;
            }

            // x2
            if (cur * 2 < 200000 && !visited[cur * 2]) {
                queue.add(cur * 2);
                dist[cur * 2] = dist[cur] + 1;
                visited[cur * 2] = true;
            }

        }
        System.out.println(dist[dest]);

        sc.close();
    }
}
