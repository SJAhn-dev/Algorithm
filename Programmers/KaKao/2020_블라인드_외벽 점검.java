import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    ArrayList<LinkedList<Integer>> distPermutation;
    int[] distList;
    int[] circleWeak;
    boolean[] distVisited;
    int weakCount;
    int distCount;
    int wallLength;

    public int solution(int n, int[] weak, int[] dist) {
        wallLength = n;
        weakCount = weak.length;
        distCount = dist.length;
        distList = dist;
        circleWeak = new int[weakCount * 2];

        for(int idx = 0; idx < weakCount; idx++) {
            circleWeak[idx] = weak[idx];
            circleWeak[idx + weakCount] = weak[idx] + wallLength;
        }

        for(int num = 0; num < distCount; num++) {
            distPermutation = new ArrayList<>();
            distVisited = new boolean[distCount];
            makeDistPermutation(null, num + 1);

            for (LinkedList<Integer> linkedList : distPermutation) {
                if (weakCheck(linkedList)) {
                    return num + 1;
                }
            }
        }

        return -1;
    }

    public boolean weakCheck(LinkedList<Integer> dist) {
        for(int weakStart = 0; weakStart < weakCount; weakStart++) {
            int distIdx = -1;
            int tempEnd = -1;
            int remain = weakCount;

            for(int weakIdx = weakStart; weakIdx < weakStart + weakCount; weakIdx++) {
                int weak = circleWeak[weakIdx];
                if(tempEnd < weak) {
                    if(distIdx + 1 >= dist.size()) { break; }
                    tempEnd = weak + dist.get(distIdx + 1);
                    distIdx++;
                }
                remain--;
            }
            if(remain == 0) { return true; }
        }

        return false;
    }

    public void makeDistPermutation(LinkedList<Integer> list, int max) {
        if(list != null && list.size() == max) {
            distPermutation.add(list);
        }

        if(list == null) {
            for(int idx = 0; idx < distCount; idx++) {
                if(!distVisited[idx]) {
                    distVisited[idx] = true;
                    LinkedList<Integer> newList = new LinkedList<>();
                    newList.add(distList[idx]);
                    makeDistPermutation(newList, max);
                    distVisited[idx] = false;
                }
            }
        }
        else {
            for(int idx = 0; idx < distCount; idx++) {
                if(!distVisited[idx]) {
                    distVisited[idx] = true;
                    LinkedList<Integer> newList = new LinkedList<>(list);
                    newList.add(distList[idx]);
                    makeDistPermutation(newList, max);
                    distVisited[idx] = false;
                }
            }
        }
    }
}
