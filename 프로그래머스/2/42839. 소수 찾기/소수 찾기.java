import java.util.*;

class Solution {
    ArrayList<Integer> numberList;
    ArrayList<Integer> results;
    Boolean[] isUsed;
    
    public int solution(String numbers) {
        numberList = new ArrayList<>(numbers.length());
        results = new ArrayList<>();
        isUsed = new Boolean[numbers.length()];
        
        
        for (int i = 0; i < numbers.length(); i++) {
            int num = numbers.charAt(i) - '0';
            numberList.add(num);
            isUsed[i] = false;
        }
        
        makeNumber(0, 0);
            
        return results.size();
    }
    
    public void makeNumber(int num, int depth) {
        if (isPrimeNumber(num) && !results.contains(num)) {
            results.add(num);
        }
        
        if (depth == numberList.size()) {
            return;
        }
       
        for (int i = 0; i < numberList.size(); i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                num = num * 10 + numberList.get(i);
                
                makeNumber(num, depth + 1);
                
                num = num / 10;
                isUsed[i] = false;
            }
        }
    }
    
    public boolean isPrimeNumber(int num) {
        if (num == 2) {
            return true;
        }
        
        if (num == 1 || num % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}