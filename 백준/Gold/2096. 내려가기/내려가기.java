import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int r = 0; r < 3; r++) {
                arr[n][r] = Integer.parseInt(st.nextToken());
            }
        }

        maxDp[0][0] = arr[0][0];
        maxDp[0][1] = arr[0][1];
        maxDp[0][2] = arr[0][2];

        minDp[0][0] = arr[0][0];
        minDp[0][1] = arr[0][1];
        minDp[0][2] = arr[0][2];

        for (int n = 1; n < N; n++) {
            maxDp[n][0] = Math.max(maxDp[n-1][0], maxDp[n-1][1]) + arr[n][0];
            maxDp[n][1] = Math.max(maxDp[n-1][0], Math.max(maxDp[n-1][1], maxDp[n-1][2])) + arr[n][1];
            maxDp[n][2] = Math.max(maxDp[n-1][1], maxDp[n-1][2]) + arr[n][2];

            minDp[n][0] = Math.min(minDp[n-1][0], minDp[n-1][1]) + arr[n][0];
            minDp[n][1] = Math.min(minDp[n-1][0], Math.min(minDp[n-1][1], minDp[n-1][2])) + arr[n][1];
            minDp[n][2] = Math.min(minDp[n-1][1], minDp[n-1][2]) + arr[n][2];
        }

        int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
        int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));

        System.out.print(max + " " + min);
    }
}