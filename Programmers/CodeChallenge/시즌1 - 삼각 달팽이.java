class Solution {
    public int[] solution(int n) {
        int maxValue = 0;
        for(int i = 1; i <= n; i++) { maxValue += i; }
        int[] answer = new int[maxValue];
        
        int[][] map = new int[n][n];
        int direction = 0; // 0 : down, 1 : right, 2 : leftUp
        int x = 0;
        int y = 0;
        int cnt = 1;
        int maxCnt = n;
        System.out.println(maxValue);
        
        for(int value = 1; value <= maxValue; value++) {
            map[y][x] = value;
            
            if(cnt == maxCnt) {
                cnt = 1;
                maxCnt --;
                direction++;
                direction = direction % 3;
            } else { cnt++; }

            switch(direction) {
                case 0:
                    y++;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    x--;
                    y--;
                    break;
            }
        }
        
        int xMax = 1;
        int count = 0;
        for(int my = 0; my < n; my++) {
            for(int mx = 0; mx < xMax; mx++) {
                answer[count] = map[my][mx];
                count++;
            }
            xMax++;
        }
        
        return answer;
    }
}
