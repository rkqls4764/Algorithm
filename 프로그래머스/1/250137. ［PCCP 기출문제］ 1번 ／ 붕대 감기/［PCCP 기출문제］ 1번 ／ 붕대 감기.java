/*
총 시간 : 마지막 몬스터 공격 시간
연속 : 공격 안 당하면 +1, 공격 당하면 0
초당 회복 : 공격 안 당하면 +x
추가 회복 : 연속이 t초 되면 +y, 연속 0
공격 : 공격 시간 되면 -피해량
*/

class Solution { // [시전 시간 t, 초당 회복량 x, 추가 회복량 y], 최대 체력, [공격 시간, 피해량]
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        int combo = 0;
        int last_attack = 0;
        
        for (int[] attack : attacks) {
            int time = attack[0];
            int dmg = attack[1];
           
            // 초당 회복
            combo = time - last_attack - 1;
            answer += combo * x;
            
            // 추가 회복
            if (combo >= t) {
                answer += (combo / t) * y;
                combo = 0;
            }
            
            // 회복은 최대 체력 넘을 수 없음
            if (answer > health) {
                answer = health;
            }
            
            // 공격
            answer -= dmg;
            
            // 체력 0 되면 종료
            if (answer <= 0) {
                return -1;
            }
            
            // 마지막 공격 시간 재설정
            last_attack = time;
        }
        
        return answer;
    }
}