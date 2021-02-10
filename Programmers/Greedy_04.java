import java.util.ArrayList;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int minValue = people[0];
        ArrayList<Integer> remainList = new ArrayList<>();
        remainList.add(people[0]);
        for(int idx = 1; idx < people.length; idx++) {
            if(minValue + people[idx] <= limit) {
                remainList.add(people[idx]);
            }
            else {
                answer++;
            }
        }
        
        int maxIdx = remainList.size() - 1;
        int minIdx = 0;
        while(maxIdx >= minIdx) {
            int maxVal = remainList.get(maxIdx);
            int minVal = remainList.get(minIdx);
            
            if(maxIdx == minIdx) {
                answer++;
                break;
            }
            
            if(maxVal + minVal > limit) {
                answer++;
                maxIdx--;
            }
            else {
                answer++;
                minIdx++;
                maxIdx--;
            }
        }
        
        return answer;
    }
}
