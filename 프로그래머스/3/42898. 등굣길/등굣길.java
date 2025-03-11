import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        
        for (int[] p : puddles) {
            dp[p[0]][p[1]] = -1;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (dp[i][j] != -1) {
                    dp[i][j] += Math.max(dp[i - 1][j], 0) + Math.max(dp[i][j - 1], 0);
                    dp[i][j] %= 1000000007;
                }
            }
        }
        
        return dp[m][n];
    }
}