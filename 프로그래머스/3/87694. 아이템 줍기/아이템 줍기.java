import java.util.*;

class Solution {
    public static int[][] map;
    public static int[][] cnt;
    public static int[][] movement = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        makeMap(rectangle);
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        
        return cnt[itemX * 2][itemY * 2] / 2;
    }
    
    public void makeMap(int[][] rectangle) {
        int maxX = 0;
        int maxY = 0;
        
        for (int[] rect : rectangle) {
            if (maxX < rect[2]) {
                maxX = rect[2];
            }
            if (maxY < rect[3]) {
                maxY = rect[3];
            }
        }
        
        map = new int[(maxX + 1) * 2][(maxY + 1) * 2];
        cnt = new int[(maxX + 1) * 2][(maxY + 1) * 2];
        
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if (map[x][y] != 2) { // 현재 위치가 다각형 내부이면 2 유지
                            if (y == y1 || y == y2 || x == x1 || x == x2) { // 경로는 1을 저장
                            map[x][y] = 1;
                        }
                        else { // 다각형 내부는 2를 저장
                            map[x][y] = 2;
                        }
                    }
                }
            }
        }
    }
    
    public void bfs(int characterX, int characterY, int itemX, int itemY) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[] start = {characterX, characterY};
        queue.addLast(start);
        
        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            
            if (cur[0] == itemX && cur[1] == itemY) {
                return;
            }
            
            for (int[] move : movement) {
                int[] next = {cur[0] + move[0], cur[1] + move[1]};
                if (0 < next[0] && next[0] < map.length && 0 < next[1] && next[1] < map[0].length) {
                    if (cnt[next[0]][next[1]] == 0 && map[next[0]][next[1]] == 1) {
                        queue.addLast(next);
                        cnt[next[0]][next[1]] = cnt[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }
    }
}