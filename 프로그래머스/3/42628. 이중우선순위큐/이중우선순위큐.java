import java.util.*;

class Solution {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    public int[] solution(String[] operations) {
        for (String s: operations) {
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if (op.equals("I")) {
                minHeap.offer(num);
                maxHeap.offer(num);
            }
            else if (op.equals("D")) {
                if (num == 1 && !maxHeap.isEmpty()) {
                    minHeap.remove(maxHeap.poll());
                }
                else if (num == -1 && !minHeap.isEmpty()) {
                    maxHeap.remove(minHeap.poll());
                }
            }
        }
        
        int[] answer = { 0, 0 };
        
        if (minHeap.size() != 0 && maxHeap.size() != 0) {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        
        return answer;
    }
}