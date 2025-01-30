import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N+1][10]; // 길이가 n이고 마지막 숫자가 x인 숫자의 수를 저장

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int n = 2; n <= N; n++) {
            dp[n][0] = dp[n-1][1];
            dp[n][9] = dp[n-1][8];

            for (int i = 1; i <= 8; i++) {
                dp[n][i] = (dp[n-1][i-1] + dp[n-1][i+1]) % 1000000000;
            }
        }

        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + dp[N][i]) % 1000000000;
        }

        System.out.print(answer);
    }
}