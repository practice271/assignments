package hw02

/**
 * Created by Jinx on 20.09.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals


public class hw02Test {
    Test fun Test1 (){
        var result = ""
        result = start(1.0)
        assertEquals("Infected Infected Infected Infected Infected Alive! ", result)
    }
    Test fun Test0 (){
        var result = ""
        result = start(0.0)
        assertEquals("Infected Alive! Alive! Alive! Alive! Alive! ", result)
    }

}
