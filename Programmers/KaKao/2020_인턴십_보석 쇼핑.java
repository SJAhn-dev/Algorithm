import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int gemSize = gems.length;

        HashMap<String, Integer> gemMap = new HashMap<>();
        int indexer = -1;
        for(String gem : gems) {
            if(!gemMap.containsKey(gem)) {
                gemMap.put(gem, ++indexer);
            }
        }

        int gemKinds = gemMap.size();
        int[] gemCount = new int[gemKinds];
        int kindCount = 0;
        int saveSize = Integer.MAX_VALUE;

        Queue<Gem> queue = new LinkedList<>();
        for(int idx = 0; idx < gemSize; idx++) {
            String gem = gems[idx];
            int gemIdx = gemMap.get(gem);

            gemCount[gemIdx]++;
            if(gemCount[gemIdx] == 1) {
                kindCount++;
            }

            queue.add(new Gem(gem, idx));

            if(queue.peek().kind.equals(gem)) {
                Gem temp = queue.peek();
                int tempKind = gemMap.get(temp.kind);
                while(gemCount[tempKind] > 1) {
                    queue.poll();
                    gemCount[tempKind]--;

                    temp = queue.peek();
                    tempKind = gemMap.get(temp.kind);
                }
            }

            if(kindCount == gemKinds) {
                if(idx - queue.peek().index < saveSize) {
                    saveSize = idx - queue.peek().index;
                    answer[0] = queue.peek().index + 1;
                    answer[1] = idx + 1;
                }
            }
        }

        return answer;
    }
    
    class Gem {
        String kind;
        int index;

        Gem(String kind, int index){
            this.kind = kind;
            this.index = index;
        }
    }
}
