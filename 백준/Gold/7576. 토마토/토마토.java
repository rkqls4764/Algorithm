import java.io.*;
import java.util.*;

class Main {
    public static int[][] map;
    public static int[][] cntMap;
    public static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public static int successCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cntMap = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());

                if (map[n][m] == 1 || map[n][m] == -1) {
                    successCnt++;
                }
            }
        }

        if (successCnt == N * M) {
            System.out.print(0);
            return;
        }

        System.out.print(bfs());
    }

    public static int bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int time = 0;

        for (int n = 0; n < map.length; n++) {
            for (int m = 0; m < map[n].length; m++) {
                if (map[n][m] == 1) {
                    queue.addLast(new int[]{ n, m });
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.removeFirst();

            for (int[] move : movement) {
                int nextX = now[0] + move[0];
                int nextY = now[1] + move[1];

                if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
                    if (map[nextX][nextY] == 0) {
                        map[nextX][nextY] = 1;
                        cntMap[nextX][nextY] = cntMap[now[0]][now[1]] + 1;
                        queue.addLast(new int[]{ nextX, nextY });
                        successCnt++;

                        if (time < cntMap[nextX][nextY]) {
                            time = cntMap[nextX][nextY];
                        }
                    }
                }
            }
        }

        if (successCnt != map.length * map[0].length) {
            return -1;
        }

        return time;
    }

}