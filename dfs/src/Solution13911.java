import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution13911 {
    public static int V, E, M, mm, S, ms;
    public static ArrayList<Integer> vm, vs;

    public static int[][] map;
    public static ArrayList<Integer>[] adj;

    public static void diijkstra(Queue<Integer> queue, int[] dist) {
        boolean[] visited = new boolean[V + 1];

        while (!queue.isEmpty()) {
            int v = queue.remove();
            visited[v] = true;

            if (adj[v] != null) {
                Iterator<Integer> it = adj[v].iterator();
                while (it.hasNext()) {
                    int next = it.next();
                    if (map[v][next] != Integer.MAX_VALUE) {
                        if (dist[next] > dist[v] + map[v][next]) {
                            dist[next] = dist[v] + map[v][next];
                        }
                        if (!visited[next] && !queue.contains(next)) {
                            queue.add(next);
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/13911.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[V + 1][V + 1];
        adj = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        // 노드 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (v1 == v2) {
                continue;
            }

            if (adj[v1] == null) {
                adj[v1] = new ArrayList<Integer>();
            }
            adj[v1].add(v2);

            if (adj[v2] == null) {
                adj[v2] = new ArrayList<Integer>();
            }
            adj[v2].add(v1);


            // 두 정점 사이에 여러개의 간선이 있을 경우 큰건 쳐냄
            map[v1][v2] = Math.min(e, map[v1][v2]);
            map[v2][v1] = Math.min(e, map[v2][v1]);
        }

        // 맥도널드 정보
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        mm = Integer.parseInt(st.nextToken());
        vm = new ArrayList<Integer>();

        int[] minDiijkstraMac = new int[V + 1];
        Arrays.fill(minDiijkstraMac, Integer.MAX_VALUE);

        minDiijkstraMac[0] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int mac = Integer.parseInt(st.nextToken());
            minDiijkstraMac[mac] = 0;
            queue.add(mac);
            vm.add(mac);
        }

        diijkstra(queue, minDiijkstraMac);


        // 스타벅스 정보
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        vs = new ArrayList<Integer>();

        int[] minDiijkstraStar = new int[V + 1];
        Arrays.fill(minDiijkstraStar, Integer.MAX_VALUE);

        minDiijkstraStar[0] = 0;
        queue = new LinkedList<Integer>();
        queue.add(0);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int star = Integer.parseInt(st.nextToken());
            minDiijkstraStar[star] = 0;
            queue.add(star);
            vs.add(star);
        }

        diijkstra(queue, minDiijkstraStar);


        // 최소 거리 계산
        int ans = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if (!vm.contains(i) && !vs.contains(i) && minDiijkstraMac[i] <= mm && minDiijkstraStar[i] <= ms) {
                int sum = minDiijkstraMac[i] + minDiijkstraStar[i];
                if (sum == min) {
                    ans += sum;
                } else if (sum < min) {
                    ans = min = sum;
                }
            }
        }
        System.out.println(ans == 0 ? -1 : ans);
    }
}
