import java.util.*;

class Solution {
    static boolean[] isVisited;
    static int stamina;
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        isVisited = new boolean[dungeons.length];
        stamina = k;
        answer = 0;
        
        goDungeon(dungeons, 0);
        
        return answer;
    }
    
    public void goDungeon(int[][] dungeons, int depth) {
        if (answer < depth) {
            answer = depth;
        }
        
        if (depth == dungeons.length) {
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!isVisited[i] && stamina >= dungeons[i][0]) {
                isVisited[i] = true;
                stamina -= dungeons[i][1];
                
                goDungeon(dungeons, depth + 1);
                
                stamina += dungeons[i][1];
                isVisited[i] = false;
            }
        }
    }
}