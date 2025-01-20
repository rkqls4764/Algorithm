import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) { // n이 1인 경우 dp[2] = 3 부분에서 인덱스 에러 발생, 미리 종료해야 함
            System.out.println(1);
            return;
        }
        
        int[] dp = new int[n+1]; // 해당 칸까지 채울 수 있는 경우의 수 저장
        dp[1] = 1; // 1x2 1개
        dp[2] = 3; // 1x2 2개 or 2x1 2개 or 2x2 1개

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }

        System.out.println(dp[n]);
    }
}