import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        Arrays.sort(times);
        
        long left = 0;
        long right = ((long) times[times.length - 1]) * ((long) n);
        long sum;
        long mid;
        
        while(left <= right) {
            mid = (left + right) / 2;
            sum = 0;
            
            for(int idx = 0; idx < times.length; idx++) {
                sum += mid / times[idx];
                
                if(sum >= n) {
                    break;
                }
            }
            
            if(n > sum) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }
        }
        
        return answer;
    }
}
