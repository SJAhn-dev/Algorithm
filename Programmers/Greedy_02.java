class Solution {
    public int solution(String name) {
        int answer = 0;
        int remain = 0;
        boolean[] visited = new boolean[name.length()];
        
        // A 0, B 1, C 2, D 3, E 4, F 5, G 6, H 7, I 8, J 9, K 10, L 11, M 12
        // N 13, O 12, P 11, Q 10, R 9, S 8, T 7, U 6, V 5, W 4, X 3, Y 2, Z 1
        int[] alphaValue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 
                            12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        for(int idx = 0; idx < name.length(); idx++) {
            char alpha = name.charAt(idx);
            int nameValue = alphaValue[alpha - 'A'];
            if(nameValue != 0 && idx != 0) {
                remain++;
            }
            else {
                visited[idx] = true;
            }
            answer += nameValue;
        }
        if(name.length() == 1) { return answer; }
        
        int leftRemain = remain;
        int rightRemain = remain;
        int leftIdx = name.length() - 1;
        int rightIdx = 1;
        int movement = 1;
        
        while(leftRemain != 0 && rightRemain != 0) {
            if(!visited[leftIdx]) { leftRemain --; }
            if(!visited[rightIdx]) { rightRemain --; }
            leftIdx--;
            rightIdx++;
            movement++;
        }
        
        int scope = 1;
        int temp = 0;
        int save = 0;
        while(remain > 0 && save < movement) {
            int left = temp;
            int right = temp;
            
            if(temp + scope < name.length()) {
                right = temp + scope;
            }
            else {
                right = name.length() - (temp + scope);
            }
            
            if(temp - scope >= 0) {
                left = temp - scope;
            }
            else {
                left = name.length() + (temp - scope);
            }
            
            if(!visited[right]) {
                save += scope;
                scope = 1;
                temp = right;
                visited[right] = true;
                remain--;
            }
            else if(!visited[left]) {
                save += scope;
                scope = 1;
                temp = left;
                visited[left] = true;
                remain--;
            }
            else { scope ++; }
        }
        
        return Math.min(answer + movement, answer + save);
    }
}
