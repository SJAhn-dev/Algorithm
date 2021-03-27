import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] numbers) {
        int numberLength = numbers.length;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < numberLength; i++) {
            for(int j = i + 1; j < numberLength; j++) {
                int val = numbers[i] + numbers[j];
                if(!list.contains(val)) {
                    list.add(val);
                }
            }
        }
        
        int[] answer = new int[list.size()];
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        Arrays.sort(answer);
        
        return answer;
    }
}
