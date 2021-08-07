import java.util.Queue
import java.util.LinkedList

class Solution {
    data class Room(var num:Int, var children:ArrayList<Int>, var isLocked: Boolean, var keyNum: Int)
    lateinit var visited: BooleanArray
    lateinit var rooms: Array<Room>

    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        visited = BooleanArray(n) { false }
        rooms = Array(n) { Room(it, arrayListOf(), false, 0) }

        path.forEach {
            rooms[it.first()].children.add(it.last())
            rooms[it.last()].children.add(it.first())
        }

        rooms[0].children.forEach {
            removeParent(0, rooms[it])
        }

        order.forEach {
            rooms[it.last()].isLocked = true
            rooms[it.last()].keyNum = it.first()
        }

        val queue: Queue<Int> = LinkedList()
        queue.add(0)

        var cycleCheck = 0
        while(queue.isNotEmpty()) {
            val temp = rooms[queue.poll()]

            if(cycleCheck > queue.size) {
                return false
            }

            if(temp.isLocked && !visited[temp.keyNum]) {
                cycleCheck++
                queue.add(temp.num)
                continue
            } else if (temp.isLocked && visited[temp.keyNum]) {
                temp.isLocked = false
                visited[temp.num] = true
            }
            cycleCheck = 0
            visited[temp.num] = true

            temp.children.forEach {
                if(visited[it]) {
                    return@forEach
                }
                queue.add(it)
            }
        }

        return true
    }

    fun removeParent(parent: Int, temp: Room) {
        temp.children.remove(parent)

        temp.children.forEach {
            removeParent(temp.num, rooms[it])
        }
    }
}
