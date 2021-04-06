import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    static int opCnt;
    static String[] numArr;
    static String opStr;
    
    public long solution(String expression) {
        long answer = 0;

        numArr = expression.split("\\*|\\+|\\-");
        opStr = expression.replaceAll("[0-9]", "");

        opCnt = opStr.length();

        // * > + > -
        Comparator<Num> cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='*')
                    return -1;
                else if(o2.operator == '*')
                    return 1;
                else if(o2.operator == '-')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        // * > - > +
        cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='*')
                    return -1;
                else if(o2.operator == '*')
                    return 1;
                else if(o2.operator == '+')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        // + > * > -
        cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='+')
                    return -1;
                else if(o2.operator == '+')
                    return 1;
                else if(o2.operator == '-')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        // + > - > *
        cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='+')
                    return -1;
                else if(o2.operator == '+')
                    return 1;
                else if(o2.operator == '*')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        // - > * > +
        cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='-')
                    return -1;
                else if(o2.operator == '-')
                    return 1;
                else if(o2.operator == '+')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        // - > + > *
        cmp = new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                if(o1.operator == o2.operator)
                    return o1.index > o2.index ? 1 : -1;
                else if(o1.operator =='-')
                    return -1;
                else if(o2.operator == '-')
                    return 1;
                else if(o2.operator == '*')
                    return -1;
                else
                    return 1;
            }
        };
        answer = Math.max(answer, calc(cmp));

        return answer;
    }
    
    public long calc(Comparator<Num> cmp) {
        PriorityQueue<Num> queue = new PriorityQueue<>(cmp);
        Num head = new Num(' ', 0, Integer.parseInt(numArr[0]), null);
        Num prev = head;
        
        for(int idx = 0; idx < opCnt; idx++) {
            Num temp = new Num(opStr.charAt(idx), idx, Integer.parseInt(numArr[idx + 1]), prev);
            queue.add(temp);
            prev = temp;
        }
        
        while(!queue.isEmpty()) {
            Num temp = queue.poll();
            Num front = temp.front;

            if(temp.operator == '*') {
                temp.number = front.number * temp.number;
            }
            else if(temp.operator == '+') {
                temp.number = front.number + temp.number;
            }
            else if(temp.operator == '-') {
                temp.number = front.number - temp.number;
            }
            temp.operator = front.operator;
            temp.index = front.index;
            temp.front = front.front;

            if(front.front == null) { head = temp; }
            else { queue.remove(front); queue.add(temp); }
        }

        return Math.abs(head.number);
    }
    
    class Num{
        char operator;
        int index;
        long number;
        Num front;
        
        Num(char operator, int index, long number, Num front) {
            this.operator = operator;
            this.index = index;
            this.number = number;
            this.front = front;
        }
    }
}
