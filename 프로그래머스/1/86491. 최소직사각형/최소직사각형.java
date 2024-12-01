import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            int width = sizes[i][0];
            int height = sizes[i][1];
            
            // 긴 변을 가로로 저장
            if (width < height) {
                width = sizes[i][1];
                height = sizes[i][0];
            }
            
            if (maxWidth < width) {
                maxWidth = width;
            }
            
            if (maxHeight < height) {
                maxHeight = height;
            }
        }
        
        return maxWidth * maxHeight;
    }
}