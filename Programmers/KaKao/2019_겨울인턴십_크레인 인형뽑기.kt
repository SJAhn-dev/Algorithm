import java.util.Stack

class Solution {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0

        var bucket: Stack<Int> = Stack()
        val boardSize = board.size

        for(move in moves) {
            var temp = 0
            for(idx in 0 until boardSize) {
                if(board[idx][move - 1] != 0) {
                    temp = board[idx][move - 1]
                    board[idx][move - 1] = 0
                    break
                }
            }

            if(temp == 0) { continue }

            if(!bucket.isEmpty() && bucket.peek() == temp) {
                bucket.pop()
                answer += 2
            }
            else {
                bucket.add(temp)
            }
        }

        return answer
    }
}
