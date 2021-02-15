import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
        char prevChar = 0;
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        for(int i = 0; i < 2; i++) {
            StringBuilder sb = new StringBuilder();
            String input = "";
            ArrayList<String> tempList = list1;
            
            if(i == 0) { input = str1; tempList = list1; }
            if(i == 1) { input = str2; tempList = list2; }
            
            for(int idx = 0; idx < input.length(); idx++) {
                char tempChar = input.charAt(idx);
                tempChar = Character.toLowerCase(tempChar);
            
                if(tempChar < (char) 'a' || tempChar > (char) 'z') {
                    sb.delete(0, 1);
                    continue;
                }
            
                if(sb.length() == 0) {
                    sb.append(tempChar);
                }
                else if (sb.length() == 1) {
                    sb.append(tempChar);
                    tempList.add(sb.toString());
                    sb.delete(0, 1);
                }
                else {
                    sb.delete(0, 1);
                    sb.append(tempChar);
                    tempList.add(sb.toString());
                    sb.delete(0, 1);
                }
            }
        }
        
        int intersection = 0;
        int union = 0;
        for(String tempString : list1) {
            if(list2.contains(tempString)) {
                intersection++;
                list2.remove( list2.indexOf(tempString) );
            }
            union++;
        }
        union += list2.size();
        if(intersection == 0 && union == 0) { return 65536; }
        double answer = ((double) intersection) / ((double) union);
        
        return (int) (answer * 65536.0);
    }
}
