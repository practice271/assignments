package hw02

import org.junit.Test
import kotlin.test.assertEquals

public class hw02Test {
   val comp1 = Computer("Windows", 0)
   val comp2 = Computer("MacOS", 1)
   val comp3 = Computer("Linux", 2)

   val a1 = arrayOf(comp1, comp2, comp3)
   val a2 = arrayOf(comp2, comp1)
   val a3 = arrayOf(comp3, comp1)
   val con = arrayOf(a1, a2, a3)

   @Test fun bigProbability() {
      val inf = arrayOf(true, false, false)
      var gr = CompGraph(3, con, inf)
      gr.delayTime(100, false)
      val answer = arrayOf(true, true, true)
      assertEquals(gr.infected.toList(), answer.toList())
   }

   @Test fun noVirus() {
      val inf = arrayOf(false, false, false)
      var gr = CompGraph(3, con, inf)
      gr.delayTime(200, false)
      assertEquals(gr.infected.toList(), inf.toList())
   }

   @Test fun twoCompsOutOfNet() {
      val comp1 = Computer("Windows", 0)
      val comp2 = Computer("MacOS", 1)
      val comp3 = Computer("Linux", 2)
      val comp4 = Computer("Windows", 3)
      val comp5 = Computer("MacOS", 4)
      val comp6 = Computer("Linux", 5)

      val a1 = arrayOf(comp1, comp2, comp4)
      val a2 = arrayOf(comp2, comp1, comp4)
      val a3 = arrayOf(comp3, comp4)
      val a4 = arrayOf(comp4, comp1, comp2, comp3)
      val a5 = arrayOf(comp5)
      val a6 = arrayOf(comp6)

      val con = arrayOf(a1, a2, a3, a4, a5, a6)
      val inf = arrayOf(false, false, false, true, false, false)
      val answer = arrayOf(true, true, true, true, false, false)
      var gr = CompGraph(6, con, inf)
      gr.delayTime(200, false)
      assertEquals(gr.infected.toList(), answer.toList())
   }
}