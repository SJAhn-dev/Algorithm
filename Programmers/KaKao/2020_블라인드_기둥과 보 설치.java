import java.util.ArrayList;

class Solution {
    static Building[][] map;
    static int mapSize;
    
    public int[][] solution(int n, int[][] build_frame) {
        map = new Building[n + 1][n + 1];
        mapSize = n + 1;
        
        for(int y = 0; y < mapSize; y++) {
            for(int x = 0; x < mapSize; x++) {
                map[y][x] = new Building(y, x);
            }
        }
        
        for(int[] command : build_frame) {
            if(command[3] == 1) {
                if(command[2] == 0) {
                    setPillow(command[0], command[1]);
                }
                else {
                    setBo(command[0], command[1]);
                }
            }
            else {
                if(command[2] == 0) {
                    delPillow(command[0], command[1]);
                }
                else {
                    delBo(command[0], command[1]);
                }
            }
        }
        
        ArrayList<BuildCheck> buildList = new ArrayList<>();
        for(int x = 0; x < mapSize; x++) {
            for(int y = 0; y < mapSize; y++) {
                if(map[y][x].hasP) {
                    buildList.add(new BuildCheck(x, y, 0));
                }
                if(map[y][x].hasB) {
                    buildList.add(new BuildCheck(x, y, 1));
                }
            }
        }
        
        int[][] answer = new int[buildList.size()][3];
        int bSize = buildList.size();
        for(int idx = 0; idx < bSize; idx++) {
            BuildCheck temp = buildList.get(idx);
            answer[idx][0] = temp.x;
            answer[idx][1] = temp.y;
            answer[idx][2] = temp.build;
        }
        
        return answer;
    }
    
    public void setPillow(int x, int y) {
        Building temp = map[y][x];
        
        if(y == 0) {
            temp.hasP = true;
            map[y + 1][x].hasBotP = true;
            return;
        }
        
        if(temp.hasB) {
            temp.hasP = true;
            if(y + 1 < mapSize) {
                map[y + 1][x].hasBotP = true;
            }
            return;
        }
        
        if(temp.hasBotP) {
            temp.hasP = true;
            if(y + 1 < mapSize) {
                map[y + 1][x].hasBotP = true;
            }
            return;
        }
        
        if(x - 1 >= 0) {
            Building left = map[y][x - 1];
            if(left.hasB) {
                temp.hasP = true;
                if(y + 1 < mapSize) {
                    map[y + 1][x].hasBotP = true;
                }
            }
        }
    }
    
    public void setBo(int x, int y) {
        Building temp = map[y][x]; 
        Building right = map[y][x + 1];
        
        if(temp.hasBotP) {
            temp.hasB = true;
        }
        
        if(right.hasBotP) {
            temp.hasB = true;
        }
        
        if(x - 1 >= 0) {
            Building left = map[y][x - 1];
            if(right.hasB && left.hasB) {
                temp.hasB = true;
            }
        }
    }
    
    public void delPillow(int x, int y) {
        Building temp = map[y][x];
        temp.hasP = false;
        if(y + 1 < mapSize) { map[y + 1][x].hasBotP = false; }
        
        for(int cy = 0; cy < mapSize; cy++) {
            for(int cx = 0; cx < mapSize; cx++) {
                if(!checkValid(cx, cy)) {
                    temp.hasP = true;
                    if(y + 1 < mapSize) { map[y + 1][x].hasBotP = true; }
                    return;
                }
            }
        }
    }
    
    public void delBo(int x, int y) {
        Building temp = map[y][x];
        temp.hasB = false;
        
        for(int cy = 0; cy < mapSize; cy++) {
            for(int cx = 0; cx < mapSize; cx++) {
                if(!checkValid(cx, cy)) {
                    temp.hasB = true;
                    return;
                }
            }
        }
    }
    
    public boolean checkValid(int x, int y) {
        Building temp = map[y][x];
        Building left = (x - 1) >= 0 ? map[y][x - 1] : null;
        Building right = (x + 1) < mapSize ? map[y][x + 1] : null;
        
        if(temp.hasB && !temp.hasBotP && !right.hasBotP) {
            if(left == null) { return false; }
            else if(!right.hasB || !left.hasB) { return false; }
        }
        
        if(temp.hasP && y != 0 && !temp.hasB && !temp.hasBotP) {
            if(left == null) { return false; }
            else if(!left.hasB) { return false; }
        }
        
        return true;
    }
    
    class Building {
        int y, x;
        boolean hasP, hasB;
        boolean hasBotP;
        
        Building(int x, int y) {
            this.y = y;
            this.x = x;
            this.hasP = false;
            this.hasB = false;
            this.hasBotP = false;
        }
    }
    
    class BuildCheck {
        int x, y, build;
        BuildCheck(int x, int y, int build) {
            this.x = x;
            this.y = y;
            this.build = build;
        }
    }
}
