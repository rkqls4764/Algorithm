import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        /*
        * 10보다 작으면 앞에 0을 붙여 두 자리 수로 만들고 각 자리 숫자 더함
        * 가장 오른쪽 자리 수와 앞에서 구한 합의 가장 오른쪽 자리 수를 이어 붙임
        * 원래 수로 돌아오는 사이클 길이 리턴
        * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            bw.write("1");
            bw.flush();
            return;
        }

        int cur = N;
        int sum = 0;
        int next = 0;
        int cnt = 0;

        while (true) {
            sum = (cur / 10) + (cur % 10);
            next = (cur % 10) * 10 + (sum % 10);
            cnt++;

            if (N == next) {
                bw.write(Integer.toString(cnt));
                break;
            }

            cur = next;
        }

        bw.flush();
    }
}