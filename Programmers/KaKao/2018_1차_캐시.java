import java.util.ArrayList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> cache = new ArrayList<>();
        if(cacheSize == 0) { return cities.length * 5; }
        
        for(String city : cities) {
            city = city.toLowerCase();
            if(cache.contains(city)) {
                answer++;
                cache.remove( cache.indexOf(city) );
                cache.add(0, city);
            }
            else {
                if(cache.size() >= cacheSize) {
                    cache.remove(cacheSize - 1);
                    cache.add(0, city);
                    answer += 5;
                }
                else {
                    cache.add(0, city);
                    answer += 5;
                }
            }
        }
        
        return answer;
    }
}
