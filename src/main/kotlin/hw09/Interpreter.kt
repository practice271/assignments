package hw09

import java.io.BufferedReader
import java.io.FileReader

class Interpreter (private val fileName : String)
{
    private val arrSize = 30000
    private var arr : Array<Char> = Array(arrSize, {i -> nullChar})
    private var ind = 0
    private val textProgram : String = readTextProgram()
    private var output : String = ""

    private val nullChar = '0' - 48


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

    private fun run (tour : Boolean = false, tourText : String = "")
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
                        }
                        else
                        {
                            someTourText += '['
                        }
                        endsCount++
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
