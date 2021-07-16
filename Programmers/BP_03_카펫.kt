class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        return (1..yellow)
            .filter { yellow % it == 0 }
            .first { brown == ((yellow / it) * 2) + (it * 2) + 4 }
            .run { intArrayOf((yellow / this) + 2, this + 2) }
    }
}
