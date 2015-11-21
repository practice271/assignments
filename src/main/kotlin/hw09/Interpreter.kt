package hw09

import java.io.BufferedReader
import java.io.FileReader

class Interpreter (private val fileName : String)
{
    var arrSize = 30000
    var arr : Array<Char> = resizeArray()
    var ind = 0
    val textProgram : String = readTextProgram()
    var output : String = ""

    val nullChar = '0' - 48

    fun resizeArray() : Array<Char>
    {
        val ch : Char = nullChar

        arr = Array(arrSize, {i -> ch})

        return arr
    }

    private fun readTextProgram() : String
    {
        val buffer : BufferedReader = BufferedReader(FileReader(fileName))

        var text : String? = buffer.readLine()
        var prog : String = ""

        while (text != null)
        {
            prog += text
            text = buffer.readLine()
        }

        return prog
    }

    fun run (tour : Boolean = false, tourText : String = "")
    {
        var someTourText = ""
        var writeTourText : Boolean = false

        var endsCount = -1

        for (ch in (if (!tour) textProgram else tourText))
        {
            when (ch)
            {
                '>' ->
                    {
                        if (writeTourText) someTourText += '>'
                        ind++
                        if (ind == arrSize) ind = 0
                    }
                '<' ->
                    {
                        if (writeTourText) someTourText += '<'
                        ind--
                        if (ind == -1) ind = arrSize - 1
                    }
                '+' ->
                    {
                        if (writeTourText) someTourText += '+'
                        arr[ind]++
                    }
                '-' ->
                    {
                        if (writeTourText) someTourText += '-'
                        arr[ind]--
                    }
                '.' ->
                    {
                        if (writeTourText) someTourText += '.'
                        output += arr[ind]
                    }
                ',' ->
                    {
                        if (writeTourText) someTourText += ','
                        arr[ind] = System.console().readLine()[0]
                    }
                '[' ->
                    {
                        if (endsCount == -1)
                        {
                            writeTourText = true
                            endsCount = 0
                        }
                        else
                        {
                            endsCount++
                            someTourText += '['
                        }
                    }
                ']' ->
                    {
                        if (endsCount > 0)
                        {
                            endsCount--
                            someTourText += ']'
                        }
                        else
                        {
                            while (arr[ind] != nullChar) run(true, someTourText)
                            endsCount = -1
                            someTourText = ""
                            writeTourText = false
                        }
                    }
            }
        }
    }

    fun start()
    {
        run()

        println(output)
    }
}

fun main(args : Array<String>)
{
    val intr = Interpreter("G:\\GitHub\\assignments\\src\\main\\kotlin\\hw09\\helloWorld.bf")

    intr.start()
}
