class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = IntArray(commands.size)
        
        for(i in 0..answer.size - 1) {
            var arr = array.slice(commands[i][0] - 1..commands[i][1] - 1)
            arr = arr.sorted()
            answer[i] = arr[commands[i][2] - 1]
        }
        
        return answer
    }
    
    fun solution2(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map {
            array.slice(IntRange(it[0] - 1, it[1] - 1)).sorted()[it[2] - 1]
        }.toIntArray()
    }
}
