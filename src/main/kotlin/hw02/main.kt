package hw02

import java.util.Random

class Computer(
   val OS : String,
   val number : Int
) {
   fun probability () : Double {
      if (OS == "Windows") {return 0.3}
      if (OS == "Linux") {return 0.2}
      if (OS == "MacOS") {return 0.1}
      else throw Exception("Unknown OS")
   }
}

fun randomise(needToRandom : Boolean) : Int {
   if (needToRandom) {
      val f = Random().nextInt(11)
      println (f)
      return f
   }
   else return 1000
}

class CompGraph(
        val numberOfComps : Int,
        val connect : Array<Array<Computer>>,//каждый компьютер соединен с самим собой
        var infected : Array<Boolean>
) {
   fun infection(needToRandom: Boolean) {
      val result = infected
      var i = numberOfComps - 1
      while (i >= 0) {
         if (infected[i]) {
            var risk = connect[i]
            var j = risk.size() - 1
            while (j > 0) {
               var k = risk[j]
               val coeff = randomise(needToRandom)
               if (!infected[k.number]) {
                  if (k.probability() * coeff > 1) {
                     result[k.number] = true
                  }
               }
               j--
            }
         }
         i--
      }
      infected = result
   }
}

fun CompGraph.delayTime (time : Int, needToRandom: Boolean) {
   var t = time
   while (t > 0) {
      this.infection(needToRandom)
      t--
   }
}

fun main(args: Array<String>){
   val comp1 = Computer("Windows", 0)
   val comp2 = Computer("MacOS", 1)
   val comp3 = Computer("Linux", 2)

   val a1 = arrayOf(comp1, comp2, comp3)
   val a2 = arrayOf(comp2, comp1)
   val a3 = arrayOf(comp3, comp1)
   val con = arrayOf(a1, a2, a3)

   val inf = arrayOf(true, false, false)

   var gr = CompGraph(3, con, inf)
   gr.delayTime(100, true)
   print(gr.infected.toList())
}