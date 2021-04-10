class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] map = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) { continue; }
                map[i][j] = Integer.MAX_VALUE / n;
            }
        }
        
        for(int[] fare : fares) {
            map[fare[0] - 1][fare[1] - 1] = fare[2];
            map[fare[1] - 1][fare[0] - 1] = fare[2];
        }
        
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if(i == k) { continue; }
                for(int j = 0; j < n; j++) {
                    if(j == k || i == j) { continue; }
                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }
        
        int start = s - 1;
        int aEnd = a - 1;
        int bEnd = b - 1;
        
        for(int i = 0; i < n; i++) {
            int temp = map[start][i] + map[i][aEnd] + map[i][bEnd];
            answer = Math.min(answer, temp);
        }
        
        return answer;
    }
}
