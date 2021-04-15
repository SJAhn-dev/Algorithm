class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int length = signs.length;
        
        for(int idx = 0; idx < length; idx++) {
            answer += signs[idx] ? absolutes[idx] : (absolutes[idx] * -1);
        }
        
        return answer;
    }
}
