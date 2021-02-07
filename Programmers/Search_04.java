import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

class Solution {
    static int[][] remainedTicket;
    static Stack<Integer> route = new Stack<>();
    static boolean finished = false;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        int airportCount;
        int ticketCount = tickets.length;
        int beginIndex = 0;
        final String ICN = "ICN";
        
        // Insert PortList
        ArrayList<String> portList = new ArrayList<>();
        for(int idx=0; idx<ticketCount; idx++) {
            if(!portList.contains(tickets[idx][0])){
                portList.add(tickets[idx][0]);
            }
            if(!portList.contains(tickets[idx][1])){
                portList.add(tickets[idx][1]);
            }
        }
        
        // Sorting Airport List (ArrayList -> Array)
        airportCount = portList.size();
        String[] portArray = portList.toArray(new String[airportCount]);
        Arrays.sort(portArray);
        portList.clear();
        
        // Undo DataType (Array -> ArrayList)
        for(int idx=0; idx<airportCount; idx++) {
            portList.add(portArray[idx]);
            if(portArray[idx].equals(ICN)){
                beginIndex = idx;
            }
        }
        
        // Initialize Remained Ticket 
        remainedTicket = new int[airportCount][airportCount];
        for(int idx=0; idx<ticketCount; idx++) {
            int src = portList.indexOf(tickets[idx][0]);
            int dst = portList.indexOf(tickets[idx][1]);
            remainedTicket[src][dst]++;
        }
        
        // Starting DFS
        route.add(beginIndex);
        dfs(beginIndex, 0, ticketCount, airportCount);
        
        // Print Answer Array
        answer = new String[ticketCount+1];
        for(int idx=0; idx<route.size(); idx++) {
            int temp = route.get(idx);
            answer[idx] = portList.get(temp);
        }
        
        return answer;
    }
    
    public void dfs(int ticketNum, int usedTicket, int ticketCount, int airportCount) {
        // Termination Condition
        if(finished) { return; }
        if(usedTicket==ticketCount) {
            finished = true;
            return;
        }
        
        // Recursive 
        for(int idx=0; idx<airportCount; idx++) {
            if(remainedTicket[ticketNum][idx] > 0) {
                remainedTicket[ticketNum][idx]--;
                route.add(idx);
                dfs(idx, usedTicket+1, ticketCount, airportCount);
                if(!finished) { route.pop(); }
                if(!finished) { remainedTicket[ticketNum][idx]++; }
            }
        }
    }
}
