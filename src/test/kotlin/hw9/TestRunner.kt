package hw9;

import kotlin.test.assertEquals

public abstract class TestRunner {

    abstract fun getRunner(): CodeRunner

    fun testCode(source: String, input: String, output: String) {
        val res = getRunner().runCode(source, input)
        assertEquals(output, res)
    }

}