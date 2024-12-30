import java.io.*;
import java.util.*;

class Main {
    public static int[][] map;
    public static boolean[][] isVisit;
    public static int[][][] cntMap;
    public static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisit = new boolean[N][M];
        cntMap = new int[K + 1][N][M];

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m) - '0';
            }
        }

        bfs(K);
    }

    public static void bfs(int K) {
        ArrayDeque<Object> queue = new ArrayDeque<>();
        queue.addLast(new Object(0, 0, 0));
        cntMap[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Object cur = queue.removeFirst();

            if (cur.x == map.length - 1 && cur.y == map[0].length - 1) { // 목적지에 도착하면 종료
                System.out.print(cntMap[cur.breakCnt][cur.x][cur.y]);
                return;
            }

            for (int[] move : movement) {
                int nextX = cur.x + move[0];
                int nextY = cur.y + move[1];

                if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length) { // 범위 체크
                    continue;
                }
                
                if (map[nextX][nextY] == 0 && cntMap[cur.breakCnt][nextX][nextY] == 0) { // 처음 방문하는 빈 공간
                    if (cur.breakCnt != 0) { // 벽을 부순 적이 있는 경우, 벽을 부수지 않은 차원에서 안 간 곳이거나 벽을 부숴서 해당 위치까지 오는 시간이 더 작으면 이동
                        if (cntMap[cur.breakCnt - 1][nextX][nextY] == 0 || cntMap[cur.breakCnt - 1][nextX][nextY] > cntMap[cur.breakCnt][cur.x][cur.y] + 1) {
                            cntMap[cur.breakCnt][nextX][nextY] = cntMap[cur.breakCnt][cur.x][cur.y] + 1;
                            queue.addLast(new Object(nextX, nextY, cur.breakCnt));
                        }
                    }
                    else { // 벽을 부순 적이 없는 경우, 처음 간 곳이면 이동
                        cntMap[cur.breakCnt][nextX][nextY] = cntMap[cur.breakCnt][cur.x][cur.y] + 1;
                        queue.addLast(new Object(nextX, nextY, cur.breakCnt));
                    }
                }
                else if (map[nextX][nextY] == 1 && cur.breakCnt < K) { // 벽이고 부술 수 있음
                    if (cntMap[cur.breakCnt + 1][nextX][nextY] == 0) { // 벽을 부순 차원에서 방문 안 한 곳이거나 해당 위치까지 오는 시간이 더 작으면 이동
                        cntMap[cur.breakCnt + 1][nextX][nextY] = cntMap[cur.breakCnt][cur.x][cur.y] + 1;
                        queue.addLast(new Object(nextX, nextY, cur.breakCnt + 1));
                    }
                }
                
            }
        }

        System.out.print("-1"); // 목적지에 도착할 수 없으면 -1 출력
    }
}

class Object {
    int x;
    int y;
    int breakCnt;

    Object(int x, int y, int breakCnt) {
        this.x = x;
        this.y = y;
        this.breakCnt = breakCnt;
    }
}