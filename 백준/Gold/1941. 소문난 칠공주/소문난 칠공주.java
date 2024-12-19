import java.util.*;
import java.io.*;

public class Main {
    static char[][] map;
    static int[] crew;
    static boolean[] isVisit;
    static int[][] movement = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        crew = new int[7];
        answer = 0;

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        makeCrew(0, 0, 0);

        System.out.print(answer);
    }

    public static void makeCrew(int idx, int start, int YCnt) {
        if (YCnt > 3) { // Y가 3개 이하여야 한다.
            return;
        }

        if (idx == 7) {
            verifyCrew();
            return;
        }

        for (int i = start; i < 25; i++) {
            crew[idx] = i;

            if (map[i / 5][i % 5] == 'Y') {
                makeCrew(idx + 1, i + 1, YCnt + 1);
            }
            else {
                makeCrew(idx + 1, i + 1, YCnt);
            }
        }
    }

    public static void verifyCrew() {
        isVisit = new boolean[7];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int cnt = 1;

        queue.addLast(new int[] { crew[0] / 5, crew[0] % 5 });
        isVisit[0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            for (int[] move : movement) {
                int nextX = cur[0] + move[0];
                int nextY = cur[1] + move[1];
                int nextIdx = nextX * 5 + nextY;

                if (nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5) {
                    continue;
                }

                for (int j = 0; j < 7; j++) {
                    if (!isVisit[j] && crew[j] == nextIdx) {
                        isVisit[j] = true;
                        queue.addLast(new int[] { nextX, nextY });
                        cnt++;
                    }
                }
            }
        }

        if (cnt == 7) {
            answer++;
        }
    }

}