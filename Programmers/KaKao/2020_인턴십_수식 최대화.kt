import kotlin.math.abs
import kotlin.text.StringBuilder

class Solution {
    fun solution(expression: String): Long {
        var answer: Long = 0

        val cases = listOf("*+-", "*-+", "+*-","+-*", "-*+", "-+*")
        val sb = StringBuilder()
        val parseExpress:ArrayList<String> = ArrayList()

        for(idx in expression.indices) {
            when(expression[idx]) {
                '*', '+', '-' -> {
                    parseExpress.add(sb.toString())
                    sb.clear()
                    parseExpress.add(expression[idx].toString())
                }
                else -> {
                    sb.append(expression[idx])
                }
            }
            if(idx == expression.length - 1) { parseExpress.add(sb.toString()) }
        }

        for(case in cases.indices) {
            val listCopy:ArrayList<String> = ArrayList(parseExpress)

            for(op in cases[case]) {
                while(listCopy.contains(op.toString())) {
                    val index = listCopy.indexOf(op.toString())
                    val left = listCopy[index - 1]
                    val right = listCopy[index + 1]
                    var calc:Long = 0

                    when(op) {
                        '*' -> calc = (left.toLong() * right.toLong())
                        '+' -> calc = (left.toLong() + right.toLong())
                        '-' -> calc = (left.toLong() - right.toLong())
                    }

                    listCopy.removeAt(index + 1)
                    listCopy.removeAt(index)
                    listCopy[index - 1] = calc.toString()
                }
            }

            answer = answer.coerceAtLeast(abs(listCopy[0].toLong()))
        }

        return answer
    }
}
