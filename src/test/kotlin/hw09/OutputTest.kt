package hw09

import org.junit.After
import org.junit.Before
import java.io.ByteArrayOutputStream
import java.io.PrintStream

abstract class OutputTest {
    protected val output = ByteArrayOutputStream()
    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(output))
    }
    @After
    fun cleanUpStreams() {
        System.setOut(null)
    }
}