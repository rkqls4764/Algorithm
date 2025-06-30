/*
- 10초 전으로 이동 : "prev", 현재 위치에서 10초 전으로 이동, 현재 10초 미만이면 처음 위치(00:00)로 이동
- 10초 후로 이동 : "next", 현재 위치에서 10초 후로 이동, 남은 시간 10초 미만이면 마지막 위치(동영상 길이)로 이동
- 오프닝 건너뛰기 : 현재 위치가 오프닝 구간이면 오프닝 끝나는 위치로 이동

mm:ss 형식
분, 초가 한 자리면 0을 붙여 두 자리로 나타낸다
commands의 원소는 prev, next
*/

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = skipOpening(pos, op_start, op_end);
        
        for (String c : commands) {
            switch (c) {
                case "prev":
                    answer = skipOpening(prev(answer), op_start, op_end);
                    break;
                case "next":
                    answer = skipOpening(next(answer, video_len), op_start, op_end);
                    break;
            }
        }
        
        return answer;
    }
    
    /* 10초 전으로 이동 : "prev", 현재 위치에서 10초 전으로 이동, 현재 10초 미만이면 처음 위치(00:00)로 이동 */
    public String prev(String pos) {
        String[] times = pos.split(":");
        int sec = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        
        // 현재 10초 미만이면 처음 위치로 이동
        if (sec < 10) {
            return "00:00";
        }
        
        // 현재 위치에서 10초 전으로 이동
        sec -= 10;
        
        int m = sec / 60;
        int s = sec % 60;
        
        String mm = (m < 10) ? "0" + Integer.toString(m) : Integer.toString(m);
        String ss = (s < 10) ? "0" + Integer.toString(s) : Integer.toString(s);
        
        return mm + ":" + ss;
    }
    
    /* 10초 후로 이동 : "next", 현재 위치에서 10초 후로 이동, 남은 시간 10초 미만이면 마지막 위치(동영상 길이)로 이동 */
    public String next(String pos, String video_len) {
        String[] times = pos.split(":");
        String[] v_times = video_len.split(":");
        
        int sec = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        int v_sec = Integer.parseInt(v_times[0]) * 60 + Integer.parseInt(v_times[1]);
        
        // 남은 시간 10초 미만이면 마지막 위치로 이동
        if (v_sec - sec < 10) {
            return video_len;
        }
        
        // 현재 위치에서 10초 후로 이동       
        sec += 10;
        
        int m = sec / 60;
        int s = sec % 60;
        
        String mm = (m < 10) ? "0" + Integer.toString(m) : Integer.toString(m);
        String ss = (s < 10) ? "0" + Integer.toString(s) : Integer.toString(s);
        
        return mm + ":" + ss;
    }
    
    /* 오프닝 건너뛰기 : 현재 위치가 오프닝 구간이면 오프닝 끝나는 위치로 이동 */
    public String skipOpening(String pos, String op_start, String op_end) {
        String[] times = pos.split(":");
        String[] s_times = op_start.split(":");
        String[] e_times = op_end.split(":");
        
        int p_sec = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        int s_sec = Integer.parseInt(s_times[0]) * 60 + Integer.parseInt(s_times[1]);
        int e_sec = Integer.parseInt(e_times[0]) * 60 + Integer.parseInt(e_times[1]);
        
        // 현재 위치가 오프닝 구간이면 오프닝 끝나는 위치로 이동
        if (s_sec <= p_sec && p_sec <= e_sec) {
            return op_end;
        }
        
        return pos;
    }
}