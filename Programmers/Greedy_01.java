import java.util.ArrayList;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        ArrayList<Integer> lostList = new ArrayList<>();
        ArrayList<Integer> reserveList = new ArrayList<>();
        
        for(int idx = 0; idx < lost.length; idx++) {
            lostList.add(lost[idx]);
        }
        for(int idx = 0; idx < reserve.length; idx++) {
            if(lostList.contains(reserve[idx])) {
                lostList.remove(lostList.indexOf(reserve[idx]));
            }
            else {
                reserveList.add(reserve[idx]);
            }
        }
        answer = n - lostList.size();
        
        for(int idx = 0; idx < reserveList.size(); idx++) {
            int temp = reserveList.get(idx);
            if(lostList.contains(temp - 1)) {
                lostList.remove(lostList.indexOf(temp - 1));
                answer++;
                continue;
            }
            if(lostList.contains(temp + 1)) {
                lostList.remove(lostList.indexOf(temp + 1));
                answer++;
            }
        }
        
        return answer;
    }
}
