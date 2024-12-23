import java.io.*;
import java.util.*;

class Main {
    public static int[] cntMap = new int[100001];
    public static int[] movement = { 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.print(0);
            return;
        }

        bfs(N, K);

        System.out.print(cntMap[K]);
    }

    public static void bfs(int N, int K) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(N);

        while (!queue.isEmpty()) {
            int now = queue.removeFirst();

            for (int move : movement) {
                int next = now + move;

                if (0 <= next && next <= 100000) {
                    if (cntMap[next] == 0) {
                        cntMap[next] = cntMap[now] + 1;
                        queue.addLast(next);
                    }

                    if (next == K) {
                        return;
                    }
                }
            }

            int teleport = now * 2;

            if (teleport <= 100000) {
                if (cntMap[teleport] == 0) {
                    cntMap[teleport] = cntMap[now] + 1;
                    queue.addLast(teleport);
                }

                if (teleport == K) {
                    return;
                }
            }
        }
    }
}
