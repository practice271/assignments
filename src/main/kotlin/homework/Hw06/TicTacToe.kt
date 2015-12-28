package homework.Hw06

import java.util.*
/*
/ @Response is response of method @parseInput
*/
class Response(var Status:Int, var X:Int, var Y:Int){}

class Step(var mark:String){
    public fun nextStep():String{
        if (mark == "X") mark = "0"
        else mark = "X"
        return mark
    }
}

class Game(){
    var res = Response(1, 0, 0)
    val step = Step("0")
    var max = 0

    private var board = HashMap<Pair<Int,Int>, String> ()

    private fun tictac(x:Int,y:Int):String?{
        val coordinate = Pair(x,y)
        return board.get(coordinate)
    }

    public fun writeMark(coordinate : Pair<Int,Int>){
        board.put(coordinate, step.nextStep())
    }

    private fun getMax(){
        for (i in board){
            if (i.key.first  > max) max = i.key.first
            if (i.key.second > max) max = i.key.second
        }
    }

    private fun writeBoard(){
        getMax()
        if (max < 5) max = 5
        for (i in 1..max){
            for (j in 1..max){
                var mark = board?.get(Pair(i,j)) ?: "N"
                if (mark == "N")
                    print(" ")
                else
                    print(mark)
            }
            println()
        }
    }

    private fun parseInput(input:String){
        when{
            input == "board"  -> {
                res.Status = 1
            }
            input == "finish" -> res.Status = 3
            else -> {
                var xString = ""
                var yString = ""
                var char = '/'
                var counter = 0
                var counterBlank = 0
                for (char in input) {
                    if (char != ' ') {
                        when{
                            counterBlank == 0 -> xString += char
                            else -> yString += char
                        }
                    }
                    else counterBlank++
                }
                var X = Integer.parseInt(xString)
                var Y = Integer.parseInt(yString)
                res.Status = 2
                res.X = X
                res.Y = Y
            }
        }
    }

    public fun winCheck(c:Pair<Int,Int>):String{
        val x = c.first
        val y = c.second
        var counters = arrayOf(1,1,1,1,1,1,1,1)
        var mark = board?.get(c) ?: ""
        for (i in 1..4){
            if (board.get(Pair(x,y-i)) == mark) counters[0]++
            if (board.get(Pair(x,y+i)) == mark) counters[1]++
            if (board.get(Pair(x-i,y)) == mark) counters[2]++
            if (board.get(Pair(x+i,y)) == mark) counters[3]++
            if (board.get(Pair(x+i,y+i)) == mark) counters[4]++
            if (board.get(Pair(x+i,y-i)) == mark) counters[5]++
            if (board.get(Pair(x-i,y-i)) == mark) counters[6]++
            if (board.get(Pair(x-i,y+i)) == mark) counters[7]++
        }
        if (counters.contains(5)) return mark
        return "-"
    }
    public fun start(){
//        writeBoard()
        println("Instructions:")
        println("1) To put mark enter coordinates in format:x y")
        println("2) To see board with marks put 'board'")
        println("3) Game finished when someone gets 5 marks in a row")
        println("4) To finish game put 'finish'")
        var isActive = true
        var input    = ""
        var resp   = ""
        while (isActive){
            input = readLine().toString()
            parseInput(input)
            when (res.Status) {
                1 -> writeBoard()
                2 -> {
                    val c = Pair(res.X, res.Y)
                    //Don't rewrite mark
                    if (board.get(c) == null)
                        writeMark(c)
                    val result = winCheck(c)
                    if (result != "-"){
                        isActive = false
                        println("${result} is winner!")
                    }
                }
                3 -> isActive = false
            }
        }
    }
}

fun main(argv:Array<String>){
    val game = Game()

    game.writeMark(Pair(1,1))
    game.writeMark(Pair(2,5))
    game.writeMark(Pair(1,2))
    game.writeMark(Pair(2,9))
    game.writeMark(Pair(1,3))
    game.writeMark(Pair(2,10))
    game.writeMark(Pair(1,4))
    game.writeMark(Pair(2,11))
    game.writeMark(Pair(1,5))

    game.start()

//    val res = game.winCheck(Pair(2,10))
//    println(res)
}
