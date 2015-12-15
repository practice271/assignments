package bfStringTest

import org.junit.Test
import kotlin.test.assertEquals
import bfStringTest.maktoff.Converter as MaktoffConverter
import bfStringTest.maktoff.BrainfuckInterpreter as MaktoffInterpreter
import bfStringTest.bulgakovk.Converter as BulgakovkConverter
import bfStringTest.bulgakovk.Interpreter as BulgakovkInterpreter
import bfStringTest.spolinaa.Converter as SpolinaaConverter
import bfStringTest.spolinaa.Interpreter as SpolinaaInterpreter
import bfStringTest.mikelle.Converter as MikelleConverter
import bfStringTest.mikelle.Interpreter as MikelleInterpreter
import bfStringTest.awesomelemon.AsciiToBrainfuck as AwesomelemonConverter
import bfStringTest.awesomelemon.brainfuckInterpeter as AwesomelemonInterpreter
import bfStringTest.annaak.Convertor_to_brainfuck as AnnaakConverter
import bfStringTest.annaak.Interpreter_brainfuck as AnnaakInterpreter

class BfToStringTest {
    val exampleStrings = listOf("abc", "def", "Hello world!", "HELLO WORLD!",
"""But the angel said to her, "Do not be afraid, Mary; you have found favor with God.
You will conceive and give birth to a son, and you are to call him Jesus. He will
be great and will be called the Son of the Most High. The Lord God will give him
the throne of his father David, and he will reign over Jacob's descendants forever;
his kingdom will never end." """,
""" Baby, can't you see?
I'm calling a guy like you
Should wear a warning
It's dangerous, I'm fallin'

There's no escape
I can't wait, I need a hit
Baby, give me it
You're dangerous, I'm lovin' it""",
"""Bicycle bicycle bicycle
I want to ride my bicycle bicycle bicycle
I want to ride my bicycle
I want to ride my bike
I want to ride my bicycle
I want to ride it where I like

You say black I say white
You say bark I say bite
You say shark I say hey man
Jaws was never my scene
And I don't like Star Wars """,
"""3.141592653589793238462643383279502884197169399375105820974944592307816406286
208998628034825342117067982148086513282306647093844609550582231725359408128481
117450284102701938521105559644622948954930381964428810975665933446128475648233
786783165271201909145648566923460348610454326648213393607260249141273724587006""")

    fun adequacyTest(convert: (String) -> String, interpret: (String) -> String) {
        for (s in exampleStrings) {
            val convertedToBF = convert(s)
            assert(convertedToBF.contains('+'))
            val interpreted = interpret(convertedToBF)
            assertEquals(s, interpreted)
        }
    }

    fun sizeTest(convert: (String) -> String): Int {
        var count = 0
        for (s in exampleStrings) {
            val convertedToBF = convert(s)
            count += convertedToBF.length
        }
        return count
    }

    val      maktoffConvertFun: (String) -> String = {      MaktoffConverter().convert(it) }
    val    bulgakovkConvertFun: (String) -> String = {    BulgakovkConverter(it).convert() }
    val     spolinaaConvertFun: (String) -> String = {     SpolinaaConverter(it).convert() }
    val      mikelleConvertFun: (String) -> String = {      MikelleConverter.optimalConverte(it) }
    val awesomelemonConvertFun: (String) -> String = { AwesomelemonConverter().translate(it) }
    val       annaakConvertFun: (String) -> String = {       AnnaakConverter(it).run() }

    val       maktoffInterpretFun: (String) -> String = {   MaktoffInterpreter().interpret(it, "") }
    val     bulgakovkInterpretFun: (String) -> String = { BulgakovkInterpreter().Interpret(it) }
    val       annaakIntepreterFun: (String) -> String = {    AnnaakInterpreter(it, "").run() }
    val awesomelemonIntepreterFun: (String) -> String = {
        AwesomelemonInterpreter().interpete(it, resToStdout = false) ?: ""
    }

    /*
    @Test fun megaTest() {
        println("Maktoff     : ${sizeTest(     maktoffConvertFun)}")
        println("Bulgakovk   : ${sizeTest(   bulgakovkConvertFun)}")
        println("Spolinaa    : ${sizeTest(    spolinaaConvertFun)}")
        println("Mikelle     : ${sizeTest(     mikelleConvertFun)}")
        println("Awesomelemon: ${sizeTest(awesomelemonConvertFun)}")
        println("Annaak      : ${sizeTest(      annaakConvertFun)}")
    }
    */

    @Test fun      maktoffAdequacy() { adequacyTest(     maktoffConvertFun,       maktoffInterpretFun) }
    @Test fun    bulgakovkAdequacy() { adequacyTest(   bulgakovkConvertFun,     bulgakovkInterpretFun) }
    @Test fun     spolinaaAdequacy() { adequacyTest(    spolinaaConvertFun,       maktoffInterpretFun) }
    @Test fun      mikelleAdequacy() { adequacyTest(     mikelleConvertFun,       maktoffInterpretFun) }
    @Test fun awesomelemonAdequacy() { adequacyTest(awesomelemonConvertFun, awesomelemonIntepreterFun) }
    @Test fun       annaakAdequacy() { adequacyTest(      annaakConvertFun,       annaakIntepreterFun) }
}