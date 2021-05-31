class Solution {
    data class Stage(var rate:Double, var num:Int)

    fun solution(N: Int, stages: IntArray): IntArray {
        val visit = IntArray(N)
        val clear = IntArray(N)

        stages.forEach {
            val stage = it.coerceAtMost(N)

            for(idx in 0 until stage) {
                visit[idx]++
                if(it > idx + 1) { clear[idx]++ }
            }
        }

        val stageArray:ArrayList<Stage> = ArrayList()
        for(idx in 0 until N) {
            stageArray.add(Stage(clear[idx].toDouble() / visit[idx].toDouble(), idx))
        }

        val sortArray = stageArray.sortedWith(compareBy({it.rate}, {it.num}))
        val answer = IntArray(N)
        sortArray.forEachIndexed { idx, stage ->
            answer[idx] = stage.num + 1
        }

        return answer
    }
}
