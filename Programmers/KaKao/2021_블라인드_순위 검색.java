import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static HashMap<String, ArrayList> crewMap = new HashMap<>();

    public int[] solution(String[] infos, String[] queries) {
        int querySize = queries.length;
        int[] answer = new int[querySize];
        crewMap = new HashMap<>();

        for(String info : infos) {
            String[] parse = info.split(" ");
            inputValue(parse, 0, "");
        }

        crewMap.forEach((key, list) -> Collections.sort(list));

        for(int idx = 0; idx < querySize; idx++) {
            String query = queries[idx];
            query = query.replaceAll("and ", "");
            String[] parse = query.split(" ");
            String key = parse[0] + parse[1] + parse[2] + parse[3];

            if(!crewMap.containsKey(key)) {
                answer[idx] = 0;
                continue;
            }

            ArrayList<Integer> list = crewMap.get(key);
            int score = Integer.parseInt(parse[4]);

            int left = 0, right = list.size() - 1, mid;
            while (left <= right) {
                mid = (left + right) / 2;
                if (list.get(mid) < score) 
                    left = mid + 1;
                else 
                    right = mid - 1;
            }

            answer[idx] = list.size() - left;
        }

        return answer;
    }
    
    public void inputValue(String[] info, int index, String input) {
        if(index == 4) {
            if(crewMap.containsKey(input)) {
                crewMap.get(input).add(Integer.parseInt(info[4]));
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                crewMap.put(input, list);
            }
            return;
        }

        inputValue(info, index + 1, input + "-");
        inputValue(info, index + 1, input + info[index]);
    }
}
