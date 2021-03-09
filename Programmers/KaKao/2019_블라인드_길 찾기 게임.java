import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static Queue<Integer> preQueue = new LinkedList<>();
    static Queue<Integer> postQueue = new LinkedList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        Node root;
        int dataSize = nodeinfo.length;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        for(int idx = 0; idx < dataSize; idx++) {
            queue.add(new Node(idx + 1, nodeinfo[idx][0], nodeinfo[idx][1]));
        }
        root = queue.poll();
        
        while(!queue.isEmpty()) {
            Node child = queue.poll();
            Node temp = root;
            while(true) {
                if(child.x < temp.x) {
                    if(temp.left == null) {
                        temp.left = child;
                        break;
                    } else {
                        temp = temp.left;
                    }
                }
                else {
                    if(temp.right == null) {
                        temp.right = child;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
        
        postorder(root);
        preorder(root);
        
        int[][] answer = new int[2][dataSize];
        
        for(int idx = 0; idx < dataSize; idx++) {
            answer[0][idx] = preQueue.poll();
            answer[1][idx] = postQueue.poll();
        }
        
        return answer;
    }
    
    public void preorder(Node temp) {
        preQueue.add(temp.num);
        if(temp.left != null) { preorder(temp.left); }
        if(temp.right != null) { preorder(temp.right); }
    }
    
    public void postorder(Node temp) {
        if(temp.left != null) { postorder(temp.left); }
        if(temp.right != null) { postorder(temp.right); }
        postQueue.add(temp.num);
    }
    
    public class Node implements Comparable<Node>{
        int x, y, num;
        Node left, right;
        
        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }
        
        @Override
        public int compareTo(Node obj) {
            if(this.y == obj.y) {
                return this.x > obj.x ? 1 : -1;
            }
            return this.y > obj.y ? -1 : 1;
        }
    }
}
