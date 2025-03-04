import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 2];
        int[] P = new int[N + 2];

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[n] = Integer.parseInt(st.nextToken());
            P[n] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2]; // n일 동안 상담을 했을 때 최대 수익

        for (int i = 1; i <= N + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]); // 전 날에 수익이 더 많으면 그걸 가져옴

            if (i + T[i] > N + 1) { // 퇴사 날까지 일을 끝낼 수 없음
                continue;
            }

            dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]); // 이 일을 하지 않는 경우와 일을 하는 경우 중 더 많은 수익을 저장
        }

        System.out.print(dp[N + 1]);
    }
}