import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            switch(c) {
                case '(' :
                    stack.push(c);
                    break;
                    
                case ')' :
                    if (stack.isEmpty()) { // 스택이 비어있는데 ')'가 들어오면 false
                        return false;
                    }
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                    break;
            }
        }
        
        // 문자열 순회 끝났는데 스택이 비어있지 않으면 false
        if (!stack.isEmpty()) {
            return false;
        }
        
        return true;
    }
}