import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] array = new String[numbers.length];
        int zeroCnt = 0;
        
        // Convert number to String array
        for(int idx=0; idx<numbers.length; idx++) {
            array[idx] = Integer.toString(numbers[idx]);
            if(numbers[idx] == 0) { zeroCnt++; }
        }
        
        // If all numbers zero, return 0
        if(zeroCnt == numbers.length) { return "0"; }
        
        // Define new comparator
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String str1, String str2) {
                return (str2 + str1).compareTo(str1 + str2);
            }
        };
        
        // Array sorting with new comparator
        Arrays.sort(array, comparator);
        
        // array to one String
        for(int idx=0; idx<array.length; idx++) {
            answer += array[idx];
        }
        
        return answer;
    }
}
