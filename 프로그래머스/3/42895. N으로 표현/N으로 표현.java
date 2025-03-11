import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }
        
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();    // 키: 사용한 N의 개수, 값: 계산 결과
        dp.put(1, new HashSet<>());
        dp.get(1).add(N);   // N을 한 번 사용하면 N
        
        for (int n = 2; n < 9; n++) {
            String num = "";    // N을 i번 붙여 만든 수
            for (int i = 0; i < n; i++) {
                num += String.valueOf(N);
            }
            
            HashSet<Integer> set = new HashSet<>();
            set.add(Integer.parseInt(num));
            
            /*
            N을 3개 사용한 경우를 구할 때,
            N을 1개 사용한 계산 결과와 N을 2개 사용한 계산 결과를 가지고 사칙연산 해서 구한다.
            N을 2개 사용한 계산 결과와 N을 1개 사용한 계산 결과를 가지고 사칙연산한 결과도 구해야 한다. 빼기나 나눗셈의 값이 다르기 때문이다.
            */
            for (int i = 1; i < n; i++) {
                for (int x : dp.get(i)) {
                    for (int y : dp.get(n - i)) {
                        set.add(x + y);
                        set.add(x - y);
                        set.add(x * y);
                        if (y != 0) {
                            set.add(x / y);
                        }
                    }
                }
                
                if (set.contains(number)) { // number를 구했으면 현재 n을 리턴
                    return n;
                }
                
                dp.put(n, set);
            }
        }
        
        return -1;  // N을 8개 이하로 사용해 number를 만들 수 없으면 -1 리턴
    }
}