import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Solution {
    data class Cmd (var state:String, var uid:String)
    
    fun solution(record: Array<String>): Array<String> {
        val answer:ArrayList<String> = ArrayList()
        val msgList:ArrayList<Cmd> = ArrayList()
        val usrMap:HashMap<String, String> = HashMap()

        val enterMsg = "님이 들어왔습니다."
        val leaveMsg = "님이 나갔습니다."

        record.forEach {
            val split = it.split(" ")
            when(split[0]) {
                "Change" -> usrMap[split[1]] = split[2]
                "Enter" -> {
                    usrMap[split[1]] = split[2]
                    msgList.add(Cmd(split[0], split[1]))
                }
                "Leave" -> {
                    msgList.add(Cmd(split[0], split[1]))
                }
            }
        }

        msgList.forEach {
            when(it.state) {
                "Enter" -> answer.add(usrMap[it.uid] + enterMsg)
                "Leave" -> answer.add(usrMap[it.uid] + leaveMsg)
            }
        }

        return answer.toTypedArray()
    }
}
