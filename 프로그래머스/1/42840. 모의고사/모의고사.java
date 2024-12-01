import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] C = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int idxA = 0;
        int idxB = 0;
        int idxC = 0;
        
        int[] answerCnt = new int[3];
        
        for (int ans : answers) {
            if (A[idxA] == ans) {
                answerCnt[0]++;
            }
            if (B[idxB] == ans) {
                answerCnt[1]++;
            }
            if (C[idxC] == ans) {
                answerCnt[2]++;
            }
            
            idxA = (idxA + 1) % A.length;
            idxB = (idxB + 1) % B.length;
            idxC = (idxC + 1) % C.length;
        }
        
        int max = 0;
        for (int i = 0; i < answerCnt.length; i++) {
            if (max < answerCnt[i]) {
                max = answerCnt[i];
            }
        }
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        for (int i = 0; i < answerCnt.length; i++) {
            if (max == answerCnt[i]) {
                answerList.add(i + 1);
            }
        }
        
        int[] answer = new int[answerList.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}