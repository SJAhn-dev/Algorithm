import java.util.HashMap;

class Solution {
    public String solution(int[] numbers, String hand) {
        HashMap<String, Keypad> map = new HashMap<>();

        int num = 1;
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                map.put(Integer.toString(num), new Keypad(x, y, Integer.toString(num)));
                num++;
            }
        }

        Keypad star = new Keypad(0, 3, "*");
        Keypad sharp = new Keypad(2, 3, "#");
        Keypad zero = new Keypad(1, 3, "0");
        map.put("*", star);
        map.put("#", sharp);
        map.put("0", zero);

        Keypad left = star;
        Keypad right = sharp;

        StringBuilder answer = new StringBuilder();
        for(int number : numbers) {
            String target = Integer.toString(number);
            Keypad keypad = map.get(target);

            int leftDist = Math.abs(keypad.x - left.x) + Math.abs(keypad.y - left.y);
            int rightDist = Math.abs(keypad.x - right.x) + Math.abs(keypad.y - right.y);

            if(number == 3 || number == 6 || number == 9) {
                right = keypad;
                answer.append("R");
            }
            else if(number == 1 || number == 4 || number == 7) {
                left = keypad;
                answer.append("L");
            }
            else if(leftDist < rightDist) {
                left = keypad;
                answer.append("L");
            }
            else if(leftDist > rightDist) {
                right = keypad;
                answer.append("R");
            }
            else if(hand.equals("left")) {
                left = keypad;
                answer.append("L");
            }
            else {
                right = keypad;
                answer.append("R");
            }
        }

        return answer.toString();
    }
    
    class Keypad {
        int x, y;
        String value;

        Keypad(int x, int y, String value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
