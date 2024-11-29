import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i]; // 현재 주식 가격
            int time = 0; // 가격이 떨어지지 않은 기간
            
            for (int j = i + 1; j < prices.length; j++) {
                time++;
                
                if (price > prices[j]) {
                    break;
                }
            }
            
            answer[i] = time;
        }
        
        return answer;
    }
}