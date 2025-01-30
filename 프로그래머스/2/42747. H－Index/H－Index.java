import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 오름차순 정렬
        
        int h = citations.length;
        
        while (true) {
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= h && citations.length - i >= h) { // h번 이상 인용된 논문이 h편 이상인 경우
                    return h;
                }
            }
            
            h--;
        }
    }
}