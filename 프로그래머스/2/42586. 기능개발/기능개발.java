import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // (100-progresses) 
        // 남은 작업일 계산 후 저장 7, 3, 9
        // 현재 숫자보다 작은 숫자까지 배포 가능
        ArrayDeque<Integer> remainDays = new ArrayDeque<>(); // 남은 작업일 저장
        
        // 남은 작업일 계산 및 저장
        for (int i = 0; i < progresses.length; i++) {
            int remainDay = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                remainDay += 1;
            }
            remainDays.addLast(remainDay);
        }
        
        ArrayList<Integer> baepoNum = new ArrayList<>(); // 배포 개수 저장
        
        while (!remainDays.isEmpty()) {
            int baepo = remainDays.pollFirst(); // 배포 가능 날짜 7 3 9
            int cnt = 1; // 배포 가능 개수
            
            // 현재 숫자보다 작은 숫자까지 배포 가능
            while (!remainDays.isEmpty() && baepo >= remainDays.getFirst()) {
                remainDays.removeFirst();
                cnt++;
            }
            
            baepoNum.add(cnt);
        }
        
        int[] answer = new int[baepoNum.size()];
        
        for (int i = 0; i < baepoNum.size(); i++) {
            answer[i] = baepoNum.get(i);
        }
        
        return answer;
    }
}