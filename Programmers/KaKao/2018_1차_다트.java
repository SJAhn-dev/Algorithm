import java.util.ArrayList;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int num = 0;
        int listSize = 0;
        boolean tenChecker = false;
        ArrayList<Integer> list = new ArrayList<>();
        for(int idx = 0; idx < dartResult.length(); idx++) {
            char temp = dartResult.charAt(idx);
            switch (temp) {
                case '*':
                    list.set(listSize - 1, list.get(listSize - 1) * 2);
                    if(listSize != 1) {
                        list.set(listSize - 2, list.get(listSize - 2) * 2);
                    }
                    tenChecker = false;
                    break;
                case '#':
                    list.set(listSize - 1, list.get(listSize - 1) * -1);
                    tenChecker = false;
                    break;
                case 'S':
                    list.add(num);
                    listSize++;
                    num = 0;
                    tenChecker = false;
                    break;
                case 'D':
                    list.add(num * num);
                    listSize++;
                    num = 0;
                    tenChecker = false;
                    break;
                case 'T':
                    list.add(num * num * num);
                    listSize++;
                    num = 0;
                    tenChecker = false;
                    break;
                default:
                    if(temp == '1') {
                        tenChecker = true;
                        num = Integer.parseInt(String.valueOf(temp));
                    }
                    else if(temp == '0' && tenChecker) {
                        num = 10;
                    }
                    else {
                        num = Integer.parseInt(String.valueOf(temp));
                        tenChecker = false;
                    }
                    break;
            }
        }
        
        for(int idx = 0; idx < list.size(); idx++) {
            answer += list.get(idx);
        }
        
        return answer;
    }
}
