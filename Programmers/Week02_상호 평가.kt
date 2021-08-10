import kotlin.math.*

class Solution {
    data class Member(var sum: Int, var index: Int, var selfScore: Int, 
                      var maxScore: Int, var minScore: Int)
    
    fun solution(scores: Array<IntArray>): String {
        var answer: String = ""
        val members = Array<Member>(scores.size) { idx ->
            Member(0, idx, 0, Int.MIN_VALUE, Int.MAX_VALUE)
        }

        scores.forEachIndexed { outerIdx, scoreList ->
            scoreList.forEachIndexed { innerIdx, score ->
                members[innerIdx].sum += score

                members[innerIdx].maxScore = max(members[innerIdx].maxScore, score)
                members[innerIdx].minScore = min(members[innerIdx].minScore, score)

                if(innerIdx == outerIdx) {
                    members[innerIdx].selfScore = score
                }
            }
        }
        
        members.forEach { it ->
            var divider = scores.size
            var count = 0
            
            for(idx in 0 until scores.size) {
                if(it.selfScore == scores[idx][it.index]) {
                    count++
                }
            }
            
            if (count == 1 && it.selfScore == it.maxScore) {
                it.sum -= it.selfScore
                divider--
            } else if (count == 1 && it.selfScore == it.minScore) {
                it.sum -= it.selfScore
                divider--
            }
            
            var value = it.sum.toDouble() / divider.toDouble()
            
            answer += if (value >= 90) {
                'A'
            } else if (value < 90 && value >= 80) {
                'B'
            } else if (value < 80 && value >= 70) {
                'C'
            } else if (value < 70 && value >= 50) {
                'D'
            } else {
                'F'
            }
        }
        
        return answer
    }
}
