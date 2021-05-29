import kotlin.collections.ArrayList;

class Solution {
    val dy = intArrayOf(1, -1, 0, 0)
    val dx = intArrayOf(0, 0, 1, -1)
    val direction = arrayOf("DOWN", "UP", "RIGHT", "LEFT", "NONE")

    data class Builder(var x: Int, var y: Int, var dir: String, var cost: Int)

    fun solution(board: Array<IntArray>): Int {
        var minCost = Int.MAX_VALUE

        val boardSize = board.size
        val costHistory:Array<IntArray> = Array(boardSize) { IntArray(boardSize) { Int.MAX_VALUE } }

        val queue:ArrayList<Builder> = ArrayList()
        costHistory[0][0] = Int.MIN_VALUE
        queue.add(Builder(0, 0, direction[4], 0))

        while(queue.isNotEmpty()) {
            val temp: Builder = queue.removeAt(0)
            if(temp.cost > minCost) { continue }
            if(temp.x == boardSize - 1 && temp.y == boardSize - 1) { minCost = minCost.coerceAtMost(temp.cost) }


            for(i in 0..3) {
                val xx = temp.x + dx[i]
                val yy = temp.y + dy[i]
                if(xx !in 0 until boardSize || yy !in 0 until boardSize) { continue }
                if(board[yy][xx] == 1) { continue }

                val nextCost = if(direction[i] != temp.dir) { 600 } else { 100 }

                if(costHistory[yy][xx] < temp.cost + nextCost) { continue }
                costHistory[yy][xx] = temp.cost + nextCost

                queue.add(Builder(xx, yy, direction[i], temp.cost + nextCost))
            }
        }

        return minCost - 500
    }
}
