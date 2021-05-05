import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] split = line.split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        int t = Integer.parseInt(split[2]);

        int[][] map = new int[r][c];

        for(int y = 0; y < r; y++) {
            String mapLine = br.readLine();
            String[] mapSplit = mapLine.split(" ");
            for(int x = 0; x < c; x++) {
                map[y][x] = Integer.parseInt(mapSplit[x]);
            }
        }
        br.close();

        int result = solution(r, c, t, map);
        System.out.println(result);
    }

    public static int solution(int r, int c, int t, int[][] map) {
        int cleaner1 = 0;
        int cleaner2 = 0;

        for(int y = 0; y < r; y++) {
            if(map[y][0] == -1) {
                cleaner1 = y;
                cleaner2 = y + 1;
                break;
            }
        }

        Queue<Cell> dustQueue = new LinkedList<>();

        Cell[][] room = new Cell[r][c];
        for(int y = 0; y < r; y++) {
            for(int x = 0; x < c; x++) {
                Cell cell = new Cell(x, y, map[y][x]);
                room[y][x] = cell;
                if(map[y][x] > 0) {
                    dustQueue.add(cell);
                }
                else {
                    cell.amount = 0;
                    cell.nextAmount = 0;
                }
            }
        }

        Cell topCleaner = room[cleaner1][0];
        Cell botCleaner = room[cleaner2][0];

        LinkedList<Cell> airflow_top = new LinkedList<>();
        LinkedList<Cell> airflow_bot = new LinkedList<>();

        int[][] move_y = {{0, -1, 0, 1}, {0, 1, 0, -1}};
        int[][] move_x = {{1, 0, -1, 0}, {1, 0, -1, 0}};

        int tx = 1;
        int ty = cleaner1;
        int dir = 0;
        Cell prev = topCleaner;

        while(true) {
            Cell cell = room[ty][tx];
            prev.next = cell;
            cell.prev = prev;
            airflow_top.add(cell);
            prev = cell;

            int dx = tx + move_x[0][dir];
            int dy = ty + move_y[0][dir];

            if(dx < 0 || dx >= c || dy < 0 || dy >= r) {
                dir++;
                tx = tx + move_x[0][dir];
                ty = ty + move_y[0][dir];
            }
            else {
                tx = dx;
                ty = dy;
            }

            if(tx == 0 && ty == cleaner1) {
                cell.next = topCleaner;
                break;
            }
        }

        tx = 1;
        ty = cleaner2;
        dir = 0;
        prev = botCleaner;

        while(true) {
            Cell cell = room[ty][tx];
            prev.next = cell;
            cell.prev = prev;
            airflow_bot.add(cell);
            prev = cell;

            int dx = tx + move_x[1][dir];
            int dy = ty + move_y[1][dir];

            if(dx < 0 || dx >= c || dy < 0 || dy >= r) {
                dir++;
                tx = tx + move_x[1][dir];
                ty = ty + move_y[1][dir];
            }
            else {
                tx = dx;
                ty = dy;
            }

            if(tx == 0 && ty == cleaner2) {
                cell.next = botCleaner;
                break;
            }
        }

        int[] dustMove_y = {0, 0, 1, -1};
        int[] dustMove_x = {1, -1, 0, 0};

        int topSize = airflow_top.size();
        int botSize = airflow_bot.size();

        while(t --> 0) {
            Queue<Cell> dustNext = new LinkedList<>();

            while (!dustQueue.isEmpty()) {
                Cell cell = dustQueue.poll();
                int amount = cell.amount / 5;
                int count = 0;

                for(int idx = 0; idx < 4; idx++) {
                    int dx = cell.x + dustMove_x[idx];
                    int dy = cell.y + dustMove_y[idx];

                    if(dy < 0 || dy >= r || dx < 0 || dx >= c) { continue; }

                    if(room[dy][dx] != botCleaner && room[dy][dx] != topCleaner) {
                        Cell spread = room[dy][dx];
                        spread.nextAmount += amount;
                        count++;
                        if(!dustNext.contains(spread)) {
                            dustNext.add(spread);
                        }
                    }
                }

                cell.nextAmount -= (count * amount);
                if(!dustNext.contains(cell)) {
                    dustNext.add(cell);
                }
            }

            while(!dustNext.isEmpty()) {
                Cell cell = dustNext.poll();
                cell.amount = cell.nextAmount;
                if(cell.amount >= 5) {
                    dustQueue.add(cell);
                }
            }

            int topCnt = topSize - 1;
            int botCnt = botSize - 1;

            while(topCnt >= 0 || botCnt >= 0) {
                if(topCnt >= 0) {
                    Cell cell = airflow_top.get(topCnt);

                    cell.amount = cell.prev.amount;
                    cell.nextAmount = cell.prev.nextAmount;
                    if(dustQueue.contains(cell) && cell.next != topCleaner) {
                        dustQueue.remove(cell);
                        if(!dustQueue.contains(cell.next)) {
                            dustQueue.add(cell.next);
                        }
                    }

                    topCnt--;
                }

                if(botCnt >= 0) {
                    Cell cell = airflow_bot.get(botCnt);

                    cell.amount = cell.prev.amount;
                    cell.nextAmount = cell.prev.nextAmount;
                    if(dustQueue.contains(cell) && cell.next != botCleaner) {
                        dustQueue.remove(cell);
                        if(!dustQueue.contains(cell.next)) {
                            dustQueue.add(cell.next);
                        }
                    }

                    botCnt--;
                }
            }
        }

        int sum = 0;

        for(int y = 0; y < r; y++) {
            for(int x = 0; x < c; x++) {
                sum += room[y][x].amount;
            }
        }

        return sum;
    }

    static class Cell {
        int x, y;
        int amount;
        int nextAmount;
        Cell next;
        Cell prev;

        Cell(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
            this.nextAmount = amount;
            this.next = null;
            this.prev = null;
        }
    }
}
