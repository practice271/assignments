package hw09

/**
 * Created by Alexander on 18.11.2015.
 */

public  class asciiToBrainfuck {
    public fun translate(input : String) : String{
        if (input == "") return ""
        val res = StringBuilder()

        val nums = Array(input.length, {0})
        var sum = 0
        for (i in input.indices) {
            val cur = input[i].toInt()
            nums[i] = cur
            sum += cur
        }

        var len = input.length
        while (len > 0) {
            res.append('+')
            len--
        }

        val medium = sum / input.length
        var med : Int = medium
        res.append("[[>]")
        while (med > 0) {
            res.append('+')
            med--
        }
        res.append("[<]>-]")
        res.append(">")

        for (i in nums.indices) {
            var count = nums[i] - medium
            if (count >= 0) {
                while (count > 0) {
                    res.append('+')
                    count--
                }
                res.append(".>")
                continue
            }
            while (count < 0) {
                res.append('-')
                count++
            }
            res.append(".>")
        }
        return res.toString()
    }
}