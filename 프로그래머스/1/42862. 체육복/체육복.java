import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] arr = new int[n + 2]; // -1: 도난당함, 0: 있음, 1: 여벌 있음
        
        for (int r : reserve) {
            arr[r] = 1;
        }
        
        for (int l : lost) {
            arr[l] -= 1;
        }
        
        Arrays.sort(lost);
        
        for (int l : lost) {
            if (arr[l - 1] == 1) {
                arr[l] = 0;
                arr[l - 1] = 0;
                System.out.println((l - 1) + "번째가 " + (l) + "한테 빌려줌");
            }
            else if (arr[l + 1] == 1) {
                arr[l] = 0;
                arr[l + 1] = 0;
                System.out.println((l + 1) + "번째가 " + (l) + "한테 빌려줌");
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            if (arr[i] == 0 || arr[i] == 1) {
                answer++;
            }
        }
        
        return answer;
    }
}