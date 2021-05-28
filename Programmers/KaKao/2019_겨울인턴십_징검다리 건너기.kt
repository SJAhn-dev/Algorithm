class Solution {
    lateinit var stoneArray: IntArray
    var skip = 0

    fun solution(stones: IntArray, k: Int): Int {
        var answer = 0
        stoneArray = stones

        var left = 0
        var right = 0
        skip = k - 1

        for(stone in stones) { right = stone.coerceAtLeast(right) }

        while(left <= right) {
            var mid = (left + right) / 2

            if(checkPossible(mid)) {
                answer = mid
                left = mid + 1
            }
            else {
                right = mid - 1
            }
        }

        return answer
    }

    fun checkPossible(value: Int): Boolean {
        var canSkip = skip

        for(stone in stoneArray) {
            if(stone - value < 0) {
                canSkip--
                if(canSkip < 0) { return false }
            }
            else { canSkip = skip }
        }

        return true
    }
}
