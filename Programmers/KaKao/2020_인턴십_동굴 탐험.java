import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public boolean solution(int n, int[][] paths, int[][] orders) {
        boolean answer = true;

        // Room 초기화
        HashMap<Integer, Room> map = new HashMap<>();
        for(int idx = 0; idx < n; idx++) {
            map.put(idx, new Room(idx, null));
        }

        // Room 연결관계
        for(int[] path : paths) {
            Room room1 = map.get(path[0]);
            Room room2 = map.get(path[1]);
            room1.childlist.add(room2);
            room2.childlist.add(room1);
        }

        // Room 부모자식관계 연결
        Queue<Room> queue = new LinkedList<>();
        queue.add(map.get(0));
        queue.peek().depth = 0;
        while(!queue.isEmpty()) {
            Room temp = queue.poll();
            int childCnt = temp.childlist.size();

            for(int idx = 0; idx < childCnt; idx++) {
                Room child = temp.childlist.get(idx);
                child.childlist.remove(temp);
                child.depth = temp.depth + 1;
                queue.add(child);
            }
        }

        // Closed Room 입력 (key Index - Closed Room)
        HashMap<Integer, ArrayList<Room>> keyCloseMap = new HashMap<>();
        for(int[] order : orders) {
            Room closed = map.get(order[1]);
            closed.roomClosed = true;
            if(!keyCloseMap.containsKey(order[0])) {
                ArrayList<Room> arrayList = new ArrayList<>();
                arrayList.add(closed);
                keyCloseMap.put(order[0], arrayList);
            }
            else {
                keyCloseMap.get(order[0]).add(closed);
            }
        }

        // Room 탐색
        Stack<Room> stack = new Stack<>();
        ArrayList<Room> closed = new ArrayList<>();
        stack.add(map.get(0));
        int cycleCnt = 0;
        while(!stack.isEmpty()) {
            Room temp = stack.pop();
            if(temp.roomClosed) { closed.add(temp); continue; }

            // key로 해당되는 Room Closed 해제
            if(keyCloseMap.containsKey(temp.roomNum)) {
                ArrayList<Room> list = keyCloseMap.get(temp.roomNum);
                for (Room room : list) {
                    room.roomClosed = false;
                    if(closed.contains(room)) {
                        stack.add(room);
                        closed.remove(room);
                    }
                }
            }

            int childCnt = temp.childlist.size();
            for(int idx = 0; idx < childCnt; idx++) {
                stack.add(temp.childlist.get(idx));
            }
        }

        return closed.isEmpty();
    }
    
    class Room {
        int roomNum;
        int depth;
        boolean roomClosed;
        ArrayList<Room> childlist;

        Room(int roomNum, Room parent) {
            this.roomNum = roomNum;
            this.depth = -1;
            this.roomClosed = false;
            this.childlist = new ArrayList<>();
        }
    }
}
