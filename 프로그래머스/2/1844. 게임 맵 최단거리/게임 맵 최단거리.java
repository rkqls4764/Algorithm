import java.util.*;

class Solution {
    static int[][] map;
    static int[][] isVisit;
    static int[][] movement = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public int solution(int[][] maps) {
        map = maps;
        isVisit = new int[map.length][map[0].length];
        isVisit[0][0] = 1;
        
        int[] start = {0,0};
        bfs(start);
        
        int answer = -1;
        if (isVisit[map.length - 1][map[0].length - 1] != 0) {
            answer = isVisit[map.length - 1][map[0].length - 1];
        }
        
        return answer;
    }
    
    public void bfs(int[] start) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        
        queue.addLast(start);
        map[start[0]][start[1]] = 0;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            
            for (int i = 0; i < movement.length; i++) {
                int nextX = cur[0] + movement[i][0];
                int nextY = cur[1] + movement[i][1];
                
                if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
                    if (isVisit[nextX][nextY] == 0 && map[nextX][nextY] == 1) {
                        int[] next = {nextX, nextY};
                        queue.addLast(next);
                        isVisit[nextX][nextY] = isVisit[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }
    }
}