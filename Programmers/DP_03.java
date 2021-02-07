import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int PUDDLES = -1;
        int[][] map = new int[n][m];
        boolean[][] searched = new boolean[n][m];
        
        for(int idx = 0; idx < puddles.length; idx++) {
            int y = puddles[idx][1] - 1;
            int x = puddles[idx][0] - 1;
            map[y][x] = PUDDLES;
        }
        
        Queue<Loc> queue = new LinkedList<>();
        if(map[0][1] != PUDDLES) {
            map[0][1] = 1;
            searched[0][1] = true;
            queue.add(new Loc(0, 1));
        }
        if(map[1][0] != PUDDLES) {
            map[1][0] = 1;
            searched[1][0] = true;
            queue.add(new Loc(1, 0));
        }
        
        while(!queue.isEmpty()) {
            Loc temp = queue.poll();
            if(temp.y == n - 1 && temp.x == m - 1) { continue; }
            if(temp.y + 1 < n && map[temp.y + 1][temp.x] != PUDDLES) {
                map[temp.y + 1][temp.x] += map[temp.y][temp.x];
                map[temp.y + 1][temp.x] = map[temp.y + 1][temp.x] % 1000000007;
                if(!searched[temp.y + 1][temp.x]) {
                    searched[temp.y + 1][temp.x] = true;
                    queue.add(new Loc(temp.y + 1, temp.x));
                }
            }
            if(temp.x + 1 < m && map[temp.y][temp.x + 1] != PUDDLES) {
                map[temp.y][temp.x + 1] += map[temp.y][temp.x];
                map[temp.y][temp.x + 1] = map[temp.y][temp.x + 1] % 1000000007;
                if(!searched[temp.y][temp.x + 1]) {
                    searched[temp.y][temp.x + 1] = true;
                    queue.add(new Loc(temp.y, temp. x + 1));
                }
            }
        }
        answer = map[n - 1][m - 1] % 1000000007;
        return answer;
    }
    
    public class Loc{
        int y;
        int x;
        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
