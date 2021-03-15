import java.util.Stack;

class Solution {
    static int[][] map;
    static boolean[][] topCheck;
    static int mapSize;
    
    public int solution(int[][] board) {
        int answer = 0;
        mapSize = board.length;
        map = board;
        Stack<Loc> stack = new Stack<>();
        
        boolean findSet = true;
        while(findSet) {
            findSet = false;
            topCheck = new boolean[mapSize][mapSize];
            
            for(int x = 0; x < mapSize; x++) {
                for(int y = 0; y < mapSize; y++) {
                    if(map[y][x] != 0) {
                        topCheck[y][x] = true;
                        stack.add(new Loc(x, y, map[y][x]));
                        break;
                    }
                }
            }
            
            while(!stack.isEmpty()) {
                int type = isPossible(stack.peek());
                if(type != -1) {
                    findSet = true;
                    Loc loc = stack.pop();
                    deleteBlock(loc, type);
                    answer++;
                } else { stack.pop(); }
            }
        }
        
        return answer;
    }
    
    public void deleteBlock(Loc loc, int type) {
        int x = loc.x;
        int y = loc.y;
        switch(type) {
            case 1:
                map[y][x] = 0;
                map[y - 1][x - 1] = 0;
                map[y][x - 1] = 0;
                map[y][x + 1] = 0;
                break;
            case 2:
                map[y][x] = 0;
                map[y][x + 1] = 0;
                map[y - 1][x + 1] = 0;
                map[y - 2][x + 1] = 0;
                break;
            case 3:
                map[y][x] = 0;
                map[y][x - 1] = 0;
                map[y - 1][x - 1] = 0;
                map[y - 2][x - 1] = 0;
                break;
            case 4:
                map[y][x] = 0;
                map[y][x + 1] = 0;
                map[y][x + 2] = 0;
                map[y - 1][x + 2] = 0;
                break;
            case 5:
                map[y][x] = 0;
                map[y][x + 1] = 0;
                map[y][x + 2] = 0;
                map[y - 1][x + 1] = 0;
                break;
        }
    }
    
    public int isPossible(Loc loc) {
        int x = loc.x;
        int y = loc.y;
        int value = loc.value;
        if(y - 1 >= 0 && x + 1 < mapSize && x - 1 >= 0) {
            if(map[y - 1][x - 1] == value && map[y - 1][x - 1] == value && map[y][x + 1] == value &&
               topCheck[y][x] && topCheck[y][x + 1]) {
                return 1;
            }
        }
        if(y - 2 >= 0 && x + 1 < mapSize) {
            if(map[y - 2][x + 1] == value && map[y][x + 1] == value && 
               topCheck[y][x]) {
                return 2;
            }
        }
        if(y - 2 >= 0 && x - 1 >= 0) {
            if(map[y - 2][x - 1] == value && map[y - 1][x - 1] == value && 
               topCheck[y][x]) {
                return 3;
            }
        }
        if(y - 1 >= 0 && x + 2 < mapSize) {
            if(map[y - 1][x + 2] == value && map[y][x + 1] == value &&
               topCheck[y][x] && topCheck[y][x + 1]) {
                return 4;
            }
        }
        if(y - 1 >= 0 && x + 2 < mapSize) {
            if(map[y - 1][x + 1] == value && map[y][x + 1] == value && map[y][x + 2] == value
               && topCheck[y][x] && topCheck[y - 1][x + 1] && topCheck[y][x + 2] ) {
                return 5;
            }
        }
    
        return -1;
    }
    
    class Loc {
        int x, y, value;
        public Loc(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
