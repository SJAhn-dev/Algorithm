import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<Filename> list = new ArrayList<>();
        
        for(int fileIdx = 0; fileIdx < files.length; fileIdx++) {
            String file = files[fileIdx];
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            boolean isHead = true;
            boolean isNum = false;
            int idx = 0;
            int fileLength = file.length();
            while(idx < fileLength) {
                char tempChar = file.charAt(idx);
                if(isHead) {
                    if(Character.isDigit(tempChar)) {
                        isHead = false;
                        isNum = true;
                        number.append(tempChar);
                    }
                    else {
                        head.append(tempChar);
                    }
                }
                else if(isNum) {
                    if(!Character.isDigit(tempChar)) {
                        isNum = false;
                        list.add(new Filename(head.toString(), number.toString(),
                                             file.substring(idx, fileLength), fileIdx));
                        break;
                    }
                    else {
                        number.append(tempChar);
                    }
                }
                
                if(idx == fileLength - 1) {
                    list.add(new Filename(head.toString(), number.toString(),
                                         null, fileIdx));
                    break;
                }
                idx++;
            }
        }
        Collections.sort(list);
        
        int idx = 0;
        for(Filename fn : list) {
            answer[idx] = fn.toString();
            idx++;
        }
        
        return answer;
    }
    
    class Filename implements Comparable<Filename>{
        String head;
        String number;
        String tail;
        int inputOrder;
        
        public Filename(String head, String number, String tail, int inputOrder) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.inputOrder = inputOrder;
        }
        
        @Override
        public int compareTo(Filename obj) {
            String headLow = this.head.toLowerCase();
            String objheadLow = obj.head.toLowerCase();
            if(headLow.equals(objheadLow)) {
                Integer numInt = Integer.parseInt(this.number);
                Integer objnumInt = Integer.parseInt(obj.number);
                if(numInt == objnumInt) {
                    Integer order = this.inputOrder;
                    Integer objorder = obj.inputOrder;
                    return order.compareTo(objorder);
                }
                else {
                    return numInt.compareTo(objnumInt);
                }
            }
            else {
                return headLow.compareTo(objheadLow);
            }
        }
        
        @Override
        public String toString() {
            if(tail == null) {
                return head + number;
            }
            return head + number + tail;
        }
    }
}
