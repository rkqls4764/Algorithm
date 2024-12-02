class Solution {
    static int answer;
    static String checkWord;
    static int cnt;
    
    public int solution(String word) {
        String[] alphabet = {"A", "E", "I", "O", "U"};
        answer = 0;
        checkWord = word;
        cnt = 0;
        
        makeWord(alphabet, "");
        
        return answer;
    }
    
    public void makeWord(String[] alphabet, String word) {
        if (word.equals(checkWord)) {
            answer = cnt;
            return;
        }
        
        for (int i = 0; i < alphabet.length; i++) {
            if (word.length() < 5) {
                word += alphabet[i];
                cnt++;
            
                makeWord(alphabet, word);
            
                word = word.substring(0, word.length() - 1);
            }
        }
    }
}