fun solution(k: Long, room_number: LongArray): LongArray {
        var answer = LongArray(room_number.size)

        val map:HashMap<Long, Long> = HashMap()

        for(index in room_number.indices) {
            val wishRoom = room_number[index]

            if(!map.containsKey(wishRoom)) {
                map[wishRoom] = wishRoom
                answer[index] = wishRoom
            }
            else {
                val stack: Stack<Long> = Stack()
                var temp = map[wishRoom]!!
                stack.add(wishRoom)

                while(true) {
                    if(map.containsKey(temp)) {
                        if(map[temp]!! == temp) {
                            stack.add(temp)
                            temp++
                        }
                        else {
                            stack.add(temp)
                            temp = map[temp]!!
                        }
                    }
                    else {
                        map[temp] = temp
                        answer[index] = temp
                        break
                    }
                }

                while(!stack.isEmpty()) {
                    map[stack.pop()] = temp
                }
            }
        }

        return answer
    }
    
    // 프로그래머스 모범답안 - 함수형
    val rooms = hashMapOf<Long, Long>()
  
    fun solution_functional(k: Long, room_number: LongArray) = LongArray(room_number.size).apply {
        room_number.forEachIndexed { idx, num -> set(idx, insert(num)) }
    }

    fun insert(num: Long): Long {
        var picked = num
        val children = arrayListOf<Long>()
        while (rooms.containsKey(picked)) {
            children.add(picked)
            picked = rooms[picked]!!
        }
        children.apply { add(picked) }.forEach { rooms[it] = rooms[picked + 1] ?: (picked + 1) }
        return picked
    }
