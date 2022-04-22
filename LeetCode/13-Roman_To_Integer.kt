class Solution {
    fun romanToInt(s: String): Int {
        var sum = 0
        
        s.forEachIndexed { index, char ->
            when (char) {
                'I' -> {
                    if (index + 1 < s.length && s[index + 1] == 'V') {
                        return@forEachIndexed
                    } else if (index + 1 < s.length && s[index + 1] == 'X') {
                        return@forEachIndexed
                    } else {
                        sum += 1
                    }
                }
                'V' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'I') {
                        sum += 4
                    } else {
                        sum += 5
                    }
                }
                'X' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'I') {
                        sum += 9
                    } else if (index + 1 < s.length && s[index + 1] == 'L') {
                        return@forEachIndexed
                    } else if (index + 1 < s.length && s[index + 1] == 'C') {
                        return@forEachIndexed
                    } else {
                        sum += 10
                    }
                }
                'L' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'X') {
                        sum += 40
                    } else {
                        sum += 50
                    }
                }
                'C' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'X') {
                        sum += 90
                    } else if (index + 1 < s.length && s[index + 1] == 'D') {
                        return@forEachIndexed
                    } else if (index + 1 < s.length && s[index + 1] == 'M') {
                        return@forEachIndexed
                    } else {
                        sum += 100
                    }
                }
                'D' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'C') {
                        sum += 400
                    } else {
                        sum += 500
                    }
                }
                'M' -> {
                    if (index - 1 >= 0 && s[index - 1] == 'C') {
                        sum += 900
                    } else {
                        sum += 1000
                    }
                }
                else -> return@forEachIndexed
            }
        }
        
        return sum
    }
}
