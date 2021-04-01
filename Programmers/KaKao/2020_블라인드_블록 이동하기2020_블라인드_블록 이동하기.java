import java.util.HashMap;
import java.util.Objects;

class Solution {
    static int minTime = Integer.MAX_VALUE;
    static int mapSize;
    static int map[][];
    static final String ROW = "ROW";
    static final String COL = "COL";
    static final String UP = "up";
    static final String DOWN = "down";
    static final String LEFT = "left";
    static final String RIGHT = "right";
    static final String ROTATE_ROWLEFT_CLK = "rotate1";
    static final String ROTATE_ROWLEFT_REV = "rotate2";
    static final String ROTATE_ROWRIGHT_CLK = "rotate3";
    static final String ROTATE_ROWRIGHT_REV = "rotate4";
    static final String ROTATE_COLTOP_CLK = "rotate5";
    static final String ROTATE_COLTOP_REV = "rotate6";
    static final String ROTATE_COLBOT_CLK = "rotate7";
    static final String ROTATE_COLBOT_REV = "rotate8";
    static HashMap<Log, Integer> logger;
    
    public int solution(int[][] board) {
        mapSize = board.length;
        map = board;
        logger = new HashMap<>();
        
        logger.put(new Log(0, 1, 0, 0), 0);
        moveRobot(0, 1, 0, 0, null, ROW, 0);
        
        return minTime;
    }
    
    public void moveRobot(int x1, int x2, int y1, int y2, String prev, String shape, int time) {
        if(checkGoal(x1, x2, y1, y2)) {
            minTime = Math.min(minTime, time);
            return;
        }
        
        if(shape == ROW) {
            // 아래
            if(y1 + 1 < mapSize && map[y1 + 1][x1] != 1 && map[y2 + 1][x2] != 1) {
                if(prev != UP) {
                    Log log = new Log(x1, x2, y1 + 1, y2 + 1);
                    if((logger.containsKey(log) && logger.get(log) > time + 1) || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x2, y1 + 1, y2 + 1, DOWN, ROW, time + 1);
                    }
                }
            }
            // 오른쪽
            if(x2 + 1 < mapSize && map[y1][x2 + 1] != 1) {
                if(prev != LEFT) {
                    Log log = new Log(x1 + 1, x2 + 1, y1, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1 + 1, x2 + 1, y1, y2, RIGHT, ROW, time + 1);
                    }
                }
                
            }
            // 왼쪽
            if(x1 - 1 >= 0 && map[y1][x1 - 1] != 1) {
                if(prev != RIGHT) {
                    Log log = new Log(x1 - 1, x2 - 1, y1, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1 - 1, x2 - 1, y1, y2, LEFT, ROW, time + 1);
                    }
                }
            }
            // 위쪽
            if(y1 - 1 >= 0 && map[y1 - 1][x1] != 1 && map[y1 - 1][x2] != 1) {
                if(prev != DOWN) {
                    Log log = new Log(x1, x2, y1 - 1, y2 - 1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x2, y1 - 1, y2 - 1, UP, ROW, time + 1);
                    }
                }
            }
            // 좌측을 축으로 시계방향 회전
            if(y1 + 1 < mapSize && map[y1 + 1][x1] != 1 && map[y1 + 1][x2] != 1) {
                if(prev != ROTATE_COLTOP_REV) {
                    Log log = new Log(x1, x1, y1, y1 + 1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x1, y1, y1 + 1, ROTATE_ROWLEFT_CLK, COL, time + 1);
                    }
                }
            }
            // 좌측을 축으로 반시계방향 회전
            if(y1 - 1 >= 0 && map[y1 - 1][x1] != 1 && map[y1 - 1][x2] != 1) {
                if(prev != ROTATE_COLBOT_CLK) {
                    Log log = new Log(x1, x1, y1 - 1, y1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x1, y1 - 1, y1, ROTATE_ROWLEFT_REV, COL, time + 1);
                    }
                }
            }
            // 우측을 축으로 시계방향 회전
            if(y1 - 1 >= 0 && map[y1 - 1][x1] != 1 && map[y1 -1][x2] != 1) {
                if(prev != ROTATE_COLBOT_REV) {
                    Log log = new Log(x2, x2, y1 - 1, y1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x2, x2, y1 - 1, y1, ROTATE_ROWRIGHT_CLK, COL, time + 1);
                    }
                }
            }
            // 우측을 축으로 반시계방향 회전
            if(y1 + 1 < mapSize && map[y1 + 1][x1] != 1 && map[y1 + 1][x2] != 1) {
                if(prev != ROTATE_COLTOP_CLK) {
                    Log log = new Log(x2, x2, y1, y1 + 1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x2, x2, y1, y1 + 1, ROTATE_ROWRIGHT_REV, COL, time + 1);
                    }
                }
            }
        }
        else {
            // 아래
            if(y2 + 1 < mapSize && map[y2 + 1][x1] != 1) {
                if(prev != UP) {
                    Log log = new Log(x1, x1, y2, y2 + 1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x1, y2, y2 + 1, DOWN, COL, time + 1);
                    }
                }
            }
            // 오른쪽
            if(x1 + 1 < mapSize && map[y1][x1 + 1] != 1 && map[y2][x2 + 1] != 1) {
                if(prev != LEFT) {
                    Log log = new Log(x1 + 1, x2 + 1, y1, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1 + 1, x2 + 1, y1, y2, RIGHT, COL, time + 1);
                    }
                }
            }
            // 왼쪽
            if(x1 - 1 >= 0 && map[y1][x1 - 1] != 1 && map[y2][x2 - 1] != 1) {
                if(prev != RIGHT) {
                    Log log = new Log(x1 - 1, x2 - 1, y1, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1 - 1, x2 - 1, y1, y2, LEFT, COL, time + 1);
                    }
                }
            }
            // 위쪽
            if(y1 - 1 >= 0 && map[y1 - 1][x1] != 1) {
                if(prev != DOWN) {
                    Log log = new Log(x1, x2, y1 - 1, y1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x2, y1 - 1, y1, UP, COL, time + 1);
                    }
                }
            }
            // 아래를 축으로 시계방향 회전
            if(x2 + 1 < mapSize && map[y1][x1 + 1] != 1 && map[y2][x2 + 1] != 1) {
                if(prev != ROTATE_ROWLEFT_REV) {
                    Log log = new Log(x2, x2 + 1, y2, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x2, x2 + 1, y2, y2, ROTATE_COLBOT_CLK, ROW, time + 1);
                    }
                }
            }
            // 아래를 축으로 반시계방향 회전
            if(x2 - 1 >= 0 && map[y1][x2 - 1] != 1 && map[y2][x2 - 1] != 1) {
                if(prev != ROTATE_ROWRIGHT_CLK) {
                    Log log = new Log(x2 - 1, x2, y2, y2);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x2 - 1, x2, y2, y2, ROTATE_COLBOT_REV, ROW, time + 1);
                    }
                }
            }
            // 위를 축으로 시계방향 회전
            if(x1 - 1 >= 0 && map[y2][x2 - 1] != 1 && map[y1][x1 - 1] != 1) {
                if(prev != ROTATE_ROWRIGHT_REV) {
                    Log log = new Log(x1 - 1, x1, y1, y1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1 - 1, x1, y1, y1, ROTATE_COLTOP_CLK, ROW, time + 1);
                    }
                }
            }
            // 위를 축으로 반시계방향 회전
            if(x1 + 1 < mapSize && map[y2][x2 + 1] != 1 && map[y1][x1 + 1] != 1) {
                if(prev != ROTATE_ROWLEFT_CLK) {
                    Log log = new Log(x1, x1 + 1, y1, y1);
                    if(logger.containsKey(log) && logger.get(log) > time + 1 || !logger.containsKey(log)) {
                        logger.put(log, time + 1);
                        moveRobot(x1, x1 + 1, y1, y1, ROTATE_COLTOP_REV, ROW, time + 1);
                    }
                }
            }
        }
    }
    
    public boolean checkGoal(int x1, int x2, int y1, int y2) {
        if(x1 == mapSize - 1 && y1 == mapSize - 1) {
            return true;
        }
        else if(x2 == mapSize - 1 && y2 == mapSize - 1) {
            return true;
        }
        return false;
    }
    
    class Log {
        int x1, x2, y1, y2;
        Log(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        
        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(!(o instanceof Log))
                return false;
            Log log = (Log) o;
            
            return this.x1 == log.x1 && this.x2 == log.x2 && 
                this.y1 == log.y1 && this.y2 == log.y2;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x1, x2, y1, y2);
        }
    }
}
