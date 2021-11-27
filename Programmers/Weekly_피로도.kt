class Solution {
    lateinit var visited: BooleanArray
    lateinit var dungeonList: Array<IntArray>
    var number: Int = 0
    var maxCount: Int = 0
    
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        number = dungeons.size
        dungeonList = dungeons
        visited = BooleanArray(dungeons.size) { false }
        
        goDungeon(k, 0)
        
        return maxCount
    }
    
    fun goDungeon(remain: Int, count: Int) {
        if (count > maxCount) { maxCount = count }
        if (remain <= 0) { return }
        
        for (idx in 0 until number) {
            if (!visited[idx] && remain >= dungeonList[idx][0]) {
                visited[idx] = true
                goDungeon(remain - dungeonList[idx][1], count + 1)
                visited[idx] = false
            }
        }
    }
}
