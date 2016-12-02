/*
 * Homework 9 (03.11.2015)
 * Tests for PETOOH compiler.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class PetoohCompilerTest {
    private val compiler = PetoohCompiler()

    /** Prints "Hello World!". */
    @Test fun test01() {
        val program = "KoKoKoKoKoKoKoKo Kud-Kudah KoKoKoKo Kud-Kudah KoKo Kudah KoKoKo " +
                "Kudah KoKoKo Kudah Ko kudah kudah kudah kudah kO kud-Kudah Ko Kudah Ko " +
                "Kudah kO Kudah Kudah Ko Kud-kudah kud-kudah kO kud Ko Kudah Kudah Kukarek " +
                "Kudah kO kO kO Kukarek KoKoKoKoKoKoKo Kukarek Kukarek KoKoKo Kukarek Kudah " +
                "Kudah Kukarek kudah kO Kukarek kudah Kukarek KoKoKo Kukarek kOkOkOkOkOkO " +
                "Kukarek kOkOkOkOkOkOkOkO Kukarek Kudah Kudah Ko Kukarek Kudah KoKo Kukarek"
        test(program, "Hello World!\n")
    }

    /** Prints "PETOOH". */
    @Test fun test02() {
        val program = "KoKoKoKoKoKoKoKoKoKo Kud-Kudah KoKoKoKoKoKoKoKo kudah kO kud-Kudah " +
                "Kukarek kudah KoKoKo Kud-Kudah kOkOkOkO kudah kO kud-Kudah Ko Kukarek kudah " +
                "KoKoKoKo Kud-Kudah KoKoKoKo kudah kO kud-Kudah kO Kukarek kOkOkOkOkO Kukarek " +
                "Kukarek kOkOkOkOkOkOkO Kukarek"
        test(program, "PETOOH")
    }

    /** Prints 13 first numbers of Fibonacci sequence. */
    @Test fun test03() {
        val program = "KoKoKoKoKoKoKoKoKoKoKo Kudah Ko Kukarek Kudah Ko Kukarek kudah kudah " +
                "Kud-Kudah Kud kO Kudah Kudah Ko kudah kudah kud-Kudah Kud kO kudah Ko Kudah " +
                "Kudah Ko kudah kud-Kudah Kud kO kudah Ko Kudah kud-kudah Kukarek kudah kudah kO kud"
        test(program, arrayOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233))
    }

    /**
     * Gets a [program] on PETOOH and expected [result].
     * Compiles this [program] and checks that output is equal to [result].
     */
    private fun test(program : String, result : Any) {
        val byteArray = compiler.compile(program)
        ClassRunner().loadClassAndRun(byteArray, "PetoohCompiler")

        val output = File("programOutput.out").readText()
        var temp   = ""
        var res    = result.toString()

        when(result) {
            is String   -> temp = output
            is Array<*> -> {
                for (c in output) temp += c.toInt().toString() + ' '
                res = ""
                for (i in result) res += i.toString() + ' '
            }
        }
        assertEquals(res, temp)
    }
}