import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;
        
        // yellow가 1 이상이므로 가로 길이는 3 이상
        for (int width = 3; width < total; width++) {
            if (total % width != 0) {
                continue;
            }
            
            int height = total / width;
            
            if (brown == (width * 2) + ((height - 2) * 2)) {
                // 카펫의 가로 길이는 세로 길이보다 길거나 같다.
                if (width >= height) {
                    answer[0] = width;
                    answer[1] = height;
                }
                else {
                    answer[0] = height;
                    answer[1] = width;
                }
            }
        }
        
        return answer;
    }
}