import java.util.Stack;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int count = 0;
        boolean[] check = new boolean[n];
        
        // Initialize Stack
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        answer++;
        
        while(count<n) {
            if(!stack.isEmpty()) {
                int temp = stack.pop();
                for(int idx=0; idx<n; idx++) {
                    if(computers[temp][idx]==1 && !check[idx]) {
                        check[idx] = true;
                        count++;
                        stack.add(idx);
                    }
                }
            }
            // 하나의 Network를 종료한 후 다른 Network로 이동
            else {
                answer++;
                for(int idx=0; idx<n; idx++) {
                    if(!check[idx]) {
                        check[idx] = true;
                        count++;
                        stack.add(idx);
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
