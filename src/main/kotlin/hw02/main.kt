package hw02

import java.util.Random

class Computer(
   val OS : String,
   val number : Int
) {
   fun probability () : Double {
      when (OS) {
      "Windows" -> return 0.3
      "Linux" -> return 0.2
      "MacOS" -> return 0.1
      else -> throw Exception("Unknown OS")
      }
   }
}

fun randomise(needToRandom : Boolean) : Int {
   if (needToRandom) {
      val f = Random().nextInt(11)
      return f
   }
   else return 1000
}

class CompGraph(
        val numberOfComps : Int,
        val connect : Array<Array<Computer>>,//each computer connected to itself
        var infected : Array<Boolean>
) {
   fun infection(needToRandom: Boolean) {
      val result = infected
      for (i in numberOfComps..1){
         if (infected[i]) {
            var risk = connect[i]
            for (j in risk.size()..1){
               var k = risk[j]
               val coeff = randomise(needToRandom)
               if (!infected[k.number]) {
                  if (k.probability() * coeff > 1) {
                     result[k.number] = true
                  }
               }
            }
         }
      }
      infected = result
   }
}

fun CompGraph.delayTime (time : Int, needToRandom: Boolean) {
   for (t in time..1 ){
      this.infection(needToRandom)
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