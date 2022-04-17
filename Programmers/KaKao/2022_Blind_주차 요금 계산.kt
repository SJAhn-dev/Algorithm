class Solution {
    private var defaultTime = 0
    private var defaultFee = 0
    private var unitTime = 0
    private var unitFee = 0
    
    data class Car(
        val number: Int,
        var startTime: Int?,
        var timeAccumulate: Int
    )
    
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var carMap = mutableMapOf<Int, Car>()
        defaultTime = fees[0]
        defaultFee = fees[1]
        unitTime = fees[2]
        unitFee = fees[3]
        
        records.forEach { record ->
            val time = record.split(" ")[0].toMinute()
            val carNum = record.split(" ")[1].toInt()
            val command = record.split(" ")[2]
            
            when (command) {
                "IN" -> {
                    if (carMap.containsKey(carNum)) {
                        carMap[carNum]!!.startTime = time
                    } else {
                        carMap[carNum] = Car(carNum, time, 0)
                    }
                }
                "OUT" -> {
                    val car = carMap[carNum]!!
                    car.timeAccumulate += time - car.startTime!!
                    car.startTime = null
                }
                else -> return@forEach
            }
        }
        
        carMap = carMap.toSortedMap()
        var answer = IntArray(carMap.size)
        var index = 0
        
        carMap.forEach { carNum, car ->
            if (car.startTime != null) {
                car.timeAccumulate += "23:59".toMinute() - car.startTime!!
            }
            println(car.timeAccumulate)
            answer[index] = calculateFee(car.timeAccumulate)
            index++
        }
        
        return answer
    }
    
    private fun calculateFee(time: Int): Int {
        if(time <= defaultTime) {
            return defaultFee
        }
        
        var fee = defaultFee
        var calculateTime = time - defaultTime
        
        fee += (calculateTime / unitTime) * unitFee
        if (calculateTime % unitTime > 0) {
            fee += unitFee
        }
        
        return fee
    }
    
    private fun String.toMinute(): Int {
        val hour = this.split(":")[0]
        val min = this.split(":")[1]

        return (hour.toInt() * 60) + min.toInt()
    }
}
