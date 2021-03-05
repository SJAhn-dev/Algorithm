import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public String[] solution(String[] record) {
        String enterMsg = "님이 들어왔습니다.";
        String leaveMsg = "님이 나갔습니다.";
        final String changeCommand = "Change";
        final String enterCommand = "Enter";
        final String leaveCommand = "Leave";
        
        HashMap<String, String> nameMap = new HashMap<>();
        ArrayList<Msg> logList = new ArrayList<>();
        
        for(String rec : record) {
            String[] log = rec.split(" ");
            
            switch(log[0]) {
                case "Change":
                    nameMap.put(log[1], log[2]);
                    break;
                case "Enter":
                    nameMap.put(log[1], log[2]);
                    logList.add(new Msg(log[1], log[0]));
                    break;
                case "Leave":
                    logList.add(new Msg(log[1], log[0]));
                    break;
            }
        }
        
        int logSize = logList.size();
        String[] answer = new String[logSize];
        for(int idx = 0; idx < logSize; idx++) {
            Msg temp = logList.get(idx);
            
            if(temp.msg.equals("Enter")) {
                answer[idx] = nameMap.get(temp.uid) + enterMsg;
            }
            else {
                answer[idx] = nameMap.get(temp.uid) + leaveMsg;
            }
        }
        
        return answer;
    }
    
    public class Msg{
        String uid;
        String msg;
        
        public Msg(String uid, String msg) {
            this.uid = uid;
            this.msg = msg;
        }
    }
}
