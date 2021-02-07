import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int length = numbers.length;
        int[][] map = new int[2][length];
        
        for(int idx=0; idx<length; idx++) {
            map[0][idx] = numbers[idx];
            map[1][idx] = -1 * numbers[idx];
        }
        
        // Queue Initialize
        Queue<Integer> queue = new LinkedList<>();
        int phase_length = 2;
        queue.add(map[0][0]);
        queue.add(map[1][0]);
        
        // Queue Loop
        for(int phase=1; phase<length; phase++) {
            int phase_idx = 0;
            int new_phase_length = 0;
            while(phase_idx < phase_length) {
                int temp = queue.poll();
                queue.add(temp + map[0][phase]);
                queue.add(temp + map[1][phase]);
                new_phase_length += 2;
                phase_idx++;
            }
            phase_length = new_phase_length;
        }
        
        // Count Result
        while(!queue.isEmpty()) {
            int value = queue.poll();
            if(value == target) { answer++; }
        }
        
        return answer;
    }
}
