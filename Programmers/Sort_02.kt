class Solution {
    fun solution(numbers: IntArray): String {
        var answer = ""
        var strarr = arrayOf<String>()
        numbers.forEach {
            strarr += it.toString()
        }

        strarr.sortWith(Comparator<String> {
            a, b -> when {
                a.length == b.length -> b.compareTo(a)
                else -> (b + a).compareTo(a + b)
            }
        })

        if(strarr[0] == "0") {
            answer = "0"
            return answer
        }

        for(idx in 0..numbers.size - 1)
            answer += (strarr[idx])

        return answer;
    }
}
