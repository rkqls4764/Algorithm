import java.util.*;

class Solution {
    public class Truck{
        int truckId = 0;
        int runTime = 0;
        
        Truck(int truckId, int runTime) {
            this.truckId = truckId;
            this.runTime = runTime;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        ArrayList<Truck> bridge = new ArrayList<>();
        
        int time = 0;           // 걸린 시간
        int truckIdx = 0;       // 트럭 인덱스
        int currentWeight = 0;  // 현재 다리 위에 올라간 트럭 무게
        
        while (truckIdx < truck_weights.length) {
            for (int i = 0; i < bridge.size(); i++) {
                if (bridge.get(i).runTime == bridge_length) {
                    currentWeight -= truck_weights[bridge.get(i).truckId];
                    bridge.remove(i);
                }
            }
            
            time++;
            
            if (currentWeight + truck_weights[truckIdx] <= weight) {
                currentWeight += truck_weights[truckIdx];
                bridge.add(new Truck(truckIdx++, 0));
            }
            
            for (int i = 0; i < bridge.size(); i++) {
                bridge.get(i).runTime++;
            }
        }
        
        // 다리 위에 남은 트럭들 지나가게 하기
        while (!bridge.isEmpty()) {
            for (int i = 0; i < bridge.size(); i++) {
                if (bridge.get(i).runTime == bridge_length) {
                    bridge.remove(i);
                }
            }
            
            time++;
            
            for (int i = 0; i < bridge.size(); i++) {
                bridge.get(i).runTime++;
            }
        }
        
        return time;
    }
}