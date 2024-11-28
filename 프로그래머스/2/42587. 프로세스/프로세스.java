import java.util.*;

class Solution {
    public class Process{
        int priority;       // 우선순위
        boolean isExcuted;  // 실행했는지 여부
        
        Process(int priority, boolean isExcuted) {
            this.priority = priority;
            this.isExcuted = isExcuted;
        }
    }
    
    public int solution(int[] priorities, int location) {
        /*
        원형큐 형식
        location은 0부터 시작
        ArrayList에 priorities 저장
        순회하며 최대값, 최대값이 있는 인덱스 저장
        해당 인덱스부터 프로세스 실행, 순회방법은 원형큐 형식으로
        실행한 프로세스는 제거
        */
        
        ArrayList<Process> processes = new ArrayList<>();
        int maxIdx = 0; // 우선순위가 가장 큰 인덱스
        int max = 0; // 가장 큰 우선순위
        
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            processes.add(new Process(priority, false));
            
            if (max < priority) {
                maxIdx = i;
                max = priority;
            }
        }
        
        int answer = 0; // 실행 횟수
        int idx = maxIdx;
        
        // location번째 프로세스가 실행될 때까지 반복
        while (processes.get(location).isExcuted == false) {
            // 실행하지 않은 프로세스면 true로 변경, 실행 횟수 증가
            if (processes.get(idx).priority == max && processes.get(idx).isExcuted == false) {
                processes.get(idx).isExcuted = true;
                answer += 1;
                
                // 실행하지 않은 프로세스 중 가장 큰 우선순위 찾기
                max = 0;
                for (Process p : processes) {
                    if (p.isExcuted == false) {
                        if (max < p.priority) {
                            max = p.priority;
                        }
                    }
                }
            }
            
            idx = (idx + 1) % processes.size();
        }
        
        return answer;
    }
}