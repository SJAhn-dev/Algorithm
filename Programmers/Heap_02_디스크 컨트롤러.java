import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> waitQueue = new PriorityQueue<>(new Comparator<Job>() {
           @Override 
            public int compare(Job j1, Job j2) {
                return j1.inputTime <= j2.inputTime ? -1 : 1;
            }
        });
        PriorityQueue<Job> processQueue = new PriorityQueue<>();
        
        for(int[] job : jobs) {
            Job jb = new Job(job[1], job[0]);
            waitQueue.add(jb);
        }
        
        int tempTime = 0;
        while(!waitQueue.isEmpty() || !processQueue.isEmpty()) {
            if(!waitQueue.isEmpty()) {
                while(waitQueue.peek().inputTime <= tempTime) {
                    processQueue.add(waitQueue.poll());
                    if(waitQueue.isEmpty()) { break; }
                }
            }
            
            if(processQueue.isEmpty() && !waitQueue.isEmpty()) {
                tempTime = waitQueue.peek().inputTime;
                continue;
            }
            
            Job job = processQueue.poll();
            answer += (job.processTime + (tempTime - job.inputTime));
            tempTime += job.processTime;
        }
        
        return answer / jobs.length;
    }
    
    class Job implements Comparable<Job> {
        int processTime;
        int inputTime;
        
        Job(int processTime, int inputTime) {
            this.processTime = processTime;
            this.inputTime = inputTime;
        }
        
        @Override
        public int compareTo(Job obj) {
            return this.processTime < obj.processTime ? -1 : 1;
        }
    }
}
