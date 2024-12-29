import java.io.*;
import java.util.*;

class Main {
    public static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            isVisit = new boolean[10000];
            bw.write(bfs(A, B) + "\n");
        }

        bw.flush();
    }

    public static String bfs(int start, int target) {
        ArrayDeque<Object> queue = new ArrayDeque<>();
        queue.addLast(new Object(start, new StringBuilder()));
        isVisit[start] = true;

        while (!queue.isEmpty()) {
            Object cur = queue.removeFirst();

            if (cur.num == target) {
                return cur.route.toString();
            }

            Object[] results = { new Object(D(cur.num), new StringBuilder("D")), new Object(S(cur.num), new StringBuilder("S")), new Object(L(cur.num), new StringBuilder("L")),  new Object(R(cur.num), new StringBuilder("R")) };

            for (Object result : results) {
                if (!isVisit[result.num]) {
                    isVisit[result.num] = true;
                    queue.addLast(new Object(result.num, new StringBuilder(cur.route.append(result.route))));
                    cur.route.deleteCharAt(cur.route.length() - 1);
                }
            }
        }

        return "";
    }

    public static int D(int num) {
        if (num * 2 > 9999) {
            return num * 2 % 10000;
        } else {
            return num * 2;
        }
    }

    public static int S(int num) {
        if (num == 0) {
            return 9999;
        } else {
            return num - 1;
        }
    }

    public static int L(int num) {
        int d1 = num / 1000;
        int d2 = (num % 1000) / 100;
        int d3 = (num % 100) / 10;
        int d4 = num % 10;

        return ((d2 * 10 + d3) * 10 + d4) * 10 + d1;
    }

    public static int R(int num) {
        int d1 = num / 1000;
        int d2 = (num % 1000) / 100;
        int d3 = (num % 100) / 10;
        int d4 = num % 10;

        return ((d4 * 10 + d1) * 10 + d2) * 10 + d3;
    }
}

class Object {
    int num;
    StringBuilder route;

    public Object(int num, StringBuilder route) {
        this.num = num;
        this.route = route;
    }
}