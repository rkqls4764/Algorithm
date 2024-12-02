import java.util.*;

class Solution {
    static ArrayList<Boolean> isVisited;
    static int stamina;
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        isVisited = new ArrayList<>(dungeons.length);
        stamina = k;
        answer = 0;
        
        for (int i = 0; i < dungeons.length; i++) {
            isVisited.add(false);
        }
        
        goDungeon(dungeons, 0, 0);
        
        return answer;
    }
    
    public void goDungeon(int[][] dungeons, int cnt, int depth) {
        if (answer < cnt) {
            answer = cnt;
        }
        
        if (!isVisited.contains(false)) {
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!isVisited.get(i) && stamina >= dungeons[i][0]) {
                isVisited.set(i, true);
                stamina -= dungeons[i][1];
                
                goDungeon(dungeons, cnt + 1, depth + 1);
                
                stamina += dungeons[i][1];
                isVisited.set(i, false);
            }
        }
    }
}