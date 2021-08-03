class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        var answer: Long = money.toLong()
        
        for(idx in 1..count) {
            answer -= (price * idx)
        }
        
        return if (answer < 0) (answer * -1L) else 0
    }
}
