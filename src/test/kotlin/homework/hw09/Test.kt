package homework.hw09

import homework.Hw09.Converter
import kotlin.test.assertEquals
import org.junit.Test
import homework.Hw09.Interpreter

public class ConverterTest{
    //Length : 129
    @Test fun Test1(){
        val text = "Hello, Kotlin!"
        val converter = Converter(text)
        test(converter,text)
    }
    //Length : 62
    @Test fun Test2(){
        val text = "+++[][][]..."
        val converter = Converter(text)
        test(converter,text)
    }
    //Length : 114
    @Test fun Test3(){
        val text = "Hello World!"
        val converter = Converter(text)
        test(converter,text)
    }
    //Length: 626
    @Test fun Test4(){
        val text = "Ata tata tata zukkyun!" +
                    "Wa tata tatata dokkyun!" +
                    "Zukyun! Dokyun!" +
                    "Zukyun! Dokyun!" +
                    "Yada! Yada! Yada! Yada!"
        val converter = Converter(text)
        test(converter,text)
    }

    //Test for other symbols
    //Length : 912
    @Test fun Test5(){
        val text = "var hash = require('crypto').createHash('sha1');" +
                "hash.update(Math.random().toString());" +
                "hash = hash.digest('hex');" +
                "var device_id = 'android:' + hash;"
        val converter = Converter(text)
        test(converter,text)
    }

    //One more
    //Length : 806
    @Test fun Test6(){
        val text = "Node.js� is a JavaScript runtime built on Chrome's V8 JavaScript engine."
        val converter = Converter(text)
        test(converter,text)
    }

    private fun test(converter: Converter, text:String){
        val bf = converter.convert()
        val interpreter = Interpreter().Interpret(bf)
        assertEquals(interpreter, text)
    }
}