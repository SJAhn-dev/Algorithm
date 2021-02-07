class Solution {
    static int targetNum;
    static int useNum;
    static boolean find = false;
    static int finalPhase = -1;
    
    public int solution(int N, int number) {
        if(N==number) { return 1; }
        targetNum = number;
        useNum = N;
        
        for(int cnt = 2; cnt < 9; cnt++) {
            dfs(0, cnt, 0);
            if(find) { return finalPhase; }
        }
        
        return -1;
    }
    
    // DFS Searching
    public void dfs(int phase, int count, int num) {
        if(phase == count && num == targetNum) { 
            find = true;
            finalPhase = phase;
            return;
        }
        if(phase == count && num != targetNum) { return; }
        if(find) { return; }
        
        dfs(phase + 1, count, num + useNum);
        dfs(phase + 1, count, num - useNum);
        dfs(phase + 1, count, num * useNum);
        dfs(phase + 1, count, num / useNum);
        
        for(int cnt = count-phase; cnt > 1; cnt--) {
            dfs(phase + cnt, count, num + combNum(cnt));
            dfs(phase + cnt, count, num - combNum(cnt));
            dfs(phase + cnt, count, num * combNum(cnt));
            dfs(phase + cnt, count, num / combNum(cnt));
        }
    }
    
    // Combine useNum ( ex. 5 + 5 => 55 )
    public int combNum(int cnt) {
        int num = useNum;
        for(int i = 1; i < cnt; i++) {
            num = (num * 10) + useNum;
        }
        return num;
    }
}
