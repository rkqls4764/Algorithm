import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        * 산술평균: int sum에 합 더하기, sum / N한 값
        * 중앙값: int[]에 넣고 정렬한 뒤에 인덱스 N/2한 값
        * 최빈값: Map<int, int>에다가 숫자, 개수 넣어서 가장 많은 값 (여러 개면 두 번째로 작은 값)
        * 범위: 정렬된 int[]의 0번째 N-1번째를 뺀 값
        */

        int N = Integer.parseInt(br.readLine());
        double sum = 0;
        int[] arr = new int[N];
        Map<Integer, Integer> map = new HashMap<>();

        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            arr[n] = num;

            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }

        int mean = (int) Math.round(sum / N);

        Arrays.sort(arr);
        int median = arr[N / 2];

        List<Integer> modes = new ArrayList<>();
        int max = Collections.max(map.values());
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();

            if (max == value) {
                modes.add(entry.getKey());
            }
        }
        
        Collections.sort(modes);
        int mode = modes.size() > 1 ? modes.get(1) : modes.get(0);

        int range = arr[N - 1] - arr[0];

        bw.write(Integer.toString(mean) + "\n" + Integer.toString(median) + "\n" + Integer.toString(mode) + "\n" + Integer.toString(range));
        bw.flush();
    }
}