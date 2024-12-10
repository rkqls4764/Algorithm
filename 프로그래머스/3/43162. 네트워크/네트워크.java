class Solution {
    public static int[][] computer;
    public static boolean[] isVisit;
    public static int num;
    public static int answer;
    
    public int solution(int n, int[][] computers) {
        computer = computers;
        isVisit = new boolean[n];
        num = n;
        answer = n;
        
        for (int start = 0; start < n; start++) {
            if (!isVisit[start]) {
                System.out.println("for " + start);
                dfs(start);
            }
        }
        
        return answer;
    }
    
    public void dfs(int start) {
        isVisit[start] = true;
        
        for (int next = 0; next < num; next++) {
            if (!isVisit[next] && computer[start][next] == 1) {
                System.out.println("dfs " + next);
                dfs(next);
                answer--;
            }
        }
    }
}