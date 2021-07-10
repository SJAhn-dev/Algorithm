import java.util.Queue
import java.util.LinkedList

class Solution {
    data class Place(val x: Int, val y: Int)
    data class Check(val x: Int, val y: Int, val count: Int, val prev: Int)
    val moveX = arrayOf(0, 0, 0, 1, -1)
    val moveY = arrayOf(0, 1, -1, 0, 0)
    
    fun solution(places: Array<Array<String>>): IntArray {
        var answer: IntArray = IntArray(5) { 0 }
        
        for(i in 0..4) {
            val people = findPeople(places[i])
            
            if(checkDistance(places[i], people)) {
                answer[i] = 1
            } else { answer[i] = 0}
        }
        
        return answer
    }
    
    fun findPeople(place: Array<String>): LinkedList<Place> {
        var people: LinkedList<Place> = LinkedList()
        
        for(y in place.indices) {
            for(x in place[y].indices) {
                if(place[y][x] == 'P') {
                    people.add(Place(x, y))
                }
            }
        }
        
        return people
    }
    
    fun checkDistance(room: Array<String>, people: LinkedList<Place>): Boolean {
        val queue:Queue<Check> = LinkedList()
        
        for(person in people) {
            queue.add(Check(person.x, person.y, 0, 0))
        }
        
        while(!queue.isEmpty()) {
            val check = queue.poll()
            if(check.prev != 0 && room[check.y][check.x] == 'P') {
                return false
            }
            
            if(check.count == 2) { continue }
            if(room[check.y][check.x] == 'X') { continue }
            
            for(i in 1..4) {
                if(check.prev == 1 && i == 2) { continue }
                else if(check.prev == 2 && i == 1) { continue }
                else if(check.prev == 3 && i == 4) { continue }
                else if(check.prev == 4 && i == 3) { continue }
                
                val dx = check.x + moveX[i]
                val dy = check.y + moveY[i]
                if(dx < 0 || dx >= 5 || dy < 0 || dy >= 5) { continue }
                
                queue.add(Check(dx, dy, check.count + 1, i))
            }
        }
        
        return true
    }
}
