import java.util.Stack;

class Solution {
    static int slength;
    
    public int solution(String s) {
        int answer = 0;
        slength = s.length();

        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < slength; idx++) {
            char temp = s.charAt(idx);
            sb.append(temp);
        }

        int count = 0;
        while(count < slength) {
            char temp = sb.charAt(0);
            sb.append(temp);
            sb.delete(0, 1);
            if(isRight(sb)) { answer++; }
            count++;
        }

        return answer;
    }

    public boolean isRight(StringBuilder sb) {
        Stack<Character> stack = new Stack<>();
        String s = sb.toString();
        for(int idx = 0; idx < slength; idx++) {
            char temp = s.charAt(idx);

            if(!stack.isEmpty()) {
                char peek = stack.peek();

                if(temp == ')' && peek == '(')
                    stack.pop();
                else if(temp == '}' && peek == '{')
                    stack.pop();
                else if(temp == ']' && peek == '[')
                    stack.pop();
                else
                    stack.add(temp);
            }
            else
                stack.add(temp);
        }

        return stack.isEmpty();
    }
}
