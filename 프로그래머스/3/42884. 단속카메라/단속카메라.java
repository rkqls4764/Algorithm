import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        boolean[] isVisit = new boolean[routes.length];
        int answer = 0;
        
        /*
        routes 순회하면서
        start, end를 현재로 지정
        다음 routes를 순회하면서
        현재 start, end에 안 겹치면 패스
        겹치면 start, end 수정 (겹쳐지는 부분으로)
        이번에 본 routes 방문 체크
        */
        
        Arrays.sort(routes, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        for (int i = 0; i < routes.length; i++) {
            if (isVisit[i]) {
                // System.out.println(i + "번 패스");
                continue;
            }
            
            int start = routes[i][0];
            int end = routes[i][1];
            isVisit[i] = true;
            // System.out.println(i + "번 사용(" + start + ", " + end + ")");
            
            for (int j = i + 1; j < routes.length; j++) {
                if (isVisit[j]) {
                    // System.out.println(j + "번 방문했음");
                    continue;
                }
                
                if (routes[j][1] < start || end < routes[j][0]) { // 방문했거나 범위 밖이면 종료
                    // System.out.println(j + "번 범위 밖임");
                    continue;
                }
                
                start = Math.max(start, routes[j][0]);
                end = Math.min(end, routes[j][1]);
                isVisit[j] = true;
                // System.out.println(j + "번으로 갱신(" + start + ", " + end + ")");
            }
            
            answer++;
        }
        
        return answer;
    }
}