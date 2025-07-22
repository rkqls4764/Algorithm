import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        int c_cnt = 0;      // 크로아티아 알파벳 개수
        int c_length = 0;   // 크로아티아 알파벳 길이

        for (int i = 0; i < word.length() - 1; i++) {
            char cur = word.charAt(i);
            char next = word.charAt(i + 1);

            switch (cur) {
                case 'c':
                    if (next == '=' || next == '-') {
                        c_cnt++;
                        c_length += 2;
                        i++;
                    }
                    break;
                case 'd':
                    if (next == '-') {
                        c_cnt++;
                        c_length += 2;
                        i++;
                    }
                    if (i < word.length() - 2) {
                        char n_next = word.charAt(i + 2);

                        if (next == 'z' && n_next == '=') {
                            c_cnt++;
                            c_length += 3;
                            i += 2;
                        }
                    }
                    break;
                case 'l':
                case 'n':
                    if (next == 'j') {
                        c_cnt++;
                        c_length += 2;
                        i++;
                    }
                    break;
                case 's':
                case 'z':
                    if (next == '=') {
                        c_cnt++;
                        c_length += 2;
                        i++;
                    }
                    break;
            }
        }

        int cnt = c_cnt + (word.length() - c_length);

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}