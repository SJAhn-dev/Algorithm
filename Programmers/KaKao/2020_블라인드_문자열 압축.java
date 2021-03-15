class Solution {
    public int solution(String s) {
        int answer = s.length();
        int cmpMax = s.length();
        
        for(int cmp = 1; cmp < cmpMax; cmp++) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            String prev = "";
            int startIdx = 0;
            int endIdx = cmp;
            while(endIdx < cmpMax) {
                String temp = s.substring(startIdx, endIdx);
                if(temp.equals(prev)) {
                    cnt++;
                }
                else if(cnt == 0) {
                    sb.append(prev);
                    prev = temp;
                }
                else {
                    sb.append(Integer.toString(cnt + 1));
                    sb.append(prev);
                    prev = temp;
                    cnt = 0;
                }
                
                startIdx += cmp;
                endIdx += cmp;
                
                if(endIdx >= cmpMax) {
                    endIdx = cmpMax;
                    temp = s.substring(startIdx, endIdx);
                    if(temp.equals(prev)) {
                        sb.append(Integer.toString(cnt + 1));
                        sb.append(prev);
                    }
                    else {
                        if(cnt != 0) {
                            sb.append(Integer.toString(cnt + 1));
                        }
                        sb.append(prev);
                        sb.append(temp);
                    }
                }
            }
            answer = Math.min(sb.toString().length(), answer);
        }
        
        return answer;
    }
}
