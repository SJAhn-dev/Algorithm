import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);
        int l = Integer.parseInt(split[1]);
        int r = Integer.parseInt(split[2]);

        int[][] map = new int[n][n];

        for(int y = 0; y < n; y++) {
            String mapLine = br.readLine();
            String[] mapSplit = mapLine.split(" ");
            for(int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(mapSplit[x]);
            }
        }
        br.close();

        int result = solution(n, l, r, map);
        System.out.println(result);
    }

    public static int solution(int n, int l, int r, int[][] map) {
        Cell[][] board = new Cell[n][n];
        int count = 0;

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                board[y][x] = new Cell(x, y, map[y][x]);
            }
        }

        int[] ax = {0, 0, 1, -1};
        int[] ay = {1, -1, 0, 0};

        while(true) {
            Stack<Union> stack = new Stack<>();

            for(int y = 0; y < n; y++) {
                for(int x = 0; x < n; x++) {
                    Cell temp = board[y][x];
                    for(int idx = 0; idx < 4; idx++) {
                        int dy = y + ay[idx];
                        int dx = x + ax[idx];

                        if(dy >= 0 && dy < n && dx >= 0 && dx < n) {
                            Cell comb = board[dy][dx];
                            int dif = Math.abs(temp.population - comb.population);
                            if(dif >= l && dif <= r) {
                                if(temp.union != null && comb.union != null && temp.union != comb.union) {
                                    Union union = comb.union;
                                    combineUnion(temp.union, comb.union);
                                    stack.remove(union);
                                }
                                else if(temp.union != null && comb.union == null) {
                                    temp.union.cellList.add(comb);
                                    comb.union = temp.union;
                                    temp.union.sum += comb.population;
                                }
                                else if(temp.union == null && comb.union != null) {
                                    comb.union.cellList.add(temp);
                                    temp.union = comb.union;
                                    comb.union.sum += temp.population;
                                }
                                else if(temp.union == null && comb.union == null){
                                    Union union = new Union();
                                    union.sum += (temp.population + comb.population);
                                    union.cellList.add(temp);
                                    union.cellList.add(comb);
                                    temp.union = union;
                                    comb.union = union;
                                    stack.add(union);
                                }
                            }
                        }
                    }
                }
            }

            if(stack.isEmpty()) { break; }
            else { count++; }

            while(!stack.isEmpty()) {
                Union union = stack.pop();
                int size = union.cellList.size();
                int population = union.sum / size;
                for(int idx = 0; idx < size; idx++) {
                    Cell cell = union.cellList.get(idx);
                    cell.union = null;
                    cell.population = population;
                }
            }
        }

        return count;
    }

    public static void combineUnion(Union u1, Union u2) {
        int size = u2.cellList.size();
        u1.sum += u2.sum;
        for(int idx = 0; idx < size; idx++) {
            Cell cell = u2.cellList.get(idx);
            u1.cellList.add(cell);
            cell.union = u1;
        }
    }
}

class Cell {
    int x, y;
    int population;
    Union union;

    Cell(int x, int y, int population) {
        this.x = x;
        this.y = y;
        this.population = population;
        this.union = null;
    }
}

class Union {
    ArrayList<Cell> cellList;
    int sum;

    Union() {
        this.cellList = new ArrayList<>();
        this.sum = 0;
    }
}
