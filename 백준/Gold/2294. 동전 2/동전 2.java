import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        int[] dp = new int[k + 1]; // n을 만드는데 필요한 동전 최소 개수

        for (int i = 1; i <= k; i++) {
            dp[i] = 100001; // 동전 가치 최댓값이 100000이다.
        }

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
            if (coin[i] <= k) {
                dp[coin[i]] = 1;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (i + coin[j] <= k) {
                    dp[i + coin[j]] = Math.min(dp[i + coin[j]], dp[i] + 1);
                }
            }
        }

        if (dp[k] == 100001) {
            System.out.print(-1);
        }
        else {
            System.out.print(dp[k]);
        }
    }
}