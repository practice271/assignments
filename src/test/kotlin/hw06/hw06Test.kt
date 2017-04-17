package hw06

import org.junit.Test
import kotlin.test.assertEquals

public class hw06Test {
    @Test fun Test1() { // вводятся ходы игроков до победного: 0-player1,1-player2, 2-player1 etc
        var game = Console()
        var player = 1
        var state = ""
        var playerStroke = 0
        val arrTest = arrayOf(0,1,3,2,6)
        // |_X_|_O_|_O_|
        // |_X_|___|___|
        // |_X_|___|___|
        while (state != "Player wins!" && state != "Dead heat!") {
            for (i in 0..4) {
                playerStroke = arrTest[i]
                state = game.nextStroke(player, playerStroke)
                if (state != "try again") player = 3 - player
            }
        }
        assertEquals(state,"Player wins!")
    }
    @Test fun Test2() { // вводятся ходы игроков до победного: 0-player1,1-player2, 2-player1 etc
        var game = Console()
        var player = 1
        var state = ""
        var playerStroke = 0
        val arrTest = arrayOf(1,0,2,4,3,8)
       // |_O_|_X_|_X_|
       // |_X_|_O_|___|
       // |___|___|_O_|
        while (state != "Player wins!" && state != "Dead heat!") {
            for (i in 0..5) {
                playerStroke = arrTest[i]
                state = game.nextStroke(player, playerStroke)
                if (state != "try again") player = 3 - player
            }
        }
        assertEquals(state,"Player wins!")
    }
    @Test fun Test3() { // вводятся ходы игроков до победного: 0-player1,1-player2, 2-player1 etc
        var game = Console()
        var player = 1
        var state = ""
        var playerStroke = 0
        val arrTest = arrayOf(0,1,4,2,6,3,5,8,7)
        // |_X_|_O_|_O_|
        // |_O_|_X_|_X_|
        // |_X_|_X_|_O_|
        while (state != "Player wins!" && state != "Dead heat!") {
            for (i in 0..8) {
                playerStroke = arrTest[i]
                state = game.nextStroke(player, playerStroke)
                if (state != "try again") player = 3 - player
            }
        }
        assertEquals(state,"Dead heat!")
    }
}