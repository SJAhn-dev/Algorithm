import java.util.ArrayList;

class Solution {
    ArrayList<Integer> savePrime = new ArrayList<>();
    boolean[] checked;
    String numString;
    int answer;
    int numberLength;
    
    public int solution(String numbers) {
        answer = 0;
        numString = numbers;
        numberLength = numbers.length();
        checked = new boolean[numberLength];
        
        for(int cnt = 1; cnt <= numberLength; cnt++) {
             makeNumber(cnt, new StringBuilder());
        }
        
        return answer;
    }
    
    public void makeNumber(int choice, StringBuilder sb) {
        if(sb.length() == choice) {
            checkPrime(Integer.parseInt(sb.toString()));
            return;
        }
        
        for(int idx = 0; idx < numberLength; idx++) {
            if(!checked[idx]) {
                checked[idx] = true;
                sb.append(numString.charAt(idx));
                makeNumber(choice, sb);
                sb.deleteCharAt(sb.length() - 1);
                checked[idx] = false;
            }
        }
    }
    
    public void checkPrime(int number) {
        if(number == 0 || number == 1 || savePrime.contains(number)) {
            return;
        }
        
        for(int i = 2; i < number; i++) {
            if(number % i == 0) {
                
                return;
            }
        }
        
        savePrime.add(number);
        answer++;
    }
}
