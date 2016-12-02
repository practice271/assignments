package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*
import java.util.*

public object Petooh {

    public val MAX_MEM = 32767
    private val lbls = Stack<Label>()
    private var firstLoop = true

    private object Token {
        val INC = "Ko"
        val DEC = "kO"
        val SHIFTR = "Kudah"
        val SHIFTL = "kudah"
        val OUT = "Kukarek"
        val WHILE = "Kud"
        val END = "kud"
    }

    public fun compile(program: String, outFileName: String): ByteArray {
        var program = program
        program = prepare(program)
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, outFileName, null, "java/lang/Object", null)

        var mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        val lStart = Label()
        mv.visitLabel(lStart)
        mv.visitIntInsn(SIPUSH, MAX_MEM)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)

        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)

        firstLoop = true
        val chars = program.toCharArray()
        var instruction = StringBuilder()
        for (i in chars.indices) {
            val c = chars[i]
            instruction.append(c)
            val buf = instruction.toString()
            if ((Token.WHILE.equals(buf) || Token.END.equals(buf)) &&
                    (i != chars.size - 1 && chars[i+1] == 'a')) {
                continue
            }
            when (buf) {
                Token.INC -> {
                    mv.visitVarInsn(ALOAD, 1)
                    mv.visitVarInsn(ILOAD, 2)
                    mv.visitInsn(DUP2)
                    mv.visitInsn(IALOAD)
                    mv.visitInsn(ICONST_1)
                    mv.visitInsn(IADD)
                    mv.visitInsn(IASTORE)
                    instruction = StringBuilder()
                }
                Token.DEC -> {
                    mv.visitVarInsn(ALOAD, 1)
                    mv.visitVarInsn(ILOAD, 2)
                    mv.visitInsn(DUP2)
                    mv.visitInsn(IALOAD)
                    mv.visitInsn(ICONST_1)
                    mv.visitInsn(ISUB)
                    mv.visitInsn(IASTORE)
                    instruction = StringBuilder()
                }
                Token.SHIFTL-> {
                    mv.visitIincInsn(2, -1)
                    instruction = StringBuilder()

                }
                Token.SHIFTR -> {
                    mv.visitIincInsn(2, 1)
                    instruction = StringBuilder()

                }
                Token.OUT-> {
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                            "Ljava/io/PrintStream;")
                    mv.visitVarInsn(ALOAD, 1)
                    mv.visitVarInsn(ILOAD, 2)
                    mv.visitInsn(IALOAD)
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                            "print", "(C)V", false)
                    instruction = StringBuilder()

                }
                Token.WHILE -> {
                    val start = Label()
                    val end = Label()

                    lbls.push(end)
                    lbls.push(start)
                    mv.visitLabel(start)
                    if (firstLoop) {
                        mv.visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
                        firstLoop = false
                    } else {
                        mv.visitFrame(F_SAME, 0, null, 0, null)
                    }
                    mv.visitVarInsn(ALOAD, 1)
                    mv.visitVarInsn(ILOAD, 2)
                    mv.visitInsn(IALOAD);
                    mv.visitJumpInsn(IFEQ, end)
                    instruction = StringBuilder()

                }
                Token.END -> {
                    mv.visitJumpInsn(GOTO, lbls.pop())
                    mv.visitLabel(lbls.pop())
                    mv.visitFrame(F_SAME, 0, null, 0, null)
                    instruction = StringBuilder()
                }
            }
        }
        mv.visitInsn(RETURN)
        val lEnd = Label()
        mv.visitLabel(lEnd)
        mv.visitMaxs(1600, 1600)
        mv.visitEnd()
        cw.visitEnd()
        return cw.toByteArray()
    }

    private fun prepare(program: String): String {
        return program.replace("[^adehkKoOru]".toRegex(), "")
    }
}

/*
public fun main(args: Array<String>) {
    val program = "KoKoKoKoKoKoKoKoKoKo KudKudah"+
            "KoKoKoKoKoKoKoKo kudah kO kudKudah Kukarek kudah"+
            "KoKoKo KudKudah"+
            "kOkOkOkO kudah kO kudKudah Ko Kukarek kudah"+
            "KoKoKoKo KudKudah KoKoKoKo kudah kO kudKudah kO Kukarek"+
            "kOkOkOkOkO Kukarek Kukarek kOkOkOkOkOkOkO"+
            "Kukarek"
    val className = "TestClass"
    val classByteArray = Petooh.compile(program, className)
    val targetFile = Paths.get("$className.class")
    Files.write(
            targetFile,
            classByteArray,
            StandardOpenOption.WRITE,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
    )
}*/
