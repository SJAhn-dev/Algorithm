import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int max = 0;
        int maxCount = 0;
        int answerCount = answers.length;
        
        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] num3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        for(int idx = 0; idx < answerCount; idx++) {
            if(answers[idx] == num1[idx % 5])
                count1++;
            if(answers[idx] == num2[idx % 8])
                count2++;
            if(answers[idx] == num3[idx % 10])
                count3++;
        }
        
        max = Math.max(count1, count2);
        max = Math.max(max, count3);
        if(count1 == max)
            maxCount++;
        if(count2 == max)
            maxCount++;
        if(count3 == max)
            maxCount++;
        
        int[] answer = new int[maxCount];
        int index = 0;
        
        if(count1 == max) {
            answer[index] = 1;
            index++;
        }
        if(count2 == max) {
            answer[index] = 2;
            index++;
        }
        if(count3 == max) {
            answer[index] = 3;
            index++;
        }
        
        return answer;
    }
}
