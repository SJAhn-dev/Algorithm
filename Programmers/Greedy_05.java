import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

class Solution {
    int[] rootMap;
    int size;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        size = n;
        ArrayList<Bridge> bridgeList = new ArrayList<>();
        
        for(int[] cost : costs) {
            int parent = Math.min(cost[0], cost[1]);
            int child = Math.max(cost[0], cost[1]);
            
            Bridge bridge = new Bridge(parent, child, cost[2]);
            bridgeList.add(bridge);
        }
        Collections.sort(bridgeList);
        
        rootMap = new int[n];
        for(int idx = 1; idx < n; idx++) {
            rootMap[idx] = idx;
        }
        
        for(Bridge bridge : bridgeList) {
            int parent = bridge.parent;
            int child = bridge.child;
            
            if(rootMap[parent] == rootMap[child]) {
                continue;
            }
            else {
                answer += bridge.cost;
                if(rootMap[parent] < rootMap[child]) {
                    connect(rootMap[child], rootMap[parent]);
                    rootMap[child] = rootMap[parent];
                }
                else {
                    connect(rootMap[parent], rootMap[child]);
                    rootMap[parent] = rootMap[child];
                }
            }
        }
        
        return answer;
    }
    
    public void connect(int temp, int target) {
        for(int idx = 0; idx < size; idx++) {
            if(rootMap[idx] == temp) {
                rootMap[idx] = target;
            }
        }
    }
    
    class Bridge implements Comparable<Bridge>{
        int parent;
        int child;
        int cost;
        
        Bridge(int parent, int child, int cost) {
            this.parent = parent;
            this.child = child;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Bridge obj) {
            if(this.cost == obj.cost) {
                if(this.parent != obj.parent) {
                    return this.parent < obj.parent ? -1 : 1;
                }
                return this.child < obj.child ? -1 : 1;
            }
            return this.cost < obj.cost ? -1 : 1;
        }
    }
}
