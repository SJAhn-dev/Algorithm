class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[][] keypad = new int[12][12];
        int tempLeft = 10;
        int tempRight = 11;
        keypad[0] = new int[]{0, 4, 3, 4, 3, 2, 3, 2, 1, 2, 1, 1};
        keypad[1] = new int[]{4, 0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 5};
        keypad[2] = new int[]{3, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 4};
        keypad[3] = new int[]{4, 2, 1, 0, 3, 2, 1, 4, 3, 2, 5, 3};
        keypad[4] = new int[]{3, 1, 2, 3, 0, 1, 2, 1, 2, 3, 2, 4};
        keypad[5] = new int[]{2, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 3};
        keypad[6] = new int[]{3, 3, 2, 1, 2, 1, 0, 3, 2, 1, 4, 2};
        keypad[7] = new int[]{2, 2, 3, 4, 1, 2, 3, 0, 1, 2, 1, 3};
        keypad[8] = new int[]{1, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 2};
        keypad[9] = new int[]{2, 4, 3, 2, 3, 2, 1, 2, 1, 0, 3, 1};
        keypad[10] = new int[]{1, 3, 4, 5, 2, 3, 4, 1, 2, 3, 0, 2};
        keypad[11] = new int[]{1, 5, 4, 3, 4, 3, 2, 3, 2, 1, 2, 0};

        for(int number : numbers) {
            if(number == 1 || number == 4 || number == 7) {
                answer.append("L");
                tempLeft = number;
            }
            else if(number == 3 || number == 6 || number == 9) {
                answer.append("R");
                tempRight = number;
            }
            else {
                if(keypad[number][tempLeft] < keypad[number][tempRight]) {
                    answer.append("L");
                    tempLeft = number;
                }
                else if(keypad[number][tempLeft] > keypad[number][tempRight]) {
                    answer.append("R");
                    tempRight = number;
                }
                else if(hand.equals("left")) {
                    answer.append("L");
                    tempLeft = number;
                }
                else if(hand.equals("right")) {
                    answer.append("R");
                    tempRight = number;
                }
            }
        }


        return answer.toString();
    }
}
