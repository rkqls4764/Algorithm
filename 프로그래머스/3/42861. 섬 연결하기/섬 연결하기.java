import java.util.*;

class Solution {
    ArrayList<Integer>[] graph;
    
    public void dfs(int start, boolean[] isVisit) {
        isVisit[start] = true;
        for (int next: graph[start]) {
            if (!isVisit[next]) {
                dfs(next, isVisit);
            }
        }
    }
    
    public boolean isConnect(int n) {
        boolean[] isVisit = new boolean[n];
        
        dfs(0, isVisit);
        
        for (boolean v : isVisit) {
            if (!v) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        /*
        costs -> 0: 시작섬, 1: 도착섬, 2: 비용
        비용 기준 내림차순 정렬, 순회하면서 제거해봄
        섬은 모두 연결돼야 함 (통로 없앨 때마다 연결 체크해야 함)
        */
        
        graph = new ArrayList[n];
        boolean[] isDelete = new boolean[costs.length];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < costs.length; i++) {
            graph[costs[i][0]].add(costs[i][1]);
            graph[costs[i][1]].add(costs[i][0]);
        }
        
        Arrays.sort(costs, (a, b) -> {  // 비용 기준 내림차순 정렬
           return Integer.compare(b[2], a[2]); 
        });
        
        for (int i = 0; i < costs.length; i++) {
            graph[costs[i][0]].remove(Integer.valueOf(costs[i][1]));
            graph[costs[i][1]].remove(Integer.valueOf(costs[i][0]));
            
            if (!isConnect(n)) {    // 전체 연결 안되면 복구
                graph[costs[i][0]].add(costs[i][1]);
                graph[costs[i][1]].add(costs[i][0]);
                continue;
            }
            
            isDelete[i] = true;
        }
        
        int answer = 0;
        
        for (int i = 0; i < costs.length; i++) {
            if (!isDelete[i]) {
                answer += costs[i][2];
            }
        }
        
        return answer;
    }
}