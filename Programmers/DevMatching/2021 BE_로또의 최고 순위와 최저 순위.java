import java.util.ArrayList;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int match = 0;
        int zero = 0;

        ArrayList<Integer> lottoList = new ArrayList<>();
        ArrayList<Integer> winList = new ArrayList<>();

        for(int idx = 0; idx < 6; idx++) {
            lottoList.add(lottos[idx]);
            winList.add(win_nums[idx]);
        }

        for(int idx = 0; idx < 6; idx++) {
            int temp = lottoList.get(idx);

            if(winList.contains(temp)) {
                match++;
            }
            else if(temp == 0) {
                zero++;
            }
        }

        answer[0] = match == 0 && zero == 0 ? 6 : 7 - (match + zero);
        answer[1] = match == 0 ? 6 : 7 - match;

        return answer;
    }
}
