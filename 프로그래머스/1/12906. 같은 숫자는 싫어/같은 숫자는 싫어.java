import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        
        for (int num : arr) {
            if (arrayDeque.isEmpty()) { //비어있으면 넣기
                arrayDeque.push(num);
            }
            else { //비어있지 않은데 이전 숫자랑 다르면 넣기
                if (arrayDeque.peek() != num) {
                    arrayDeque.push(num);
                }
            }
        }
        
        int[] answer = new int[arrayDeque.size()];
        int idx = 0;
        
        while (!arrayDeque.isEmpty()) {
            answer[idx++] = arrayDeque.pollLast();
        }

        return answer;
    }
}