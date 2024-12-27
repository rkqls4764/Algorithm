import java.io.*;
import java.util.*;

class Main {
    public static char[][] map;
    public static int[][] cntMap;
    public static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            cntMap = new int[H][W];

            int humanX = 0;
            int humanY = 0;

            for (int h = 0; h < H; h++) {
                String input = br.readLine();
                for (int w = 0; w < W; w++) {
                    map[h][w] = input.charAt(w);

                    if (map[h][w] == '@') {
                        map[h][w] = '.';
                        humanX = h;
                        humanY = w;
                    }
                }
            }

            bw.write(bfs(humanX, humanY) + "\n");
        }

        bw.flush();
    }

    public static String bfs(int humanX, int humanY) {
        if (0 == humanX || 0 == humanY || humanX == map.length - 1 || humanY == map[0].length - 1) { // 처음 사람 위치가 테두리 위치이면 1 리턴
            return "1";
        }

        ArrayDeque<Object> queue = new ArrayDeque<>();

        for (int h = 0; h < map.length; h++) { // 큐에 불 추가
            for (int w = 0; w < map[h].length; w++) {
                if (map[h][w] == '*') {
                    queue.addLast(new Object(h, w, true));
                }
            }
        }

        queue.addLast(new Object(humanX, humanY, false)); // 큐에 사람 추가

        while (!queue.isEmpty()) {
            Object now = queue.removeFirst();

            for (int[] move : movement) {
                int nextX = now.x + move[0];
                int nextY = now.y + move[1];

                if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
                    if (now.isFire) {
                        if (map[nextX][nextY] == '.') { // 빈 공간인 경우 불 옮기기 가능
                            map[nextX][nextY] = '*';
                            queue.addLast(new Object(nextX, nextY, true));
                        }
                    }
                    else {
                        if (!queue.contains(new Object(nextX, nextY, true)) && map[nextX][nextY] == '.' && cntMap[nextX][nextY] == 0) { // 불이 붙으려는 칸이 아니고 처음 방문하는 빈 공간인 경우 이동 가능
                            if (0 == nextX || 0 == nextY || nextX == map.length - 1 || nextY == map[0].length - 1) { // 테두리에 도착한 경우 탈출
                                return String.valueOf(cntMap[now.x][now.y] + 2); // 현재 위치에서 테두리 위치로 이동할 때 + 1, 테두리 위치에서 바깥으로 이동할 때 + 1
                            }

                            queue.addLast(new Object(nextX, nextY, false));
                            cntMap[nextX][nextY] = cntMap[now.x][now.y] + 1;
                        }
                    }
                }
            }
        }

        return "IMPOSSIBLE";
    }
}

class Object {
    int x;
    int y;
    boolean isFire; //true: 불, false: 사람

    public Object(int x, int y, boolean isFire) {
        this.x = x;
        this.y = y;
        this.isFire = isFire;
    }
}