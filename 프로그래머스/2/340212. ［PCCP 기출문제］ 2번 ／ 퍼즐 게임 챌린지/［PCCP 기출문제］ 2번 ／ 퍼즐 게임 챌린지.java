/*
diff <= level이면, time_cur 만큼 시간 사용하고 해결
diff > level이면, 총 diff - level 번 틀림
틀리면 time_cur + time_prev 만큼 시간 사용, 다시 풀 때 time_cur 만큼 시간 사용

난이도, 소요 시간, 숙련도는 양의 정수
제한 시간 안에 모든 퍼즐을 풀 수 있는 숙련도(레벨) 최소값을 리턴
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int low = 1;
        int high = 0;
        
        for (int d : diffs) {
            high = Math.max(d, high);
        }
        
        while (low <= high) {
            int level = (low + high) / 2;
            
            boolean isSuccess = solvePuzzle(diffs, times, limit, level);
            
            // 현재 레벨로 성공한 경우 최댓값 낮춤
            if (isSuccess) {
                high = level - 1;
            }
            // 실패한 경우 최솟값 올림
            else {
                low = level + 1;
            }
        }
        
        return low;
    }
    
    public boolean solvePuzzle(int[] diffs, int[] times, long limit, int level) {
        int idx = 0;
        long time = 0;
        
        while (idx < diffs.length) {
            // 퍼즐 풀이 성공한 경우
            if (diffs[idx] <= level) {
                time += times[idx];
            }
            // 퍼즐 풀이 실패한 경우
            else {
                time += (times[idx] + times[idx - 1]) * (diffs[idx] - level) + times[idx];
            }
            
            // 제한 시간을 넘은 경우
            if (time > limit) {
                return false;
            }
            
            idx++;
        }
        
        return true;
    }
}