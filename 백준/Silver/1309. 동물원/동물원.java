import java.io.*;
import java.util.*;

class Main {
    public static final int NONE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] dp = new int[3][N+1]; //2xN 크기에 사자를 배치할 수 있는 경우의 수
        dp[NONE][1] = 1;
        dp[LEFT][1] = 1;
        dp[RIGHT][1] = 1;

        for (int n = 2; n <= N; n++) {
            dp[NONE][n] = (dp[NONE][n-1] + dp[LEFT][n-1] + dp[RIGHT][n-1]) % 9901; // 사자를 배치하지 않았으면 모든 상태 가능
            dp[LEFT][n] = (dp[RIGHT][n-1] + dp[NONE][n-1]) % 9901; // 사자를 왼쪽에 배치했으면 오른쪽에 두거나 빈 상태 가능
            dp[RIGHT][n] = (dp[LEFT][n-1] + dp[NONE][n-1]) % 9901; // 사자를 오른쪽에 배치했으면 왼쪽에 두거나 빈 상태 가능
        }

        int answer = (dp[NONE][N] + dp[LEFT][N] + dp[RIGHT][N]) % 9901;
        System.out.print(answer);
    }
}