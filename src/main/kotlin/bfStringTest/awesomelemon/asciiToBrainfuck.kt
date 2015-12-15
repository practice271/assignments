package bfStringTest.awesomelemon

/**
 * Created by Alexander on 18.11.2015.
 */

public  class AsciiToBrainfuck {
    public fun translateUpTo255symbols(input : String, magickalNumber : Int? = 105) : String{
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

        val mean = magickalNumber ?: sum / input.length
        var m : Int = mean
        res.append("[[>]")
        while (m > 0) {
            res.append('+')
            m--
        }
        res.append("[<]>-]")
        res.append(">")

        for (i in nums.indices) {
            var count = nums[i] - mean
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
        res.append(">>>>>")
        return res.toString()
    }

    //will work slighly better on English words and
    //significntly better on non-English gibberish
    public fun translateChooseBest(input : String) : Pair<String, Int> {
        var res = ""
        var minLen = Int.MAX_VALUE
        var bestI = -1
        for (i in 1..255) {
            val curRes = translateUpTo255symbols(input, i)
            if (curRes.length < minLen) {
                minLen = curRes.length
                bestI = i
                res = curRes
            }
        }
        return Pair(res, bestI)
    }

    public fun translate(input : String) : String {
        if (input.length <= 255) return translateChooseBest(input).first
        val sb = StringBuilder(input)
        val res = StringBuilder()
        val len = input.length
        for (i in 0 .. len / 255) {
            val curRes = translateChooseBest(sb[i * 255, Math.min((i + 1) * 255, len)].toString())
            res.append(curRes.first)
        }
        return res.toString()
    }
}