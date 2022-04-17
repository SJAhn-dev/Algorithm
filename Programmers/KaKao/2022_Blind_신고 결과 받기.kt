class Solution {
    data class User(
        val index: Int,
        val name: String,
        var count: Int,
        val reportee: MutableSet<String>
    )
    
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer = IntArray(id_list.size)
        val userMap = mutableMapOf<String, User>()
        
        id_list.forEachIndexed { index, name ->
            userMap[name] = User(index, name, 0, mutableSetOf())
        }
        
        report.forEach { it ->
            val reporter = it.split(" ")[0]
            val target = it.split(" ")[1]
            
            val reporterUser = userMap[reporter]!!
            val targetUser = userMap[target]!!
            if (targetUser.reportee.contains(reporter).not()) {
                targetUser.count++
                targetUser.reportee.add(reporter)
            }
        }
        
        userMap.forEach { name, user ->
            if (user.count >= k) {
                user.reportee.forEach { it ->
                    val sender = userMap[it]!!
                    answer[sender.index]++
                }
            }
        }
        
        return answer
    }
}
