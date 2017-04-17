package homeworks.hw06.logic

/**
 * Created by Ilya on 23.10.2015.
 */
public abstract class Logic {
    var array = arrayOf(Array(3, {"null"}), Array(3, {"null"}), Array(3, {"null"}))

    public fun checkSuccess(char: String): Boolean {
        fun checkDiagonals(): Boolean {
            if (array[0][0] == char && array[1][1] == char && array[2][2] == char ||
                array[2][0] == char && array[1][1] == char && array[0][2] == char) {
                return true
            }
            return false
        }
        fun checkHorizontals(): Boolean {
            if (array[0][0] == char && array[0][1] == char && array[0][2] == char ||
                array[1][0] == char && array[1][1] == char && array[1][2] == char ||
                array[2][0] == char && array[2][1] == char && array[2][2] == char) {
                return true
            }
            return false
        }
        fun checkVerticals(): Boolean {
            if (array[0][0] == char && array[1][0] == char && array[2][0] == char ||
                array[0][1] == char && array[1][1] == char && array[2][1] == char ||
                array[0][2] == char && array[1][2] == char && array[2][2] == char) {
                return true
            }
            return false
        }

        if (checkDiagonals() || checkHorizontals() || checkVerticals()) {
            return true
        }
        return false
    }

}