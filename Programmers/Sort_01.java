import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int cnt = 0;
        
        for(int idx=0; idx<commands.length; idx++) {
            int arrayLength = commands[idx][1] - commands[idx][0] + 1;
            int[] arr = new int[arrayLength];
            System.arraycopy(array, commands[idx][0]-1, arr, 0, arrayLength);
            Arrays.sort(arr);
            answer[cnt] = arr[commands[idx][2] - 1];
            cnt++;
        }
        
        
        return answer;
    }
}
