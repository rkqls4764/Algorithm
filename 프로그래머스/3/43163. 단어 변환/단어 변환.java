import java.util.*;

class Solution {
    static ArrayList<String> wordList;
    static boolean[] isVisit;
    static int answer;
    
    public int solution(String begin, String target, String[] words) {
        wordList = new ArrayList<>(words.length);
        isVisit = new boolean[words.length];
        answer = Integer.MAX_VALUE;
        
        for (String word : words) {
            wordList.add(word);
        }
        
        // target이 words 안에 없으면 0 리턴
        if (!wordList.contains(target)) {
            return 0;
        }
        
        dfs(begin, target, 0);
    
        return answer;
    }
    
    public void dfs(String cur, String target, int cnt) {
        if (cur.equals(target)) {
            if (answer > cnt) {
                answer = cnt;
            }
            return;
        }
        
        for (int i = 0; i < wordList.size(); i++) {
            if (!isVisit[i]) {
                String word = wordList.get(i);
                int difference = 0;
                
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) != cur.charAt(j)) {
                        difference++;
                    }
                }
                
                if (difference == 1) {
                    isVisit[i] = true;
                    dfs(word, target, cnt + 1);
                    isVisit[i] = false;
                }
            }
        }
    }
}