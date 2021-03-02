import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        String origin = s.substring(1, s.length() - 1);
        String[] splited = origin.split("\\},\\{");
        
        splited[0] = splited[0].substring(1, splited[0].length());
        splited[splited.length - 1] = splited[splited.length - 1].
            substring(0, splited[splited.length - 1].length() - 1);
        
        Arrays.sort(splited, (str1, str2) -> str1.length() - str2.length());
        
        for(String str : splited) {
            String[] tuple = str.split(",");
            for(String numStr : tuple) {
                int num = Integer.parseInt(numStr);
                if(!answer.contains(num)) {
                    answer.add(num);
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
