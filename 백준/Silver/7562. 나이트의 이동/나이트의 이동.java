import java.io.*;
import java.util.*;

class Main {
    public static int[][] isVisit;
    public static int[][] movement = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            isVisit = new int[I][I];

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            if (x == targetX && y == targetY) {
                bw.write("0\n");
                continue;
            }

            bfs(x, y, targetX, targetY);
            bw.write(isVisit[targetX][targetY] + "\n");
        }

        bw.flush();
    }

    public static void bfs(int x, int y, int targetX, int targetY) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{ x, y });
        isVisit[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.removeFirst();

            for (int[] move : movement) {
                int nextX = now[0] + move[0];
                int nextY = now[1] + move[1];

                if (0 <= nextX && nextX < isVisit.length && 0 <= nextY && nextY < isVisit.length) {
                    if (isVisit[nextX][nextY] == 0) {
                        queue.addLast(new int[] { nextX, nextY });
                        isVisit[nextX][nextY] = isVisit[now[0]][now[1]] + 1;
                    }
                }

                if (nextX == targetX && nextY == targetY) {
                    return;
                }
            }
        }
    }
}