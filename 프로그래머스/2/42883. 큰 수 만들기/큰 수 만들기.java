import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int count = k;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (char n : number.toCharArray()) {
            // 제거 횟수 있고, 스택 안 비었고, top이 현재 숫자보다 작으면 top 제거
            while (count > 0 && !stack.isEmpty() && stack.getFirst() < n) {
                stack.removeFirst();
                count--;
            }
            
            stack.addFirst(n);
        }
        
        // 뒤에서 남은 k만큼 제거
        StringBuilder result = new StringBuilder();
        int size = stack.size() - count;
        
        for (int i = 0; i < size; i++) {
            result.append(stack.pollLast());
        }
        
        return result.toString();
    }
}