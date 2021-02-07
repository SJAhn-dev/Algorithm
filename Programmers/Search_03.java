import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int wordCount = words.length;
        int wordLength = target.length();
        int targetIndex = -1;
        boolean map[][] = new boolean[wordCount][wordCount];
        ArrayList<Integer> beginList = new ArrayList<>();
        
        // Insert Map Info
        for(int y = 0; y < wordCount; y++) {
            if(words[y].equals(target)) { targetIndex = y; }
            for(int x=0; x<wordCount; x++) {
                int diffWithTemp = wordLength;
                int diffWithBegin = wordLength;
                for(int idx=0; idx<wordLength; idx++) {
                    if(words[x].charAt(idx) == words[y].charAt(idx)){
                        diffWithTemp--;
                    }
                    if(words[x].charAt(idx) == begin.charAt(idx)){
                        diffWithBegin--;
                    }
                }
                if(diffWithTemp == 1) { map[y][x] = true; }
                if(diffWithBegin == 1 && !beginList.contains(x)) { beginList.add(x); }
            }
        }
        
        // If no answer -> Return
        if(targetIndex == -1) { return 0; }
        
        // Initialize Queue
        Queue<Integer> queue = new LinkedList<>();
        for(int idx=0; idx<beginList.size(); idx++) {
            queue.add(beginList.get(idx));
        }
        
        // BFS Part
        int phase = 1;
        int phase_size = queue.size();
        while(!queue.isEmpty()) {
            int phase_count = 0;
            int next_phase_size = 0;
            while(phase_count < phase_size) {
                int temp = queue.poll();
                if(temp == targetIndex) { return phase; }
                for(int idx=0; idx<wordCount; idx++) {
                    if(map[temp][idx] && !queue.contains(idx)) {
                        queue.add(idx);
                        next_phase_size++;
                    }
                }
                phase_count++;
            }
            phase++;
            phase_size = next_phase_size;
        }
        
        return 0;
    }
}
