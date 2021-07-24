import java.util.HashMap

class Solution {
    data class Member(val parent: String?, var cost: Int)
    private val map: HashMap<String, Member> = HashMap()

    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val answer = IntArray(enroll.size)

        for(idx in enroll.indices) {
            map[enroll[idx]] = Member(if(referral[idx] == "-") "root" else referral[idx], 0)
        }

        for(idx in seller.indices) {
            distribution(map[seller[idx]]!!, (amount[idx] * 100).toDouble())
        }

        enroll.forEachIndexed { index, name ->
            answer[index] = map[name]!!.cost
        }

        return answer
    }

    fun distribution(seller: Member, profit: Double) {
        val parentProfit = (profit * 0.1).toInt()

        seller.cost += (profit.toInt() - parentProfit)

        if(parentProfit == 0) { return }
        if(map[seller.parent] == null) { return }

        distribution(map[seller.parent]!!, parentProfit.toDouble())
    }
}
