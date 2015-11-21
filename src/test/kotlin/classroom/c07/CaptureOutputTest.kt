package classroom.c07

import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream
import junit.framework.TestCase

/**
 * Created by Mikhail on 21.11.2015.
 */

class CaptureOutputTest : TestCase() {
    internal var capture: CaptureOutput? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        capture = CaptureOutput()
    }

    fun testMyPrint() {
        // Prepare to capture output
        val originalOut = System.out
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)
        System.setOut(ps)

        // Perform tests
        capture?.myPrint("Hello, output!")
        TestCase.assertEquals("Hello, output!", os.toString())

        // Restore normal operation
        System.setOut(originalOut)
    }
}
