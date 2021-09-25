class Solution {
    val voca = mutableListOf<String>()
    val alphabets = arrayOf("A", "E", "I", "O", "U")

    fun solution(word: String): Int {
        makeWord("")

        return voca.indexOf(word)
    }

    fun makeWord(word: String) {
        if (!voca.contains(word)) {
            voca.add(word)
        }
        if (word.length == 5) {
            return
        }
        alphabets.forEach {
            makeWord(word + it)
        }
    }
}
