class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        val map = HashMap<String, Int>()
        clothes.forEach {
            if(map.containsKey(it[1])) {
                map[it[1]] = map[it[1]]!! + 1
            }
            else {
                map[it[1]] = 1
            }
        }

        map.values.forEach {
            answer *= (it + 1)
        }
        
        return answer - 1
    }
}
