class Solution {
    fun solution(table: Array<String>, languages: Array<String>, preference: IntArray): String {
        var answer: String = ""
        val partList = mutableListOf<String>()
        val scoreMap = mutableMapOf<String, Int>()

        table.forEach {
            val parse = it.split(" ")
            partList.add(parse[0])

            for (score in 1 until parse.size) {
                scoreMap["${parse[0]}+${parse[score]}"] = (6 - score)
            }
        }

        var maxScore = 0

        partList.forEach { part ->
            var scoreSum = 0

            languages.forEachIndexed { index: Int, language: String ->
                if(scoreMap.containsKey("$part+$language")) {
                    scoreSum += (scoreMap["$part+$language"]!! * preference[index])
                }
            }

            if (scoreSum > maxScore) {
                answer = part
                maxScore = scoreSum
            } else if (scoreSum == maxScore && compareValues(answer, part) > 0) {
                answer = part
            }
        }

        return answer
    }
}
