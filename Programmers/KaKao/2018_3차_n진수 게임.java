class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        
        int cnt = 0;
        int number = 1;
        int turn = 2;
        String numStr = "";
        if(p == 1) { answer.append("0"); cnt++; }
        
        while(cnt < t) {
            numStr = convertNumber(n, number);
            for(int idx = 0; idx < numStr.length(); idx++) {
                if((turn % m == 0 && m == p) || (turn % m == p)) {
                    answer.append(numStr.charAt(idx));   
                    cnt++;
                    if(cnt == t) { break; }
                }
                turn++;
            }
            number++;
        }
        
        return answer.toString();
    }
    public String convertNumber(int n, int number) {
        StringBuilder numStr = new StringBuilder();
        
        int share = number;
        int remain = 0;
        String remainStr = "";
        while(share > 0) {
            remain = share % n;
            share = share / n;
            if(n > 10 && remain >= 10) {
                switch(remain) {
                    case 10:
                        remainStr = "A";
                        break;
                    case 11:
                        remainStr = "B";
                        break;
                    case 12:
                        remainStr = "C";
                        break;
                    case 13:
                        remainStr = "D";
                        break;
                    case 14:
                        remainStr = "E";
                        break;
                    case 15:
                        remainStr = "F";
                        break;
                }
            } else {
                remainStr = Integer.toString(remain);
            }
            numStr.insert(0, remainStr);
            
        }
        
        return numStr.toString();
    }
}
