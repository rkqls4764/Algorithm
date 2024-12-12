import java.util.*;

class Solution {
    public static ArrayList<String> route;
    public static String[][] ticketList;
    public static boolean[] isUsed;
    public static String[] answer;
    
    public String[] solution(String[][] tickets) {
        route = new ArrayList<>();
        ticketList = tickets;
        isUsed = new boolean[tickets.length];
        
        Arrays.sort(ticketList, (o1, o2) -> {
            if (o1[1].compareTo(o2[1]) != 0) { // 도착지가 다르면 도착지 기준 오름차순 정렬
                return o1[1].compareTo(o2[1]);
            }
            return o1[0].compareTo(o2[0]); // 도착지가 같으면 출발지 기준 오름차순 정렬
        });
        
        route.add("ICN");
        dfs("ICN");
        
        return answer;
    }
    
    public void dfs(String cur) {
        if (answer != null) { // 처음 경로를 찾으면 다른 경로를 찾을 필요 없음
            return;
        }
        
        if (route.size() == ticketList.length + 1) {
            answer = new String[ticketList.length + 1];
            for (int i = 0; i < route.size(); i++) {
                answer[i] = route.get(i);
            }
            return;
        }
            
        for (int i = 0; i < ticketList.length; i++) {
            if (!isUsed[i]) {
                String start = ticketList[i][0];
                String end = ticketList[i][1];
            
                if (cur.equals(start)) {
                    isUsed[i] = true;
                    route.add(end);
                    dfs(end);
                    route.remove(route.size() - 1); // 맨 뒤에 값을 제거해야 하므로 값으로 제거하면 안 됨
                    isUsed[i] = false;
                }
            }
        }
    }
}