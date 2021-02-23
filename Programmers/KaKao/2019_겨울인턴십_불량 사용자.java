import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    static int banArrayLength;
    static int usrArrayLength;
    static boolean[][] possible;
    static ArrayList<String> countList = new ArrayList<>();
    static int answer = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        banArrayLength = banned_id.length;
        usrArrayLength = user_id.length;
        possible = new boolean[banned_id.length][user_id.length];
        
        int banIdx = 0;
        for(String ban : banned_id) {
            int banLength = ban.length();
            int usrIdx = 0;
            for(String usr : user_id) {
                    if(ban.length() == usr.length()) {
                        for(int idx = 0; idx < banLength; idx++) {
                            char banChar = ban.charAt(idx);
                            char usrChar = usr.charAt(idx);
                            if(banChar != '*' && usrChar != banChar) { break; }
                            if(idx == banLength - 1) { possible[banIdx][usrIdx] = true; }
                        }
                    }
                    usrIdx++;
                }
            banIdx++;
        }
        
        dfs(new StringBuilder(), 0);
        
        return answer;
    }
    
    public void dfs(StringBuilder sb, int y) {
        if(y == banArrayLength) {
            char[] charArr = sb.toString().toCharArray();
            Arrays.sort(charArr);
            String result = new String(charArr);
            if(!countList.contains(result)) {
                countList.add(result);
                answer++;
            }
            return;
        }
        
        for(int x = 0; x < usrArrayLength; x++) {
            if(possible[y][x] && sb.indexOf(Integer.toString(x)) == -1) {
                sb.append(Integer.toString(x));
                dfs(sb, y + 1);
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }
}
