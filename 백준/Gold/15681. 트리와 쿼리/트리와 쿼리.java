import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<Integer>[] graph;
    public static boolean[] isVisit;
    public static int[] subTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int R = Integer.parseInt(st.nextToken());   // 루트의 번호
        int Q = Integer.parseInt(st.nextToken());   // 쿼리의 수

        graph = new ArrayList[N+1];
        isVisit = new boolean[N+1];
        subTree = new int[N+1];

        for (int n = 1; n <= N; n++) {
            graph[n] = new ArrayList<>();
        }

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        find(R);

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            bw.write(subTree[U] + "\n");
        }

        bw.flush();
    }

    public static int find(int node) {
        isVisit[node] = true;
        int cnt = 1;

        for (int next : graph[node]) {
            if (!isVisit[next]) {
                cnt += find(next);
            }
        }

        subTree[node] = cnt;
        return cnt;
    }
}