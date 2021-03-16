import java.util.Stack;

class Solution {
    static final char OPEN = '(';
    static final char CLOSE = ')';
    
    public String solution(String p) {
        return recursive(p);
    }
    
    public String recursive(String w) {
        if(w.equals("")) { return ""; }
        int sepIdx = separate(w);
        String u = w.substring(0, sepIdx);
        String v = w.substring(sepIdx, w.length());
    
        if(isRight(u)) {
            return u + recursive(v);
        }
        else {
            if(u.length() == 2) {
                return "(" + recursive(v) + ")";
            }
            else {
                return "(" + recursive(v) + ")" + reverse(u);
            }
        }
    }
    
    public String reverse(String u) {
        StringBuilder newU = new StringBuilder();
        for(int idx = 1; idx < u.length() - 1; idx ++) {
            char temp = u.charAt(idx);
            if(temp == OPEN) { newU.append(")"); }
            else { newU.append("("); }
        }
        return newU.toString();
    }
    
    public int separate(String w) {
        int cnt = 0;
        for(int idx = 0; idx < w.length(); idx++) {
            char temp = w.charAt(idx);
            if(temp == OPEN) { cnt++; }
            else { cnt--; }
            
            if(cnt == 0) { return idx + 1; }
        }
        return w.length();
    }
    
    public boolean isRight(String u) {
        Stack<Character> stack = new Stack<>();
        for(int idx = 0; idx < u.length(); idx++) {
            char temp = u.charAt(idx);
            if(temp == OPEN) {
                stack.add(OPEN);
            }
            else if(temp == CLOSE && stack.isEmpty()) {
                return false;
            }
            else if(temp == CLOSE && stack.peek() == OPEN) {
                stack.pop();
            }
        }
        return true;
    }
}
