import java.util.PriorityQueue;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int[] stealF = new int[money.length];
        int[] stealNF = new int[money.length];
        
        stealF[0] = money[0];
        stealF[1] = money[0];
        stealNF[0] = 0;
        stealNF[1] = money[1];
        
        for(int idx = 2; idx < money.length; idx++) {
            stealF[idx] = Math.max(stealF[idx - 2] + money[idx], stealF[idx - 1]);
            stealNF[idx] = Math.max(stealNF[idx - 2] + money[idx], stealNF[idx - 1]);
        }
        
        return Math.max(stealF[money.length - 2], stealNF[money.length - 1]);
    }
}
