package hw09

import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.tree.*
import org.objectweb.asm.Opcodes.*
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by Alexander on 19.11.2015.
 */
class bfToJDK {
    val opcodes : List<Opcode> by Delegates.notNull<List<Opcode>>()
    val cn = ClassNode()
    val cw: ByteArray
        get() {
            cn.version = V1_7
            cn.access = ACC_PUBLIC
            cn.name = "ClassTest"
            cn.superName = "java/lang/Object"

            val mn = MethodNode(ACC_PUBLIC, "<init>", "()V", null, null)
            val il = mn.instructions
            il.add(VarInsnNode(ALOAD, 0))
            il.add(MethodInsnNode(INVOKESPECIAL, cn.superName, "<init>", "()V", false))
            il.add(InsnNode(RETURN))
            cn.methods.add(mn)

            val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
            cn.accept(cw)

            return cw.toByteArray()
        }

    fun toByteCode(className: String, memorySize: Int): ByteArray {

        //val mn = MethodNode(ACC_PUBLIC, "run", "()V", null, null)
        val il = InsnList()
        val lbls = Stack<LabelNode>()

        //объявляем массив размером memorySize
        il.add(LdcInsnNode(memorySize)) //заносим в стэк константу типа integer
        il.add(IntInsnNode(NEWARRAY, T_CHAR)) //создаем массив
        il.add(VarInsnNode(ASTORE, 1)) //кладем его в локальную переменную 1

        //объявляем позицию
        il.add(LdcInsnNode(memorySize / 2))
        il.add(VarInsnNode(ISTORE, 2)) //кладем его в локальную переменную 2

        //идем по массиву операций
        for (opcode in opcodes) {
            when (opcode.type) {
                Opcode.Type.SHL -> il.add(IincInsnNode(2, -1))
                Opcode.Type.SHR -> il.add(IincInsnNode(2, 1))
                Opcode.Type.ADD -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(DUP2))
                    il.add(InsnNode(CALOAD))
                    il.add(LdcInsnNode(1))
                    il.add(InsnNode(IADD))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.SUB -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(DUP2))
                    il.add(InsnNode(CALOAD))
                    il.add(LdcInsnNode(-1))
                    il.add(InsnNode(IADD))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.OUT -> {
                    il.add(VarInsnNode(ALOAD, 0))
                    il.add(FieldInsnNode(GETFIELD, cn.name, "out", "Ljava/io/PrintStream;"))
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false))
                }
                Opcode.Type.IN -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(VarInsnNode(ALOAD, 0))
                    il.add(FieldInsnNode(GETSTATIC, cn.name, "in", "Ljava/io/InputStream;"))
                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.WHILE -> {
                    //создаем сразу метки начала и конца
                    val begin = LabelNode()
                    val end = LabelNode()

                    //кладем их в стэк
                    lbls.push(end)
                    lbls.push(begin)

                    //указываем начало
                    il.add(begin)
                    //проверяем условие
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(JumpInsnNode(IFEQ, end)) //переходим в конец при условии, что значение равно нулю
                }
                Opcode.Type.END -> {
                    //переходим в начало
                    il.add(JumpInsnNode(GOTO, lbls.pop()))
                    //прописываем конец
                    il.add(lbls.pop())
                }
            }
        }

        // ......................
    }
}