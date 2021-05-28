class Solution {
    var bannedId: ArrayList<Banid> = arrayListOf()
    var caseSet:HashSet<HashSet<String>> = HashSet()
    lateinit var possible: Array<BooleanArray>
    lateinit var usedId: BooleanArray
    lateinit var idArray: Array<String>

    data class Banid (var index: Int, var id: String)

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        possible = Array(banned_id.size) { BooleanArray(user_id.size) }
        usedId = BooleanArray(user_id.size)
        idArray = user_id

        for(ban in banned_id.indices) {
            var count = 0
            var lastIndex = -1

            for(usr in user_id.indices) {
                if(idCheck(user_id[usr], banned_id[ban])) {
                    count++
                    lastIndex = usr
                    possible[ban][usr] = true
                }
            }

            if(count == 1) {
                usedId[lastIndex] = true
            }
            else {
                bannedId.add(Banid(ban, banned_id[ban]))
            }
        }

        if(bannedId.isEmpty()) { return 1 }
        else { countCase(HashSet(),0) }

        return caseSet.size
    }

    fun countCase(set: HashSet<String>, index: Int) {
        if(index == bannedId.size) {
            if(!caseSet.contains(set)) {
                caseSet.add(set)
            }
            return
        }

        for(i in usedId.indices) {
            if(possible[index][i] && !usedId[i]) {
                usedId[i] = true
                val newSet = HashSet(set)
                newSet.add(idArray[i])
                countCase(newSet, index + 1)
                usedId[i] = false
            }
        }
    }

    fun idCheck(user_id: String, banned_id: String): Boolean {
        if(user_id.length != banned_id.length) { return false }

        for(idx in user_id.indices) {
            val user = user_id[idx]
            val ban = banned_id[idx]

            if(ban == '*') { continue }
            if(user != ban) { return false }
        }

        return true
    }
}
