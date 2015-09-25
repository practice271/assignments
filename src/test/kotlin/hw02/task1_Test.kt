/**
 * Created by z on 20.09.2015.
 */

package hw02
import org.junit.Test
import java.util.Arrays;

public class task1_Test {
    @Test fun test1() {
        val OS = arrayOf("Solaris","Windows", "Linux", "Linux", "Solaris", "Windows", "Mac OS X", "Linux")
        var infected = arrayOf(true, false, false, false, true, false, false, true)
        val neighbors = arrayOf(listOf(5,6), listOf(4,7), listOf(3,7), listOf(2,6), listOf(1,7), listOf(0,6), listOf(0,3,5), listOf(2,1,4))
        val infectedResult = arrayOf(true, true, false, false, true, true, true, true)
        virus(OS,infected, neighbors)
        var k: Boolean = Arrays.equals(infected, infectedResult)
        print (k)
    }

    @Test fun test2() {
        val OS = arrayOf("Linux","Solaris","Mac OS X", "Linux", "Mac OS X", "Windows", "Solaris")
        var infected = arrayOf(false, true, false, false, true, true, false)
        val neighbors = arrayOf(listOf(4,5), listOf(4,6), listOf(3,6), listOf(2,5), listOf(0,1), listOf(0,3), listOf(1,2))
        val infectedResult = arrayOf(false, true, true, false, true, true, true)
        virus(OS,infected, neighbors)
        var k: Boolean = Arrays.equals(infected, infectedResult)
        print (k)
    }

    @Test fun test3() {
        val OS = arrayOf("Mac OS X","Solaris", "Linux", "Mac OS X", "Windows", "Windows")
        var infected = arrayOf(false, true, true, false, false, false)
        val neighbors = arrayOf(listOf(2,4), listOf(3,5), listOf(0,4), listOf(1,5), listOf(0,2), listOf(1,3))
        val infectedResult = arrayOf(true, true, true, true, true, true)
        virus(OS,infected, neighbors)
        var k: Boolean = Arrays.equals(infected, infectedResult)
        print (k)
    }

}
