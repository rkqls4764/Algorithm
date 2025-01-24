import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] dp = new int[k + 1][T+1]; // k번째 동전까지 사용해서 T원을 만들 수 있는 경우의 수
        dp[0][0] = 1; // 0원을 만들 수 있는 경우의 수는 1이다.

        for (int i = 1; i <= k; i++) { // 동전 순회
            st = new StringTokenizer(br.readLine());
            int coinPrice = Integer.parseInt(st.nextToken());
            int coinCnt = Integer.parseInt(st.nextToken());

            for (int value = 0; value <= T; value++) {
                for (int cnt = 0; cnt <= coinCnt; cnt++) { // 사용할 동전 개수
                    int nextValue = value + coinPrice * cnt; // 만들 수 있는 다음 값

                    if (nextValue > T) {
                        break;
                    }

                    dp[i][nextValue] += dp[i - 1][value];
                }
            }
        }

        System.out.print(dp[k][T]);
    }
}