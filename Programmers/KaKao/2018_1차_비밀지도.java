class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int idx = 0; idx < n; idx++) {
            int num1 = arr1[idx];
            int num2 = arr2[idx];
            StringBuilder sb = new StringBuilder();
            for(int num = 0; num < n; num++) {
                char wall = (num1 % 2) + (num2 % 2) == 0 ? ' ' : '#';
                sb.insert(0, wall);
                num1 = num1 / 2;
                num2 = num2 / 2;
            }
            answer[idx] = sb.toString();
        }
        return answer;
    }
}
