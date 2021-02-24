import java.util.PriorityQueue;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        PriorityQueue<Stone> queue = new PriorityQueue<>();
        for(int idx = 0; idx < stones.length; idx++) {
            queue.add(new Stone(idx, stones[idx]));
        }
        if(k == 1) { return queue.poll().value; }
        
        boolean[] finished = new boolean[stones.length];
        
        while(!queue.isEmpty()) {
            Stone temp = queue.poll();
            answer = temp.value;
            finished[temp.index] = true;
            int cnt = 1;
            int left = temp.index;
            int right = temp.index;
            boolean leftCan = true;
            boolean rightCan = true;
            
            while(true) {
                boolean leftVisit = false;
                boolean rightVisit = false;
                if(left - 1 >= 0 && leftCan) {
                    left--;
                    leftVisit = true;
                    
                    if(finished[left]) { cnt++; }
                    else { leftVisit = false; leftCan = false; }
                }
                if(right + 1 < stones.length && rightCan) {
                    right++;
                    rightVisit = true;
                    
                    if(finished[right]) { cnt++; }
                    else { rightVisit = false; rightCan = false; }
                }
                if(leftVisit == false && rightVisit == false) { break; }
                if(cnt >= k) { return answer; }
            }
        }
        
        return answer;
    }
    
    public class Stone implements Comparable<Stone>{
        int value;
        int index;
        
        public Stone(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        @Override
        public int compareTo(Stone obj) {
            Integer thisValue = this.value;
            Integer objValue = obj.value;
            return thisValue.compareTo(objValue);
        }
    }
}
