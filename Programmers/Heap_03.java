import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(String operation : operations) {
            String[] split = operation.split(" ");
            
            if(split[0].equals("I")) {
                int input = Integer.parseInt(split[1]);
                minQueue.add(input);
                maxQueue.add(input);
            }
            else if(Integer.parseInt(split[1]) == 1) {
                if(maxQueue.isEmpty()) { continue; }
                int remove = maxQueue.poll();
                minQueue.remove(remove);
            }
            else {
                if(minQueue.isEmpty()) { continue; }
                int remove = minQueue.poll();
                maxQueue.remove(remove);
            }
        }
        
        int[] answer = new int[2];
        
        answer[0] = maxQueue.isEmpty() ? 0 : maxQueue.peek();
        answer[1] = minQueue.isEmpty() ? 0 : minQueue.peek();

        return answer;
    }
}
