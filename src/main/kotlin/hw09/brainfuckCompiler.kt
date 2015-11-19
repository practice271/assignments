//package hw09
//
//import org.objectweb.asm.ClassWriter
//import org.objectweb.asm.MethodVisitor
//import org.objectweb.asm.Opcodes
//import java.nio.file.Files
//import java.nio.file.Paths
//import kotlin.properties.Delegates
//
//internal class brainfuckCompiler() {
//    //asm code (this is `init` function
//    var size = 10
//    var tape = Array(size, {0})
//    var tapeInd = size / 2
//
//    private fun enlargeArray() {
//        //asm code
//        val newAr = Array(size * 2, {0})
//        System.arraycopy(tape, 0, newAr, size / 2, size)
//        tapeInd += size / 2
//        size *= 2
//        tape = newAr
//    }
//
//    public fun compile(mw : MethodVisitor, input : String) {
//        //not asm code in 3 lines below
//        var i = 0
//        while (i < input.length) {
//            when (input[i]) {
//                '>' -> {
//                    //asm code here
//                    if (tapeInd == size - 1) enlargeArray()
//                    tapeInd++
//                }
//                '<' -> {
//                    if (tapeInd == 0) enlargeArray()
//                    tapeInd--
//                }
//                '+' -> tape[tapeInd]++
//                '-' -> tape[tapeInd]--
//                '.' -> print("${tape[tapeInd].toChar()} ")
//                ',' -> tape[tapeInd] = System.`in`.read()
//                '[' -> {
//                    var bracketCounter = 1
//                    val cycle = StringBuilder()
//                    i++
//                    while(true){
//                        if (input[i] == '[') bracketCounter++
//                        if (input[i] == ']') bracketCounter--
//                        if (bracketCounter == 0) {
//                            break
//                        }
//                        cycle.append(input[i])
//                        i++
//                    }
//                    while (tape[tapeInd] != 0) compile(cycle.toString())
//                }
//            }
//            i++
//        }
//    }
//
//    companion object {
//        val calcMethodName: String = "calc"
//        val className: String = "brainfuckCompiler"
//    }
//
//    private fun getClassWriter(): ClassWriter {
//        val cw = ClassWriter(0)
//        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null)
//
//        val mw = cw.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC, calcMethodName,
//                "(Ljava/lang/String;)V", null, null)
//     //   compile(mw)
//        mw.visitInsn(Opcodes.RETURN)//dubious
//        mw.visitMaxs(stackUsage(), 1)
//        mw.visitEnd()
//
//        val mainMethodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC, "main",
//                "([Ljava/lang/String;)V", null, null)
//        mainMethodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
//                "out", "Ljava/io/PrintStream;")
//        mainMethodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, className, calcMethodName, "()V")
////        mainMethodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
////                "java/io/PrintStream", "println",
////                "(I)V")
//        mainMethodVisitor.visitInsn(Opcodes.RETURN)
//        mainMethodVisitor.visitMaxs(2, 2)
//        mainMethodVisitor.visitEnd()
//        cw.visitEnd()
//
//        return cw
//    }
//
//    private fun stackUsage() : Int = 100
//
//    public fun generateClassByteArray(): ByteArray =
//            this.getClassWriter().toByteArray()
//}
//
//internal class ByteArrayClassLoader(): ClassLoader() {
//    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
//        return super.defineClass(name, buf, 0, buf.size)
//    }
//}
//
//public fun saveToDisk(classByteArray: ByteArray) {
//    val targetFile = Paths.get("${brainfuckCompiler.className}.class")
//    Files.write(targetFile, classByteArray)
//}
//
//public fun loadClassAndRun(classByteArray: ByteArray): Any? {
//    val cl = ByteArrayClassLoader()
//    val exprClass = cl.loadClass(brainfuckCompiler.className, classByteArray)
//    val methods = exprClass?.methods
//    if (methods == null || methods.isEmpty()) { throw Exception() }
//    for (method in methods) {
//        if (method.name != brainfuckCompiler.calcMethodName) { continue }
//        return method.invoke(null)
//    }
//    return null
//}
//
//public fun main(args : Array<String>) {
//    val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
//    val simple = "+++[>+<-]>."
//    val hw = "Hello World!"
//    val br_hw = asciiToBrainfuck().interprete(hw)
//    brainfuckInterpeter().interpete(br_hw)
//}