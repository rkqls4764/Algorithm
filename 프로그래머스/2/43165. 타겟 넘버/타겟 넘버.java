import java.util.*;

class Solution {
    static int[] numberList;
    static int targetNum;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        numberList = numbers;
        targetNum = target;
        answer = 0;
        
        dfs(numberList[0], 1);
        dfs(numberList[0] * (-1), 1);
        
        return answer;
    }
    
    public void dfs(int sum, int depth) {
        if (depth == numberList.length) {
            if (sum == targetNum) {
                answer++;
            }
            return;
        }
        
        dfs(sum + numberList[depth], depth + 1);
        dfs(sum - numberList[depth], depth + 1);
    }
}