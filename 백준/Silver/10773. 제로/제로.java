import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                stack.removeFirst();
            }
            else {
                stack.addFirst(num);
            }
        }

        int sum = 0;

        while (!stack.isEmpty()) {
            sum += stack.removeFirst();
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }
}