class Solution {
    static int[][] lockpad;
    static int[][] lockpadBackup;
    static int lockStart;
    static int lockEnd;
    
    public boolean solution(int[][] key, int[][] lock) {
        int padSize = key.length - 1;
        int lockpadSize = lock.length + (padSize * 2);
        int keySize = key.length;
        lockStart = padSize;
        lockEnd = padSize + lock.length;
        lockpad = new int[lockpadSize][lockpadSize];
        lockpadBackup = new int[lockpadSize][lockpadSize];
        int[][] key90 = new int[keySize][keySize];
        int[][] key180 = new int[keySize][keySize];
        int[][] key270 = new int[keySize][keySize];
        
        for(int y = 0; y < lockpadSize; y++) {
            for(int x = 0; x < lockpadSize; x++) {
                lockpad[y][x] = 1;
            }
        }
        
        for(int y = 0; y < lock.length; y++) {
            for(int x = 0; x < lock.length; x++) {
                lockpad[y + padSize][x + padSize] = lock[y][x];
                lockpadBackup[y + padSize][x + padSize] = lock[y][x];
            }
        }
        
        // make key90
        for(int y = 0; y < keySize; y++) {
            for(int x = 0; x < keySize; x++) {
                key90[y][x] = key[x][keySize - y - 1];
            }
        }
        
        //make key180
        for(int y = 0; y < keySize; y++) {
            for(int x = 0; x < keySize; x++) {
                key180[y][x] = key90[x][keySize - y - 1];
            }
        }
        
        //make key270
        for(int y = 0; y < keySize; y++) {
            for(int x = 0; x < keySize; x++) {
                key270[y][x] = key180[x][keySize - y - 1];
            }
        }
        
        for(int y = 0; y < lockpadSize - padSize; y++) {
            for(int x = 0; x < lockpadSize - padSize; x++) {
                for(int ty = 0; ty < keySize; ty++) {
                    for(int tx = 0; tx < keySize; tx++) {
                        lockpad[y + ty][x + tx] += key[ty][tx];
                    }
                }
                if(checkLock()) { return true; }
                
                for(int ty = 0; ty < keySize; ty++) {
                    for(int tx = 0; tx < keySize; tx++) {
                        lockpad[y + ty][x + tx] += key90[ty][tx];
                    }
                }
                if(checkLock()) { return true; }
                
                for(int ty = 0; ty < keySize; ty++) {
                    for(int tx = 0; tx < keySize; tx++) {
                        lockpad[y + ty][x + tx] += key180[ty][tx];
                    }
                }
                if(checkLock()) { return true; }
                
                for(int ty = 0; ty < keySize; ty++) {
                    for(int tx = 0; tx < keySize; tx++) {
                        lockpad[y + ty][x + tx] += key270[ty][tx];
                    }
                }
                if(checkLock()) { return true; }
            }
        }
        
        return false;
    }
    
    public boolean checkLock() {
        for(int y = lockStart; y < lockEnd; y++) {
            for(int x = lockStart; x < lockEnd; x++) {
                if(lockpad[y][x] == 0 || lockpad[y][x] == 2) {
                    rollBack();
                    return false;
                }
            }
        }
        return true;
    }
    
    public void rollBack() {
        for(int y = lockStart; y < lockEnd; y++) {
            for(int x = lockStart; x < lockEnd; x++) {
                lockpad[y][x] = lockpadBackup[y][x];
            }
        }
    }
}
