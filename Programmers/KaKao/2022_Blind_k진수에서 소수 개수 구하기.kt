import kotlin.math.sqrt

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val baseN = toBaseN(n, k)
        println(baseN)

        val split = baseN.replace("0+".toRegex(), "0").split("0")

        split.filter { it != "" }.forEach {
            val number = it.toIntOrNull()

            if(number == null) {
                if(isLongPrime(it.toLong())) {
                    answer++
                }
            } else {
                if(isPrime(number)) {
                    answer++
                }
            }
        }

        return answer
    }
    
    fun toBaseN(n: Int, k: Int): String {
        var temp = n
        var builder = ""

        while(temp > 0) {
            builder = (temp % k).toString() + builder
            temp /= k
        }

        return builder
    }
    
    fun isPrime(n: Int): Boolean {
        if(n < 2) { return false }

        for(div in 2 until n) {
            if(n % div == 0) {
                return false
            }
        }

        return true
    }
    
    fun isLongPrime(n : Long): Boolean {
        if(n < 2) { return false }
        else if (n == 2L) { return true }

        val sqrt = sqrt(n.toDouble())

        for(div in 2 until sqrt.toInt()) {
            if(n % div == 0L) {
                return false
            }
        }

        return true
    }
}
