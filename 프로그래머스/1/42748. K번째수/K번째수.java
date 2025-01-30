import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int answerIdx = 0;
        
        for (int n = 0; n < answer.length; n++) {
            int i = commands[n][0];
            int j = commands[n][1];
            int k = commands[n][2];
            
            int[] arr = new int[j - i + 1];
            int idx = 0;
            
            for (int m = i - 1; m < j; m++) {
                arr[idx++] = array[m];
            }
            
            Arrays.sort(arr);
            answer[answerIdx++] = arr[k - 1];
        }
        
        return answer;
    }
}