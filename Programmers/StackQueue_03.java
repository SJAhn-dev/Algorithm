import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> onBridge = new LinkedList<>();
        
        int tempWeight = 0;
        
        for(int truck : truck_weights) {
            while(true) {
                if(onBridge.isEmpty()) {
                    onBridge.offer(truck);
                    tempWeight += truck;
                    answer++;
                    break;
                }
                else if(onBridge.size() == bridge_length) {
                    tempWeight -= onBridge.poll();
                }
                else {
                    if(tempWeight + truck > weight) {
                        onBridge.offer(0);
                        answer++;
                    }
                    else {
                        onBridge.offer(truck);
                        tempWeight += truck;
                        answer++;
                        break;
                    }
                }
            }
        }
        
        return answer + bridge_length;
    }
}
