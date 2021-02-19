import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        int dictIdx = 27;
        
        // Initialize Dict
        char tempChar = 'A';
        for(int idx = 1; idx < 27; idx++) {
            dict.put(Character.toString(tempChar), idx);
            tempChar++;
        }
        
        int msgLength = msg.length();
        StringBuilder sb = new StringBuilder();
        int remainChar = msgLength;
        int tempIdx = 0;
        while(tempIdx < msgLength) {
            sb.append( msg.charAt(tempIdx));
            
            if(dict.containsKey(sb.toString())) {
                tempIdx++;
                if(tempIdx == msgLength) {
                    answer.add(dict.get(sb.toString()));
                    break;
                }
            }
            else {
                int dictValue = dict.get(sb.substring(0, sb.length() - 1));
                answer.add(dictValue);
                dict.put(sb.toString(), dictIdx);
                dictIdx ++;
                sb.delete(0, sb.length());
            }
        }
        
        // List to Array
        int[] ansArr = new int[answer.size()];
        for(int idx = 0; idx < answer.size(); idx++) {
            ansArr[idx] = answer.get(idx);
        }

        return ansArr;
    }
}
