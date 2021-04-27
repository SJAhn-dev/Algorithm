import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int n, int[][] edge) {
        boolean[] visited = new boolean[n];
        boolean[][] map = new boolean[n][n];
        
        for(int[] connect : edge) {
            map[connect[0] - 1][connect[1] - 1] = true;
            map[connect[1] - 1][connect[0] - 1] = true;
        }
        
        int maxCount = 0;
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        int phase = 0;
        while(!queue.isEmpty()) {
            int phase_size = queue.size();
            int phase_count = 0;
            while(phase_count < phase_size) {
                int temp = queue.poll();
                
                for(int idx = 0; idx < n; idx++) {
                    if(map[temp][idx] && !visited[idx]) {
                        queue.add(idx);
                        visited[idx] = true;
                    }
                }
                
                phase_count++;
            }
            phase++;
            if(!queue.isEmpty()) {
                maxCount = queue.size();
            }
        }
        
        return maxCount;
    }
}
