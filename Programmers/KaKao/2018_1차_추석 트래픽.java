import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        
        ArrayList<Timeset> setList = new ArrayList<>();
        
        for(String log : lines) {
            String[] tokens = log.split(" ");
            String[] timeTokens = tokens[1].split(":");
            
            // Processing Time (ms)
            double pTimeF = Double.parseDouble(tokens[2].substring(0, tokens[2].length() - 1));
            int pTime = (int) (pTimeF * 1000.0);
            
            // endTime (ms)
            int endTime = Integer.parseInt(timeTokens[0]) * 60 * 60 * 1000;
            endTime += Integer.parseInt(timeTokens[1]) * 60 * 1000;
            endTime += (int) (Double.parseDouble(timeTokens[2]) * 1000.0);
            
            // startTime (ms)
            int startTime = endTime - pTime + 1;
            
            setList.add(new Timeset(startTime, endTime));
        }
        Collections.sort(setList);
        
        int tempTime = Integer.MIN_VALUE;
        PriorityQueue<Timeset> queue = new PriorityQueue<>();
        for(Timeset set : setList) {
            if(set.end > tempTime + 999) {
                if(set.start > tempTime + 999) {
                    answer = Math.max(answer, queue.size());
                    while(!queue.isEmpty()) {
                        if(set.start > queue.peek().start + 999) {
                            Timeset ts = queue.poll();
                            if(ts.end > ts.start) {
                                queue.add(new Timeset(ts.end, ts.end));
                            }
                        }
                        else {
                            tempTime = queue.peek().start;
                            break;
                        }
                    }
                }
            }
            queue.add(new Timeset(set.start, set.end));
        }
        answer = Math.max(answer, queue.size());
        
        return answer;
    }
    
    public class Timeset implements Comparable<Timeset>{
        public int start, end;
        public Timeset(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Timeset obj) {
            Integer num1 = new Integer(this.start);
            Integer num2 = new Integer(obj.start);
            return num1.compareTo(num2);
        }
    }
}
