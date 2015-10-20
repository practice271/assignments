import java.util.*

public abstract class EnglishCharacter
{
    protected abstract var symbol : Char
    protected abstract var width : Int
    protected abstract var height : Int

    public abstract fun printCharacter()
}


public class CharacterA : EnglishCharacter()
{
    override var symbol: Char = 'A'
    override var width = 10
    override var height = 20

    override public fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

public class CharacterB : EnglishCharacter()
{
    override var symbol = 'B'
    override var width = 20
    override var height = 30

    override public fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

public class CharacterC : EnglishCharacter()
{
    override var symbol = 'C'
    override var width = 40
    override var height = 50

    override public fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

public class FlyweightFactory
{
     public fun getCharacter(characterCode : Int) : EnglishCharacter
     {
         var character : EnglishCharacter? = null

         when (characterCode){
            1 -> character = CharacterA()
            2 -> character = CharacterB()
            3 -> character = CharacterC()
         }


        return character ?: throw Exception("Unknown letter");
    }
}

fun main (args : Array<String>)
{
    var factory : FlyweightFactory  = FlyweightFactory();
    var characterCodes : Array<Int> = arrayOf(1, 2, 3);
    for (nextCode in characterCodes){
        var character : EnglishCharacter = factory.getCharacter(nextCode);
        character.printCharacter();
    }
}
