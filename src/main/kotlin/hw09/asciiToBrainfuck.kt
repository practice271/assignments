package hw09

/**
 * Created by Alexander on 18.11.2015.
 */

internal class asciiToBrainfuck {
    public fun interprete(input : String) : String{
        val res = StringBuilder()

        val num = Array(input.length, {0})
        var sum = 0
        for (i in input.indices) {
            val cur = input[i].toInt()
            num[i] = cur
            sum += cur
        }

        var len = input.length
        while (len > 0) {
            res.append('+')
            len--
        }
        //res.append('>')

        val medium = sum / input.length
        var med : Int = medium
        res.append("[[>]")
        while (med > 0) {
            res.append('+')
            med--
        }
        res.append("[<]>-]")
        res.append(">")

        for (i in input.indices) {
            var count = num[i] - medium
            if (count > 0) {
                while (count > 0) {
                    res.append('+')
                    count--
                }
                res.append(".>")
                continue
            }
            if (count < 0) {
                while (count < 0) {
                    res.append('-')
                    count++
                }
                res.append(".>")
            }
        }
        val a = res.length
        return res.toString()
    }
}