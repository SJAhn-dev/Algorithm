class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        char[][] map = new char[m][n];
        
        for(int y = 0; y < m; y++) {
            for(int x = 0; x < n; x++) {
                map[y][x] = board[y].charAt(x);
            }
        }
        
        boolean endFlag = false;
        
        while(!endFlag) {
            endFlag = true;
            boolean[][] bombMap = new boolean[m][n];
            
            for(int y = 0; y < m - 1; y++) {
                for(int x = 0; x < n - 1; x++) {
                    if(map[y][x] == (char) 'X') { continue; }
                    if(map[y][x + 1] != map[y][x]) { continue; }
                    if(map[y + 1][x] != map[y][x]) { continue; }
                    if(map[y + 1][x + 1] != map[y][x]) { continue; }

                    if(!bombMap[y][x]) { bombMap[y][x] = true; answer++; }
                    if(!bombMap[y][x + 1]) { bombMap[y][x + 1] = true; answer++; }
                    if(!bombMap[y + 1][x]) { bombMap[y + 1][x] = true; answer++; }
                    if(!bombMap[y + 1][x + 1]) { bombMap[y + 1][x + 1] = true; answer++; }

                    endFlag = false;
                }
            }
            
            if(endFlag) { break; }
            for(int y = m - 1; y > 0; y--) {
                for(int x = n - 1; x >= 0; x--) {
                    if(!bombMap[y][x]) { continue; }
                    int dy = y;
                    boolean findUpper = false;

                    while(!findUpper) {
                        dy--;
                        if(dy < 0) { break; }

                        if(!bombMap[dy][x]) { findUpper = true; }
                        else { map[dy][x] = 'X'; }
                    }
                    if(findUpper) {
                        bombMap[dy][x] = true;
                        map[y][x] = map[dy][x];
                        map[dy][x] = (char) 'X';
                    }
                }
            }
        }
        return answer;
    }
}
