import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        int[] topIdx = new int[board.length];
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board.length; y++) {
                if(board[y][x] != 0) {
                    topIdx[x] = y;
                    break;
                }
            }
        }
        
        for(int move : moves) {
            if(topIdx[move - 1] == board.length) { continue; }
            
            int tempItem = board[topIdx[move - 1]][move - 1];
            
            if(tempItem != 0) {
                if(!stack.isEmpty() && tempItem == stack.peek()) {
                    stack.pop();
                    board[topIdx[move - 1]][move - 1] = 0;
                    answer += 2;
                }
                else {
                    stack.add(tempItem);
                    board[topIdx[move - 1]][move - 1] = 0;
                }
                
                if(topIdx[move - 1] < board.length) {
                    topIdx[move - 1] = topIdx[move - 1] + 1;
                }
            }
        }
        
        return answer;
    }
}
