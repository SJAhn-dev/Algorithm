class Solution {
    var keyList:ArrayList<Int> = ArrayList()

    fun solution(relation: Array<Array<String>>): Int {
        val entityCnt = relation.size
        val columnSize = relation[0].size

        for(select in 1 until (1.shl(columnSize))) {
            val dataSet:HashSet<String> = HashSet()
            for(row in 0 until entityCnt) {
                val sb = StringBuilder()
                for (col in 0 until columnSize) {
                    if(select.and(1.shl(col)) > 0) {
                        sb.append(relation[row][col])
                    }
                }
                dataSet.add(sb.toString())
            }

            if(dataSet.size == entityCnt && isMinKey(select)) {
                keyList.add(select)
            }
        }

        return keyList.size
    }

    fun isMinKey(key: Int): Boolean {
        for(compareKey in keyList) {
            val res = key.and(compareKey)
            if(res == key) {
                keyList.add(key)
                keyList.remove(compareKey)
                return false
            }
            else if (res == compareKey) {
                return false
            }
        }

        return true
    }
}
