package hw09

/**
 * Created by Alexander on 18.11.2015.
 */
//I've written this code in 3 minutes just in case no one bothers to write proper one.
internal class asciiToBrainfuck {
    public fun interprete(input : String) : String{
        val res = StringBuilder()
        for (c in input) {
            var count = c.toInt()
            while (count > 0) {
                res.append('+')
                count--
            }
            res.append(".>")
        }
        return res.toString()
    }
}