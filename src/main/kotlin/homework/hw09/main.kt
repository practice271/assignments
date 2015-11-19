package homework.hw09

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main(args : Array<String>) {
    val program = "+++[-]."
    //val program = "+++[>++[>++++<]<]."
    val className = "TestClass"
    val classByteArray = BrainfuckCompiler.generateClassByteArray(program, className)
    val targetFile = Paths.get("$className.class")
    Files.write(
            targetFile,
            classByteArray,
            StandardOpenOption.WRITE,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
    )
}