import java.util.Stack;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] match = new int[n][n];
        
        // match[y][x]
        // 0 = no data, 1 = win to x, 2 = lose to x
        
        // Initialize Match Array
        for(int idx = 0; idx < results.length; idx++) {
            int winner = results[idx][0] - 1;
            int loser = results[idx][1] - 1;
            match[winner][loser] = 1;
            match[loser][winner] = 2;
        }
        
        for(int idx = 0; idx < n; idx++) {
            Stack<Integer> upper = new Stack<>();
            Stack<Integer> lower = new Stack<>();
            boolean[] checker = new boolean[n];
            int up = 0;
            int down = 0;
            for(int i = 0; i < n; i++) {
                if(match[idx][i] == 1) {
                    lower.add(i);
                    checker[i] = true;
                }
                else if(match[idx][i] == 2) {
                    upper.add(i);
                    checker[i] = true;
                }
            }
            
            while(!upper.isEmpty()) {
                int temp = upper.pop();
                up++;
                for(int j = 0; j < n; j++) {
                    if(!checker[j] && match[temp][j] == 2) {
                        upper.add(j);
                        checker[j] = true;
                    }
                }
            }
            while(!lower.isEmpty()) {
                int temp = lower.pop();
                down++;
                for(int j = 0; j < n; j++) {
                    if(!checker[j] && match[temp][j] == 1) {
                        lower.add(j);
                        checker[j] = true;
                    }
                }
            }
            if(up + down == n - 1) { answer++; }
        }
        
        return answer;
    }
}
