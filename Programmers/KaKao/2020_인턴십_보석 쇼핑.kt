class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf()
        var minSize = Int.MAX_VALUE
        val gemsKinds = gems.toSet().size

        var kindCount = 0
        val windowMap:LinkedHashMap<String, Int> = LinkedHashMap()

        for(idx in gems.indices) {
            if(!windowMap.containsKey(gems[idx])) {
                kindCount++
            }
            windowMap.remove(gems[idx])
            windowMap[gems[idx]] = idx

            if(windowMap.size == gemsKinds) {
                val minIdx = windowMap.values.first()
                if(idx - minIdx < minSize) {
                    minSize = idx - minIdx
                    answer = intArrayOf(minIdx + 1, idx + 1)
                }
            }
        }

        return answer
    }
}
