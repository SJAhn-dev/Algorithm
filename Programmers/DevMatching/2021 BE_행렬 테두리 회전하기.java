import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        Cell[][] board = new Cell[rows][columns];

        int num = 1;

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {
                board[y][x] = new Cell(x, y, num);
                num++;
            }
        }

        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        int[] answer = new int[queries.length];

        int qidx = 0;
        for(int[] query : queries) {
            Queue<Cell> queue = new LinkedList<>();

            int startY = query[0] - 1;
            int startX = query[1] - 1;
            int endY = query[2] - 1;
            int endX = query[3] - 1;
            int min = Integer.MAX_VALUE;

            int tx = startX;
            int ty = startY;
            int px = startX;
            int py = startY + 1;
            int dir = 0;

            while(true) {
                Cell temp = board[ty][tx];
                temp.nextValue = board[py][px].value;
                queue.add(temp);
                min = Math.min(min,temp.value);

                int nx = tx + dx[dir];
                int ny = ty + dy[dir];

                py = ty;
                px = tx;

                if(nx < startX || nx > endX || ny < startY || ny > endY) {
                    dir++;
                    tx = tx + dx[dir];
                    ty = ty + dy[dir];
                }
                else {
                    tx = nx;
                    ty = ny;
                }

                if(dir == 3 && ty == startY && tx == startX) {
                    break;
                }
            }

            while (!queue.isEmpty()) {
                Cell cell = queue.poll();
                cell.value = cell.nextValue;
            }

            answer[qidx++] = min;
        }

        return answer;
    }
    
    class Cell {
        int x, y;
        int value, nextValue;

        Cell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.nextValue = value;
        }
    }
}

