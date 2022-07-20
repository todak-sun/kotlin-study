package me.todak.study

class TwoSomeSolution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for ((i, num) in nums.withIndex()) {
            val needValue = target - num
            println("needValue: $needValue")
            val foundedIndex = nums.indexOf(needValue)
            if (foundedIndex != -1 && foundedIndex != i) {
                return intArrayOf(i, foundedIndex)
            }
        }
        throw Exception()
    }
}


fun main(args: Array<String>) {
    val ret = TwoSomeSolution().twoSum(intArrayOf(2, 7, 11, 15), 9)
}

