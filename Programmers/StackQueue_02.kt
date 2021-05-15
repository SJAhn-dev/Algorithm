import java.util.PriorityQueue
import java.util.Queue
import java.util.LinkedList

class Solution {
    data class Document(var value: Int, var index: Int)
    
    fun solution(priorities: IntArray, location: Int): Int {
        var target : Document = Document(-1, -1)
        var pqueue : PriorityQueue<Document> = PriorityQueue(Comparator<Document> {
            a, b -> when {
                a.value == b.value -> b.index.compareTo(a.index)
                else -> b.value.compareTo(a.value)
            }
        })

        var queue : Queue<Document> = LinkedList()

        for(idx in priorities.indices) {
            var doc = Document(priorities[idx], idx)
            pqueue.add(doc)
            queue.add(doc)

            if(idx == location) {
                target = doc
            }
        }

        var list : LinkedList<Document> = LinkedList();

        while(!queue.isEmpty()) {
            var doc : Document = queue.poll()

            if(pqueue.peek().value == doc.value) {
                list.add(doc)
                pqueue.poll()
            }
            else {
                queue.add(doc)
            }
        }

        return list.indexOf(target) + 1;
    }
}
