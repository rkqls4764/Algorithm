import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.add(s);
        }
        
        int answer = 0;
        
        while(pq.size() > 1) {
            int min = pq.poll();
            
            if (min >= K) {
                return answer;
            }
            
            int min2 = pq.poll();
            
            int newFood = min + (min2 * 2);
            pq.add(newFood);
            
            answer++;
        }
        
        if (pq.poll() >= K) {
            return answer;
        }
        
        return -1;
    }
}