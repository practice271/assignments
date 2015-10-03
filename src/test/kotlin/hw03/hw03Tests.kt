package hw03

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.asserter

public class hw03Tests
{
    @Test fun Add ()
    {
        var tree : Node? = null

        tree = Add(tree, 5)
        tree = Add(tree, 4)
        tree = Add(tree, 3)
        tree = Add(tree, 2)

        assertEquals(tree?.printTree(), "| ||  | 2| ||  | \n| 3| \n4\n| 5| \n")
    }

    @Test fun Delete ()
    {
        var tree : Node? = null

        tree = Add(tree, 5)
        tree = Add(tree, 4)
        tree = Add(tree, 3)
        tree = Add(tree, 2)

        assertEquals(Remove(tree, 3)?.printTree(), "| 2| \n4\n| 5| \n")
    }

    @Test fun Delete2 ()
    {
        var tree : Node? = null

        tree = Add(tree, 5)
        tree = Add(tree, 4)
        tree = Add(tree, 3)
        tree = Add(tree, 2)

        assertEquals(Remove(tree, 1)?.printTree(), "| ||  | 2| ||  | \n| 3| \n4\n| 5| \n")
    }

    @Test fun Search ()
    {
        var tree : Node? = null

        tree = Add(tree, 5)
        tree = Add(tree, 4)
        tree = Add(tree, 3)
        tree = Add(tree, 2)

        assertEquals(SearchNode(tree, 4), true)
    }

    @Test fun Search2 ()
    {
        var tree : Node? = null

        tree = Add(tree, 5)
        tree = Add(tree, 4)
        tree = Add(tree, 3)
        tree = Add(tree, 2)

        assertEquals(SearchNode(tree, 6), false)
    }
}