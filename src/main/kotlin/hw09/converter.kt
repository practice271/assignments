/* A converter from an ASCII text to a short Brainfuck program, which prints this text
(expectation: 5 h; reality: 5 h)
by Sokolova Polina */

package hw09

public class Converter(val s : String) {
    val length = s.length
    var partNumber  = Array(length, { arrayOf(0, 0, 0) })
    private fun parse(m1 : Int, m2 : Int) {
        for (i in 0..length - 1) {
            var num = s[i].toInt()
            var amountM1 = num / m1
            var modM1 = num mod m1
            var amountM2  =  modM1 / m2
            var rem = modM1 mod m2
            partNumber[i][2] = rem
            partNumber[i][1] = amountM2
            partNumber[i][0] = amountM1
        }
    }
    private fun makeLoop(start : String, j : Int) : String {
        var res  = start + "["
        var p = "+"
        for (i in 0..length - 1) {
            res += ">" + p.repeat(partNumber[i][j])
        }
        for (i in 0..length - 1) {
            res += "<"
        }
        res += "-]"
        return res
    }
    private fun remainder() : String {
        var res = ""
        var col = 2
        var p = "+"
        for (i in 0..length - 1) {
            res += ">" + p.repeat(partNumber[i][col]) + "."
        }
        return res
    }
    public fun convert() : String {
        var minProgram = noLoop()
        var minLength  = minProgram.length
        var p = "+"
        for (i in 2..64) {
            for (j in 1..i - 1) {
                parse(i, j)
                var p1 = p.repeat(i)
                var p2 = p.repeat(j)
                var res = makeLoop(p1, 0) + makeLoop(p2, 1) + remainder()
                if (res.length < minLength) {
                    minLength  = res.length
                    minProgram = res
                }
            }
        }
        return minProgram
    }
    private fun noLoop() : String {
        var res = ""
        var p = "+"
        for (i in 0..length - 1) {
            res += p.repeat(s[i].toInt()) + ".>"
        }
        return res
    }
}