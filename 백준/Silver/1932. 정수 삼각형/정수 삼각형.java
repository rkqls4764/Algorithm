import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] += dp[i - 1][0];       // 맨 왼쪽
            dp[i][i] += dp[i - 1][i - 1];   // 맨 오른쪽
            
            for (int j = 1; j <= i - 1; j++) {   // 가운데
                int bigNum = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                dp[i][j] += bigNum;
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (answer < dp[n - 1][i]) {
                answer = dp[n - 1][i];
            }
        }

        System.out.print(answer);
    }
}