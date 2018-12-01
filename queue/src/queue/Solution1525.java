package queue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Pair {
    String key;
    int count;

    Pair(String key, int count) {
        this.key = key;
        this.count = count;
    }
}
public class Solution1525 {
    public static Map<String, Integer> dp;

    public static int[] dx = { 0, 0, 1, -1 };
    public static int[] dy = { -1, 1, 0, 0 };

    public static void bfs(String key) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(key, 0));
        dp.put(key, 0);
        
        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            int index = pair.key.indexOf('0');

            for (int i = 0; i < 4; i++) {
                int xx = index / 3 + dx[i];
                int yy = index % 3 + dy[i];
                if (0 <= xx && xx < 3 && 0 <= yy && yy < 3) {
                    int indexx = xx * 3 + yy;
                    char[] chars = pair.key.toCharArray();
                    chars[index] = chars[indexx];
                    chars[indexx] = '0';
                    String next = new String(chars);

                    if (!dp.containsKey(next)) {
                        dp.put(next, pair.count + 1);
                        queue.add(new Pair(next, pair.count + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/1525.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new HashMap<String, Integer>();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            sb.append(br.readLine());
        }
        String init = sb.toString().replaceAll(" ", "");

        bfs(init);

        if (dp.containsKey("123456780")) {
            System.out.println(dp.get("123456780"));
        } else {
            System.out.println(-1);
        }

        br.close();
    }
}
