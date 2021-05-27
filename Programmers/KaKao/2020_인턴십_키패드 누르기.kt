import kotlin.text.StringBuilder

class Solution {
    val dist_2 = arrayOf(3, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 4)
    val dist_5 = arrayOf(2, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 3)
    val dist_8 = arrayOf(1, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 2)
    val dist_0 = arrayOf(0, 4, 3, 4, 3, 2, 3, 2, 1, 2, 1, 1)
    var HAND = ""

    fun solution(numbers: IntArray, hand: String): String {

        var leftTemp = 10
        var rightTemp = 11
        HAND = hand
        var answer = StringBuilder()

        for(number in numbers) {
            when(number) {
                1, 4, 7 -> {
                    answer.append("L")
                    leftTemp = number
                }
                3, 6, 9 ->  {
                    answer.append("R")
                    rightTemp = number
                }
                else -> {
                    val near = checkDist(number, leftTemp, rightTemp)
                    answer.append(near)
                    if(near == "L") { leftTemp = number }
                    else { rightTemp = number }
                }
            }
        }

        return answer.toString()
    }

    fun checkDist(target: Int, leftTemp: Int, rightTemp: Int): String {
        var leftDist = 0
        var rightDist = 0

        when(target) {
            2 -> {
                leftDist = dist_2[leftTemp]
                rightDist = dist_2[rightTemp]
            }
            5 -> {
                leftDist = dist_5[leftTemp]
                rightDist = dist_5[rightTemp]
            }
            8 -> {
                leftDist = dist_8[leftTemp]
                rightDist = dist_8[rightTemp]
            }
            0 -> {
                leftDist = dist_0[leftTemp]
                rightDist = dist_0[rightTemp]
            }
        }

        if(leftDist == rightDist) {
            if(HAND == "left") { return "L" }
            else { return "R" }
        }
        else if (leftDist > rightDist) {
            return "R"
        }
        else {
            return "L"
        }
    }
}
