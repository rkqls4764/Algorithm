import java.io.*;
import java.util.*;

class Main {
    public static int[] nextNode;
    public static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            nextNode = new int[N+1];
            depth = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                nextNode[n] = Integer.parseInt(st.nextToken());
                depth[n] = 0;
            }

            int cnt = 0;
            for (int n = 1; n <= N; n++) {
                if (depth[n] == 0) {
                    depth[n] = 1;
                    cnt += dfs(n);
                }
            }

            System.out.println(N - cnt);
        }
    }

    public static int dfs(int nodeNum) {
        int next = nextNode[nodeNum];
        int cycleCnt = 0;

        if (depth[next] == 0) { // 첫 방문
            depth[next] = depth[nodeNum] + 1;
            cycleCnt = dfs(next);
        }
        else { // 재방문 (사이클)
            cycleCnt = depth[nodeNum] - depth[next] + 1;
        }

        depth[nodeNum] = 100001; // 다음 탐색을 위해 재귀 안에서 초기화 (n의 최대값이 100000이므로 100001을 넣어 초기화함)

        return cycleCnt < 0 ? 0 : cycleCnt;
    }
}