import java.util.PriorityQueue;

class Solution {
    public int solution(int[] food_times, long k) {
        long cycleSize = food_times.length;
        long tempValue = k + 1;
        
        PriorityQueue<Food> queue = new PriorityQueue<>();
        for(int idx = 0; idx < cycleSize; idx++) {
            queue.add(new Food(idx, food_times[idx]));
        }
        
        Food minFood = queue.poll();
        long minValue = minFood.value;
        long saveValue = 0;
        long compare = cycleSize * (minValue - saveValue);
        while(tempValue > compare) {
            if(minValue - saveValue != 0) {
                tempValue -= compare;
                saveValue += (minValue - saveValue);
            }
            food_times[minFood.index] = 0;
            cycleSize--;
            
            if(!queue.isEmpty()) {
                minFood = queue.poll();
                minValue = minFood.value;
                compare = cycleSize * (minValue - saveValue);
            } else { return -1; }
        }
        
        while(tempValue > cycleSize) {
            tempValue -= cycleSize;
        }
        
        for(int idx = 0; idx < food_times.length; idx++) {
            if(food_times[idx] != 0) {
                tempValue--;
            }
            if(tempValue == 0) {
                return idx + 1;
            }
        }
        
        return -1;
    }
    
    class Food implements Comparable<Food>{
        int index, value;
        
        public Food(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        @Override
        public int compareTo(Food obj) {
            return this.value > obj.value ? 1 : -1;
        }
    }
}
