import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        // 위, 아래: 아스키 코드로 가까운 거 찾아서 더하기
        for (int i = 0; i < name.length(); i++) {
            char target = name.charAt(i);
            answer += Math.min(target - 'A', 'Z' - target + 1);
        }
        
        // 왼쪽, 오른쪽: 처음에 옆이 A가 아닌 곳으로 가기
        int move = name.length() - 1;

        for(int i = 0; i < name.length(); i++){
            int index = i + 1;
            
            while(index < name.length() && name.charAt(index) == 'A'){
                index++;
            }
            
            move = Math.min(move, i * 2 + name.length() - index);
            move = Math.min(move, (name.length() - index) * 2 + i);
        }
        
        return answer + move;
    }
}