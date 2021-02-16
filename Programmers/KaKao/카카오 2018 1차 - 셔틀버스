import java.util.PriorityQueue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer;
        int tempBus = 540;
        int finalBus = tempBus + ((n - 1) * t);
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(String time : timetable) {
            String[] timeSplit = time.split(":");
            int minutes = (Integer.parseInt(timeSplit[0]) * 60) + Integer.parseInt(timeSplit[1]);
            queue.add(minutes);
        }
        
        int conTime = 0;
        boolean finalSeat = false;
        while(tempBus <= finalBus) {
            int seat = 0;
            boolean arrive = false;
            
            while(!arrive) {
                if(queue.size() == 0) {
                    arrive = true;
                    conTime = finalBus;
                    break;
                }
                if(queue.peek() <= tempBus) {
                    conTime = queue.poll();
                    seat++;
                }
                else { arrive = true; }
                
                if(seat == m) { arrive = true; }
            }
            
            if(tempBus == finalBus) {
                if(seat < m) {
                    conTime = finalBus;
                    finalSeat = true;
                }
                if(conTime == 0){
                    conTime = finalBus;
                }
            }
            tempBus += t;
        }
        int hour;
        int min;
        
        if(conTime == finalBus && finalSeat) {
            hour = finalBus / 60;
            min = finalBus % 60;
        }
        else {
            hour = conTime / 60;
            min = (conTime % 60) - 1;
            if(min == -1) {
                hour--;
                min = 59;
            }
        }
        
        answer = timeToString(hour, min);
        
        return answer.toString();
    }
    
    public String timeToString(int hour, int min) {
        StringBuilder timeString = new StringBuilder();
        
        if(hour < 10) { timeString.append("0"); }
        timeString.append(Integer.toString(hour));
        timeString.append(":");
        
        if(min < 10) { timeString.append("0"); }
        timeString.append(Integer.toString(min));
        
        return timeString.toString();
    }
}
