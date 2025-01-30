import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] items = new int[N+1][2]; // 물건 무게, 가치
        int[][] dp = new int[N+1][K+1]; // N번째 물건을 무게가 K인 가방에 넣을 때 넣을 수 있는 물건의 최대 가치

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[n][0] = W;
            items[n][1] = V;
        }

        for (int n = 1; n <= N; n++) { // 물건 순회
            for (int k = 1; k <= K; k++) { // 가방 무게 순회 (1~K)
                if (items[n][0] <= k) { // 현재 물건을 넣을 수 있음
                    dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-items[n][0]] + items[n][1]); // 기존값과 이번 물건 가치를 더한 값 중 큰 값 저장
                }
                else { // 현재 물건을 넣을 수 없음
                    dp[n][k] = dp[n-1][k]; // 기존값 저장
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}