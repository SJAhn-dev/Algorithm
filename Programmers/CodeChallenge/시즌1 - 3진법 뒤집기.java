class Solution {
    public int solution(int n) {
        String convertNum = convertToRev3(n);
        return Integer.parseInt(convertNum, 3);
    }
    
    public String convertToRev3(int n) {
        StringBuilder num = new StringBuilder();
        int remain = n;
        
        while(remain > 0) {
            num.insert(0, Integer.toString(remain % 3));
            remain = remain / 3;
        }
        
        return num.reverse().toString();
    }
}
