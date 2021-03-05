import java.util.Collections;
import java.util.ArrayList;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[][] status = new int[2][N];
        
        for(int usr : stages) {
            if(usr == N + 1) {
                usr = N;
            } else {
                status[1][usr - 1]++;
            }
            
            for(int idx = 0; idx < usr; idx++) {
                status[0][idx]++;
            }
        }
        
        ArrayList<Stage> stageList = new ArrayList<>();
        for(int idx = 0; idx < N; idx++) {
            if(status[1][idx] == 0 || status[0][idx] == 0) {
                stageList.add(new Stage(idx + 1, 0));
            }
            else {
                double failure = ((double) status[1][idx]) / ((double)status[0][idx]);
                stageList.add(new Stage(idx + 1, failure));
            }
        }
        Collections.sort(stageList);
        
        for(int idx = 0; idx < N; idx++) {
            answer[idx] = stageList.get(idx).stageNum;
        }
        
        return answer;
    }
    
    public class Stage implements Comparable<Stage> {
        int stageNum;
        double failure;
        
        public Stage(int stageNum, double failure) {
            this.stageNum = stageNum;
            this.failure = failure;
        }
        
        @Override
        public int compareTo(Stage obj) {
            if(this.failure != obj.failure) {
                Double fail = this.failure;
                Double objfail = obj.failure;
                return objfail.compareTo(fail);
            }
            else {
                Integer thisnum = this.stageNum;
                Integer objnum = obj.stageNum;
                return thisnum.compareTo(objnum);
            }
        }
        
    }
}
