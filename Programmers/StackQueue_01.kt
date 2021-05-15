import java.util.Queue
import java.util.LinkedList

class Solution {
    data class Work(var progress: Int, var per: Int)
    
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = intArrayOf()
        var queue : Queue<Work> = LinkedList()

        for(idx in progresses.indices) {
            queue.add(Work(progresses[idx], speeds[idx]))
        }

        var accumlate : Int = 0

        while(!queue.isEmpty()) {
            var nextQueue : Queue<Work> = LinkedList()

            while(!queue.isEmpty()) {
                var work : Work = queue.poll()
                work.progress += work.per
                nextQueue.add(work)
            }

            while(!nextQueue.isEmpty() && nextQueue.peek().progress >= 100) {
                accumlate++
                nextQueue.poll()
            }
            queue = nextQueue

            if(accumlate != 0) {
                answer += accumlate
                accumlate = 0
            }
        }

        return answer;
    }
}
