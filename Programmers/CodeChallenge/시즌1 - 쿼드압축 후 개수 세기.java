class Solution {
    static int zeroCnt;
    static int oneCnt;
    static int[][] map;
    
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        map = arr;
        
        compression(0, 0, arr[0].length);
        
        answer[0] = zeroCnt;
        answer[1] = oneCnt;
        
        return answer;
    }
    
    public void compression(int startX, int startY, int size) {
        int xSize = startX + size;
        int ySize = startY + size;
        int saveVal = map[startY][startX];
        
        for(int y = startY; y < ySize; y++) {
            for(int x = startX; x < xSize; x++) {
                if(map[y][x] != saveVal) {
                    compression(startX, startY, size / 2);
                    compression(startX + (size / 2), startY, size / 2);
                    compression(startX, startY + (size / 2), size / 2);
                    compression(startX + (size / 2), startY + (size / 2), size / 2);
                    return;
                }
                saveVal = map[y][x];
            }
        }
        
        if(saveVal == 0) {
            zeroCnt++;
        }
        else {
            oneCnt++;
        }
    }
}
