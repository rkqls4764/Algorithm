import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int n = 0; n < N; n++) {
            String word = br.readLine();
            char prev = word.charAt(0);
            
            ArrayList<Character> alphabets = new ArrayList<>();
            alphabets.add(prev);
            
            boolean isGroupWord = true;

            for (int i = 1; i < word.length(); i++) {
                char cur = word.charAt(i);

                // 알파벳이 같으면 패스
                if (prev == cur) {
                    continue;
                }

                // 그룹 단어가 아님
                if (alphabets.contains(cur)) {
                    isGroupWord = false;
                    break;
                }
                // 다른 알파벳이 들어옴
                else {
                    alphabets.add(cur);
                    prev = cur;
                }
            }

            if (isGroupWord) {
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}