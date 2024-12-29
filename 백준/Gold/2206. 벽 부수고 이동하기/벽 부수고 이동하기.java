import java.io.*;
import java.util.*;

class Main {
    public static int[][] map;
    public static int[][][] cntMap;
    public static int[][] movement = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cntMap = new int[N][M][2];

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) - '0';
            }
        }

        bfs();
    }

    public static void bfs() {
        ArrayDeque<Object> queue = new ArrayDeque<>();
        queue.addLast(new Object(0, 0, 0));
        cntMap[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Object cur = queue.removeFirst();

            if (cur.x == map.length - 1 && cur.y == map[0].length - 1) {
                System.out.print(cntMap[cur.x][cur.y][cur.isBreak]);
                return;
            }

            for (int[] move : movement) {
                int nextX = cur.x + move[0];
                int nextY = cur.y + move[1];

                if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
                    if (cntMap[nextX][nextY][cur.isBreak] == 0) { // 처음 방문함
                        if (map[nextX][nextY] == 0) { // 빈 공간임
                            cntMap[nextX][nextY][cur.isBreak] = cntMap[cur.x][cur.y][cur.isBreak] + 1;
                            queue.addLast(new Object(nextX, nextY, cur.isBreak));
                        }
                        else if (map[nextX][nextY] == 1 && cur.isBreak == 0) { // 벽이고 부수기 안 했음
                            cntMap[nextX][nextY][1] = cntMap[cur.x][cur.y][0] + 1;
                            queue.addLast(new Object(nextX, nextY, 1));
                        }
                    }
                }
            }
        }

        System.out.print("-1");
    }
}

class Object {
    int x;
    int y;
    int isBreak;

    Object(int x, int y, int isBreak) {
        this.x = x;
        this.y = y;
        this.isBreak = isBreak;
    }
}