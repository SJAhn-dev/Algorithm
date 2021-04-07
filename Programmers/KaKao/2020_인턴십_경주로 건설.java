class Solution {
    static int mapSize;
    static int[][] costHistory;
    static boolean[][] visited;
    static int[][] map;
    static int minCost = Integer.MAX_VALUE;
    static final String bot = "BOT";
    static final String top = "TOP";
    static final String right = "RIGHT";
    static final String left = "LEFT";
    
    public int solution(int[][] board) {
        mapSize = board.length;
        map = board;
        visited = new boolean[mapSize][mapSize];
        costHistory = new int[mapSize][mapSize];
        
        buildRoad(0, 0, 0, null);
        
        return minCost;
    }
    
    public void buildRoad(int x, int y, int costSum, String direction) {
        if(x == mapSize - 1 && y == mapSize - 1) 
            minCost = Math.min(costSum, minCost);
        else if(costSum > minCost)
            return;
        
        if(costHistory[y][x] != 0 && costHistory[y][x] < costSum)
            return;
        else
            costHistory[y][x] = costSum;
        
        // Right
        if(x + 1 < mapSize && !visited[y][x + 1] && map[y][x + 1] != 1) {
            visited[y][x + 1] = true;
            if((x == 0 && y == 0) || direction == right) 
                buildRoad(x + 1, y, costSum + 100, right);
            else 
                buildRoad(x + 1, y, costSum + 600, right);
            visited[y][x + 1] = false;
        }
        
        // Left
        if(x - 1 >= 0 && !visited[y][x - 1] && map[y][x - 1] != 1) {
            visited[y][x - 1] = true;
            if(direction == left) 
                buildRoad(x - 1, y, costSum + 100, left);
            else 
                buildRoad(x - 1, y, costSum + 600, left);
            visited[y][x - 1] = false;
        }
        
        // Bottom
        if(y + 1 < mapSize && !visited[y + 1][x] && map[y + 1][x] != 1) {
            visited[y + 1][x] = true;
            if((x == 0 && y == 0) || direction == bot) 
                buildRoad(x, y + 1, costSum + 100, bot);
            else 
                buildRoad(x, y + 1, costSum + 600, bot);
            visited[y + 1][x] = false;
        }
        
        // Top
        if(y - 1 >= 0 && !visited[y - 1][x] && map[y - 1][x] != 1) {
            visited[y - 1][x] = true;
            if(direction == top) 
                buildRoad(x, y - 1, costSum + 100, top);
            else 
                buildRoad(x, y - 1, costSum + 600, top);
            visited[y - 1][x] = false;
        }
    }
}
