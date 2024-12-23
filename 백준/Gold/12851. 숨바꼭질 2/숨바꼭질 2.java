import java.io.*;
import java.util.*;

class Main {
    public static int[] cntMap = new int[100001];
    public static int[] movement = { 1, -1 };
    public static int route = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.print("0\n1");
            return;
        }

        bfs(N, K);

        System.out.print(cntMap[K] + "\n" + route);
    }

    public static void bfs(int N, int K) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(N);

        while (!queue.isEmpty()) {
            int now = queue.removeFirst();
            int nextTime = cntMap[now] + 1;

            int teleport = now * 2;

            if (teleport <= 100000) {
                if (cntMap[teleport] == 0 || cntMap[teleport] == nextTime) {
                    if (teleport == K) {
                        if (cntMap[teleport] == 0) {
                            cntMap[teleport] = nextTime;
                            route++;
                        }
                        else {
                            if (nextTime == cntMap[teleport]) {
                                route++;
                            }
                        }
                    }
                    cntMap[teleport] = nextTime;
                    queue.addLast(teleport);
                }
            }

            for (int move : movement) {
                int next = now + move;

                if (0 <= next && next <= 100000) {
                    if (cntMap[next] == 0 || cntMap[next] == nextTime) {
                        if (next == K) {
                            if (cntMap[next] == 0) {
                                cntMap[next] = nextTime;
                                route++;
                            }
                            else {
                                if (nextTime == cntMap[next]) {
                                    route++;
                                }
                            }
                        }
                        cntMap[next] = nextTime;
                        queue.addLast(next);
                    }
                }
            }
        }
    }
}