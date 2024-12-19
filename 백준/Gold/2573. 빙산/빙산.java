import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map;
    public static boolean[][] isVisit;
    public static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public static int iceCnt;
    public static int meltCnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisit = new boolean[N][M];
        iceCnt = N * M;
        meltCnt = 0;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 0) {
                    meltCnt++;
                    isVisit[n][m] = true;
                }
            }
        }

        System.out.print(melt());
    }

    public static int melt() {
        int year = 0;

        while (meltCnt < iceCnt) {
            ArrayList<Change> changeList = new ArrayList<>();
            year++;

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != 0) {
                        int zeroCnt = 0;

                        for (int[] move : movement) {
                            int[] next = new int[]{i + move[0], j + move[1]};

                            if (0 <= next[0] && next[0] < map.length && 0 <= next[1] && next[1] < map[i].length) {
                                if (map[next[0]][next[1]] == 0) {
                                    zeroCnt++;
                                }
                            }
                        }

                        int changeNum = map[i][j] - zeroCnt;
                        if (changeNum > 0) {
                            changeList.add(new Change(i, j, changeNum));
                            isVisit[i][j] = false;
                        }
                        else {
                            changeList.add(new Change(i, j, 0));
                            isVisit[i][j] = true;
                            meltCnt++;
                        }
                    }
                }
            }

            for (Change change : changeList) {
                map[change.x][change.y] = change.value;
            }

            if (isSplit(map)) {
                return year;
            }
        }

        return 0;
    }

    public static boolean isSplit(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!isVisit[i][j] && map[i][j] != 0) {
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.addLast(new int[] { i, j });
                    isVisit[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.removeFirst();

                        for (int[] move : movement) {
                            int[] next = { cur[0] + move[0], cur[1] + move[1] };

                            if (0 <= next[0] && next[0] < map.length && 0 <= next[1] && next[1] < map[i].length) {
                                if (!isVisit[next[0]][next[1]] && map[next[0]][next[1]] != 0) {
                                    queue.addLast(new int[]{next[0], next[1]});
                                    isVisit[next[0]][next[1]] = true;
                                }
                            }
                        }
                    }

                    cnt++;
                }
            }
        }

        return cnt > 1;
    }

    public static class Change {
        int x;
        int y;
        int value;

        public Change(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}