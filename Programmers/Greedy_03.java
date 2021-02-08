import java.util.ArrayList;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int length = number.length();
        int lastMax = 0;
        int remain = k;
        int answersize = 0;
        if(number.length() == 1) { return "0"; }
        
        ArrayList<Integer> answerList = new ArrayList<>();
        boolean[] removeList = new boolean[length];
        
        int leftIdx = 0;
        int rightIdx = 1;
        while(remain > 0 && rightIdx < length) {
            int left = number.charAt(leftIdx);
            int right = number.charAt(rightIdx);
            
            if(left >= right) {
                answerList.add(leftIdx);
                answersize++;
                lastMax = left;
            }
            else if(right > left) {
                remain--;
                removeList[leftIdx] = true;
                
                if(answerList.size() > 0 && lastMax < right) {
                    int targetIdx = answerList.get(answersize - 1);
                    int newLeft = number.charAt(targetIdx);
                    while(newLeft < right && remain > 0 && answersize > 0) {
                        remain --;
                        answerList.remove(answersize - 1);
                        answersize--;
                        removeList[targetIdx] = true;
                        if(answersize > 0) {
                            targetIdx = answerList.get(answersize - 1);
                            newLeft = number.charAt(targetIdx);
                        }
                    }
                }
                
                lastMax = right;
            }
            
            leftIdx = rightIdx;
            rightIdx ++;
            
            if(remain > length - leftIdx) {
                break;
            }
        }
        
        for(int idx = 0; idx < length - remain; idx++) {
            if(!removeList[idx]) {
                answer += number.charAt(idx);
            }
        }
        
        return answer;
    }
}
