package hw6

import javafx.application.Application

fun main(args: Array<String>) {
    if (args.size > 0 && args[0] == "-g")
        Application.launch(UI::class.java)
    else {
        val t = Term()
        t.run()
    }
}