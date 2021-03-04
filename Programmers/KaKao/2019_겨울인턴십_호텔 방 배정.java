import java.util.HashMap;
import java.util.Stack;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int length = room_number.length;
        long[] answer = new long[room_number.length];
        HashMap<Long, Long> cache = new HashMap<>();
        Stack<Long> stack = new Stack<>();
        
        for(int idx = 0; idx < length; idx++) {
            long orgnum = room_number[idx];
            long num = room_number[idx];
            stack.add(num);
            
            while(true) {
                if(cache.containsKey(num)) {
                    num = cache.get(num) + 1;
                    stack.add(num);
                }
                else {
                    answer[idx] = num;
                    stack.add(num);
                    while(!stack.isEmpty()) {
                        cache.put(stack.pop(), num);
                    }
                    break;
                }
            }
        }
        
        return answer;
    }
}
