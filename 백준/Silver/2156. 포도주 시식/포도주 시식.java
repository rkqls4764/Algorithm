import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n+1];

        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[3][n+1]; // 해당 잔까지 마실 수 있는 포도주의 최대 양
        dp[0][1] = 0; // 0: n번째를 안 마심
        dp[1][1] = wine[1]; // 1: n-1번째를 안 마시고 n번째를 마심
        dp[2][1] = 0; // 2: n-1번째를 마시고 n번째를 마심

        for (int i = 2; i <= n; i++) {
            dp[0][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1]));
            dp[1][i] = dp[0][i-1] + wine[i];
            dp[2][i] = dp[1][i-1] + wine[i];
        }

        System.out.print(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n])));
    }
}