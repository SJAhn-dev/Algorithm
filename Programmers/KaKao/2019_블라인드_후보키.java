import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(String[][] relation) {
        int colSize = relation[0].length;
        int dataSize = relation.length;
        ArrayList<Integer> keyList = new ArrayList<>();
        
        for(int colBit = 1; colBit < (1 << colSize); colBit++) {
            HashSet<String> dataSet = new HashSet<>();
            for(int row = 0; row < dataSize; row++) {
                StringBuilder sb = new StringBuilder();
                for(int col = 0; col < colSize; col++) {
                    if((colBit & (1 << col)) > 0) {
                        sb.append(relation[row][col]);
                    }
                }
                dataSet.add(sb.toString());
            }
            if(dataSet.size() == dataSize) {
                if(isMinkey(keyList, colBit)) {
                    keyList.add(colBit);
                }
            }
        }
        
        return keyList.size();
    }
    
    public boolean isMinkey(ArrayList<Integer> keyList, int key) {
        for(int idx = 0; idx < keyList.size(); idx++) {
            int tempKey = keyList.get(idx);
            if((tempKey & key) == key) {
                keyList.set(idx, key);
                return false;
            }
            else if((tempKey & key) == tempKey) {
                return false;
            }
        }
        return true;
    }
}
