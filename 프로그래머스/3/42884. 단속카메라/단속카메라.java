import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        boolean[] isVisited = new boolean[routes.length];
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> { // 1. 진입점 오름차순 정렬, 2. 나간점 오름차순 정렬
            // if (a[0] == b[0]) {
            //     return Integer.compare(a[1], b[1]);
            // }
            return Integer.compare(a[0], b[0]);
        });
        
        for (int i = 0; i < routes.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            
            int start = routes[i][0];
            int end = routes[i][1];
            isVisited[i] = true;
            
            for (int j = i + 1; j < routes.length; j++) {
                if (isVisited[j]) {
                    continue;
                }
                
                if (routes[j][1] < start || end < routes[j][0]) { // 방문했거나 범위 밖이면 종료
                    continue;
                }
                
                start = Math.max(start, routes[j][0]);
                end = Math.min(end, routes[j][1]);
                isVisited[j] = true;
            }
            
            answer++;
        }
        
        return answer;
    }
}