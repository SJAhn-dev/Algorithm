import java.util.Stack

class Solution {
    data class Cell(var prev: Int, var next: Int, val index: Int, var expired: Boolean)
    lateinit var map: Array<Cell?>

    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        map = Array(n) { i -> null }

        for(idx in 0 until n) {
            map[idx] = Cell(idx - 1, idx + 1, idx, false)
        }

        var selected: Cell = map[k]!!
        val stack: Stack<Int> = Stack()

        cmd.forEach {
            when(it[0]) {
                'U', 'D' -> {
                    selected = cmdMove(selected,it[0], it.split(" ")[1].toInt())
                }
                'C' -> {
                    stack.add(selected.index)
                    selected.expired = true

                    if(selected.prev != -1) {
                        map[selected.prev]!!.next = selected.next
                    }

                    if(selected.next != n) {
                        map[selected.next]!!.prev = selected.prev
                        selected = map[selected.next]!!
                    }
                    else {
                        selected = map[selected.prev]!!
                    }
                }
                'Z' -> {
                    val revert = map[stack.pop()]!!
                    revert.expired = false

                    if(revert.prev != -1) {
                        map[revert.prev]!!.next = revert.index
                    }

                    if(revert.next != n) {
                        map[revert.next]!!.prev = revert.index
                    }
                }
            }
        }

        val sb = StringBuilder()

        map.forEach { cell ->
            sb.append(if(cell!!.expired) 'X' else 'O')
        }

        return sb.toString()
    }

    fun cmdMove(selected: Cell, input: Char, amount: Int): Cell {
        var temp = selected

        if(input == 'U') {
            for(i in 0 until amount) {
                temp = map[temp.prev]!!
            }
        }
        else {
            for(i in 0 until amount) {
                temp = map[temp.next]!!
            }
        }
        return temp
    }
}
