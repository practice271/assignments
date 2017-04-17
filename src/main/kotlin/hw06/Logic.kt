package hw06

import java.util.*

public class Logic(){
    var arrPlay = Array(9,{0})

    public fun availableStroke(): Boolean{
        return (!arrPlay.contains(0))
    }

    public fun findWiner(): Int{ // ������� ���������� ����������
        if (arrPlay[0] == 1 && arrPlay[3] == 1 && arrPlay[6] == 1 ||
                arrPlay[0] == 1 && arrPlay[1] == 1 && arrPlay[2] == 1 ||
                arrPlay[0] == 1 && arrPlay[4] == 1 && arrPlay[8] == 1 ||
                arrPlay[2] == 1 && arrPlay[4] == 1 && arrPlay[6] == 1 ||
                arrPlay[1] == 1 && arrPlay[4] == 1 && arrPlay[7] == 1 ||
                arrPlay[2] == 1 && arrPlay[5] == 1 && arrPlay[8] == 1 ||
                arrPlay[3] == 1 && arrPlay[4] == 1 && arrPlay[5] == 1 ||
                arrPlay[6] == 1 && arrPlay[7] == 1 && arrPlay[8] == 1){
            return 1
        }
        if (arrPlay[0] == 2 && arrPlay[3] == 2 && arrPlay[6] == 2 ||
                arrPlay[0] == 2 && arrPlay[1] == 2 && arrPlay[2] == 2 ||
                arrPlay[0] == 2 && arrPlay[4] == 2 && arrPlay[8] == 2 ||
                arrPlay[2] == 2 && arrPlay[4] == 2 && arrPlay[6] == 2 ||
                arrPlay[1] == 2 && arrPlay[4] == 2 && arrPlay[7] == 2 ||
                arrPlay[2] == 2 && arrPlay[5] == 2 && arrPlay[8] == 2 ||
                arrPlay[3] == 2 && arrPlay[4] == 2 && arrPlay[5] == 2 ||
                arrPlay[6] == 2 && arrPlay[7] == 2 && arrPlay[8] == 2){
            return 2
        }
        return 0
    }

    public fun reader(player: Int,playerStroke: Int): Int {
        if (playerStroke >= 0 && playerStroke <= 8) {
            if (arrPlay[playerStroke] == 0) {
                arrPlay[playerStroke] = player
                return 0
            }
            return -2
        }
        return -1
    }
}
