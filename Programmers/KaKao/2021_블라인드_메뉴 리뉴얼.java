import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    static HashMap<Integer, HashMap> combineMap;
    
    public String[] solution(String[] orders, int[] course) {
        combineMap = new HashMap<>();
        for(int idx = 2; idx <= 10; idx++) {
            combineMap.put(idx, new HashMap<String, Integer>());
        }

        for(String order : orders) {
            parseOrder(order);
        }

        ArrayList<String> list = new ArrayList<>();
        for(int size : course) {
            HashMap<String, Integer> map = combineMap.get(size);
            PriorityQueue<CourseSet> queue = new PriorityQueue<>();
            map.forEach((k, v) -> queue.add(new CourseSet(k, v)));

            int prev = Integer.MIN_VALUE;
            while (!queue.isEmpty()) {
                CourseSet temp = queue.poll();
                if(temp.count < prev || temp.count < 2) { break; }
                list.add(temp.combine);
                prev = temp.count;
            }
        }
        Collections.sort(list);

        return list.toArray(new String[0]);
    }
    
    public void parseOrder(String order) {
        int orderLength = order.length();
        char[] charArray = order.toCharArray();
        Arrays.sort(charArray);

        for(int idx = 2; idx <= orderLength; idx++) {
            for(int j = 0; (1 << orderLength) > j; ++j) {
                if(Integer.bitCount(j) == idx) {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < orderLength; ++i) {
                        if(((1 << i) & j) > 0) {
                            sb.append(charArray[i]);
                        }
                    }


                    HashMap<String, Integer> map = combineMap.get(idx);
                    if(map.containsKey(sb.toString())) {
                        int temp = map.get(sb.toString());
                        map.put(sb.toString(), temp + 1);
                    }
                    else {
                        map.put(sb.toString(), 1);
                    }
                }
            }
        }
    }
    
    class CourseSet implements Comparable<CourseSet>{
        String combine;
        int count;
        CourseSet(String combine, int count) {
            this.combine = combine;
            this.count = count;
        }

        @Override
        public int compareTo(CourseSet o) {
            return this.count >= o.count ? -1 : 1;
        }
    }
}
