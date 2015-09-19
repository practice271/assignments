// Тесты для задания 02 от 15.09.2015 (компьютерная сеть)
// Автор: Кирилл Смиренко, группа 271
package homework.hw02

import java.util.Random
import org.junit.Test
import kotlin.test.assertEquals

class CustomRandom(f : () -> Double) : Random() {
    val nd : () -> Double = f

    override fun nextDouble() : Double {
        return nd()
    }
}

public class Hw02Test {
    @Test fun testZeroInfection() {
        // тест линейной сети, где зараза не распространяется
        val net = Network(
                arrayOf("Windows", "Android", "Mac OS", "Linux"),
                listOf(Pair(0, 1), Pair(1, 2), Pair(2, 3)),
                listOf(1),
                CustomRandom({ () -> 1.0})
        )
        // через 100 минут никто не заражен
        for (i in 1..100) net.tick()
        assertEquals("0100", net.getInfectionMap())
    }

    @Test fun testTotalInfection() {
        // тест линейной сети, где зараза распространяется всегда
        val net = Network(
                arrayOf("Windows", "Android", "Mac OS", "Linux", "Windows"),
                listOf(Pair(0, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4)),
                listOf(1),
                CustomRandom({ () -> 0.0})
        )
        // через 1 минуту заразились ближайшие соседи
        net.tick()
        assertEquals("11100", net.getInfectionMap())
        // еще через 1 минуту заразились все, кроме последнего
        net.tick()
        assertEquals("11110", net.getInfectionMap())
        // еще через 1 минуту заражены все
        net.tick()
        assertEquals("11111", net.getInfectionMap())
    }

    @Test fun testTotalInfectionBranchy() {
        // тест разветвленной сети, где зараза распространяется всегда
        val net = Network(
                arrayOf("Windows", "Linux", "Windows", "Android", "Mac OS", "Windows", "Windows"),
                listOf(Pair(0, 1), Pair(0, 3), Pair(1, 4), Pair(3, 4), Pair(4, 5), Pair(2, 5), Pair(4, 6)),
                listOf(3),
                CustomRandom({ () -> 0.0})
        )
        // после 1 минуты заражено 3 компьютера
        net.tick()
        assertEquals("1001100", net.getInfectionMap())
        // после 2 минуты заражено 6 компьютеров
        net.tick()
        assertEquals("1101111", net.getInfectionMap())
        // после 3 минуты заражены все
        net.tick()
        assertEquals("1111111", net.getInfectionMap())
    }
}