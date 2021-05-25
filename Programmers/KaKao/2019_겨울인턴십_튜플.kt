class Solution {
    fun solution(s: String): IntArray {
        var answer:ArrayList<Int> = ArrayList()

        var array:List<String> = s.substring(2, s.length - 2).split("},{")
        var list:ArrayList<List<String>> = ArrayList()

        for(tuple in array) {
            list.add(tuple.split(","))
        }

        var sort = list.sortedWith(Comparator<List<String>>{ a, b ->
            when {
                a.size > b.size -> 1
                a.size < b.size -> -1
                else -> 0
            }
        })

        for(tuple in sort) {
            for(num in tuple) {
                if(!answer.contains(Integer.parseInt(num))) {
                    answer.add(Integer.parseInt(num))
                }
            }
        }

        return answer.toIntArray()
    }
    
    fun solution2(s: String): IntArray {
        return s.substring(2 until s.length - 2)
            .split("},{")
            .asSequence()
            .map { it.split(",").map { num -> num.toInt() } }
            .toList()
            .sortedBy { it.size }
            .fold(setOf<Int>()) { acc, list -> acc.union(list) }
            .toIntArray()
    }
}
