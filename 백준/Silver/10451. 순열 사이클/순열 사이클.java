import java.io.*;
import java.util.*;

class Main {
    public static int[] input;
    public static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            input = new int[N+1];
            isVisit = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                input[n] = Integer.parseInt(st.nextToken());
            }

            int cycleCnt = 0;

            for (int n = 1; n <= N; n++) {
                if (!isVisit[n]) {
                    cycleCnt += findCycle(n);
                }
            }

            bw.write(cycleCnt + "\n");
        }

        bw.flush();
    }

    public static int findCycle(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);

        while (!queue.isEmpty()) {
            int now = queue.removeFirst();
            isVisit[now] = true;

            if (input[now] == start) { // 출발 숫자와 같으면 사이클 맞음
                return 1;
            }

            if (!isVisit[input[now]]) { // 다음 숫자 넣기
                queue.addLast(input[now]);
            }
        }

        return 0;
    }
}