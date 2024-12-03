import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> wireList;
    static int answer;
    
    public int solution(int n, int[][] wires) {
        wireList = new ArrayList<>();
        answer = n;
        
        for (int i = 0; i <= n; i++) {
            wireList.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            wireList.get(wire[0]).add(wire[1]);
            wireList.get(wire[1]).add(wire[0]);
        }
        
        for (int[] wire : wires) {
            cutWire(wire);
        }
        
        return answer;
    }
    
    public void cutWire(int[] wire) {
        wireList.get(wire[0]).remove(Integer.valueOf(wire[1]));
        wireList.get(wire[1]).remove(Integer.valueOf(wire[0]));
        
        boolean[] isVisited = new boolean[wireList.size()];
        
        for (int i = 1; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        
        count(isVisited);
        
        wireList.get(wire[0]).add(wire[1]);
        wireList.get(wire[1]).add(wire[0]);
    }
    
    public void count(boolean[] isVisited) {
        ArrayList<Integer> cntList = new ArrayList<>();
        
        for (int i = 1; i < wireList.size(); i++) {
            if (!isVisited[i]) {
                int cnt = 0;
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                queue.addLast(i);
            
                while (!queue.isEmpty()) {
                    int idx = queue.removeFirst();
                    
                    for (int j = 0; j < wireList.get(idx).size(); j++) {
                        if (!isVisited[wireList.get(idx).get(j)]) {
                            queue.addLast(wireList.get(idx).get(j));
                        }
                    }
                    
                    if (!isVisited[idx]) {
                        isVisited[idx] = true;
                        cnt++;
                    }
                }
                
                cntList.add(cnt);
            }
        }
        
        int result = Math.abs(cntList.get(0) - cntList.get(1));
        if (answer > result) {
            answer = result;
        }
    }
}