import java.io.*;
import java.util.*;

class Main {
    public static int[][] map;
    public static int[][] cntMap;
    public static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cntMap = new int[N][M];

        for (int n = 0; n < N; n++) {
            String input = br.readLine();

            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) - '0';
            }
        }

        bfs();

        System.out.print(cntMap[N-1][M-1]);
    }

    public static void bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{ 0, 0 });
        cntMap[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.removeFirst();

            for (int[] move : movement) {
                int nextX = now[0] + move[0];
                int nextY = now[1] + move[1];

                if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
                    if (map[nextX][nextY] == 1 && cntMap[nextX][nextY] == 0) {
                        queue.add(new int[]{ nextX, nextY });
                        cntMap[nextX][nextY] = cntMap[now[0]][now[1]] + 1;
                    }
                }
            }
        }
    }
}
