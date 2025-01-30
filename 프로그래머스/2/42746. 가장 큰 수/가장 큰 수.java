import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] numList = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numList[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numList, new Comparator<String>() {
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b); // 붙였을 때 큰 순서대로 정렬
            }
        });
        
        StringBuilder answer = new StringBuilder();
        
        if (numList[0].equals("0")) { // 모든 숫자가 0인 경우 "0" 리턴
            return "0";
        }
        
        for (int i = 0; i < numList.length; i++) {
            answer.append(numList[i]);
        }
        
        return answer.toString();
    }
}