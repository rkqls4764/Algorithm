import java.util.*;

class Solution {
    public static int[][] map;
    public static boolean[][] isVisit;
    public static int[][] movement = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        makeMap(rectangle);
        dfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, 0);
        
        return answer / 2;
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
        isVisit = new boolean[(maxX + 1) * 2][(maxY + 1) * 2];
        
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
    
    public void dfs(int curX, int curY, int itemX, int itemY, int cnt) {
        if (curX == itemX && curY == itemY) {
            if (answer > cnt) {
                answer = cnt;
            }
            return;
        }
        
        for (int[] move : movement) {
            int[] next = {curX + move[0], curY + move[1]};
            if (0 < next[0] && next[0] < map.length && 0 < next[1] && next[1] < map[0].length) {
                if (!isVisit[next[0]][next[1]] && map[next[0]][next[1]] == 1) {
                    isVisit[next[0]][next[1]] = true;
                    dfs(next[0], next[1], itemX, itemY, cnt + 1);
                    isVisit[next[0]][next[1]] = false;
                }
            }
        }
        
    }
}