/*
m x n
열로 뚫었을 때 지나가기만 하면 다 뽑음
최대한 많이 뽑았을 때 석유량 리턴

bfs로 연결된 석유 수 찾기 -> 열이 인덱스인 배열에 저장?
*/

import java.util.*;

class Solution {
    public static int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    public static Set<Integer> visitCol;
    public static int cnt = 0;
    
    public int solution(int[][] land) {  
        int[] colOil = new int[land[0].length];
        
        // 땅 순회
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[row].length; col++) {
                // 땅이면 패스
                if (land[row][col] == 0) {
                    continue;
                }
                
                // 현재 석유 덩어리 크기 체크
                cnt = 0;
                visitCol = new HashSet<>();
                oilCheck(land, row, col);
                
                // 열 기준 석유량 저장
                for (int c : visitCol) {
                    colOil[c] += cnt;
                }
            }
        }
        
        int answer = 0;
        
        for (int c : colOil) {
            answer = Math.max(answer, c);
        }
        
        return answer;
    }
    
    /* BFS로 석유 덩어리 찾기 */
    public void oilCheck(int[][] land, int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addFirst(new int[] { x, y });
        
        // 방문 체크
        land[x][y] = 0;
        cnt++;
        
        // 방문한 열 저장
        visitCol.add(y);
        
        while (!queue.isEmpty()) {
            int[] cur = queue.removeLast();
        
            // 인접 체크
            for (int[] d : directions) {
                int nextX = cur[0] + d[0];
                int nextY = cur[1] + d[1];
                
                // 범위 안이고 석유면 이동
                if (0 <= nextX && nextX < land.length && 0 <= nextY && nextY < land[0].length) {
                    if (land[nextX][nextY] == 1) {
                        // 방문 체크
                        land[nextX][nextY] = 0;
                        cnt++;
                        
                        // 방문한 열 저장
                        visitCol.add(nextY);
                        
                        queue.addFirst(new int[] { nextX, nextY });
                    }
                }
            }
        }
    }
}
