import java.io.*;

class Main {
    public static Long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new Long[n+1];

        System.out.print(fibonacci(n));
    }

    public static Long fibonacci(int n) {
        if (n <= 1) { // 0, 1번째 피보나치 수는 0, 1이다
            return (long) n;
        }

        if (arr[n] != null) { // 피보나치 수를 이전에 구했으면 계산 없이 리턴
            return arr[n];
        }

        arr[n] = fibonacci(n-1) + fibonacci(n-2);
        return arr[n];
    }
}