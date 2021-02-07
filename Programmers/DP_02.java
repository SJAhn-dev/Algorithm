class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        
        int[] prevArr = new int[height];
        prevArr[0] = triangle[0][0];
        
        for(int idx = 1; idx < height; idx++) {
            int[] tempArr = new int[height];
            
            tempArr[0] = prevArr[0] + triangle[idx][0];
            
            for(int inIdx = 1; inIdx < idx; inIdx++) {
                tempArr[inIdx] = triangle[idx][inIdx] +
                    Math.max(prevArr[inIdx - 1], prevArr[inIdx]);
            }
            
            tempArr[idx] = prevArr[idx - 1] + triangle[idx][idx];
            
            System.arraycopy(tempArr, 0, prevArr, 0, height);
        }
        
        int maxValue = 0;
        for(int idx = 0; idx < height; idx++) {
            maxValue = Math.max(prevArr[idx], maxValue);
        }
        
        return maxValue;
    }
}
