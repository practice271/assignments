package TicTacToe

/**
 * The tic-tac-toe on infinity field.
 */

public open class Logic() {

    public var fieldSizeHorizontal: Int = 5 //default size
    public var fieldSizeVertical: Int = 5   //default size
    public var field: Array<Array<Char>> = Array(fieldSizeHorizontal,{Array(fieldSizeVertical, { ' ' })})
    public var winLength: Int = 5
    public var gameOver: Boolean = false
    public var winner: Char = ' '

    public fun checkWinRow() {
        var count: Int = 1
        for (x in 0..fieldSizeHorizontal - 1) {
            for (y in 0..fieldSizeVertical - 2) {
                if ((field[x][y] != ' ')&&(field[x][y] == field[x][y + 1])) { count++ }
                else { count = 1}
                if (count == winLength) {
                    gameOver = true
                    winner = field[x][y]
                }
            }
        }
    }

    public fun checkWinColumn() {
        var count: Int = 1
        for (y in 0..fieldSizeVertical - 1) {
            for (x in 0..fieldSizeHorizontal - 2) {
                if ((field[x][y] != ' ')&&(field[x][y] == field[x + 1][y])) { count++ }
                else { count = 1}
                if (count == winLength) {
                    gameOver = true
                    winner = field[x][y]
                }
            }
        }
    }

    public fun checkWinLRDiagonal() {
        var count = 1
        var minLength = Math.min(fieldSizeVertical, fieldSizeHorizontal)
        for (x in 0..minLength - 2) {
            if ((field[x][x] != ' ')&&(field[x][x] == field[x + 1][x + 1])) { count++ }
            else { count = 1}
            if (count == winLength) {
                gameOver = true
                winner = field[x][x]
            }
        }
    }

    public fun checkWinRLDiagonal() {
        var count = 1
        var minLength = Math.min(fieldSizeVertical, fieldSizeHorizontal)-1

        for (i in 0..minLength-1) {
            if ((field[i][minLength - i] != ' ')&&(field[i][minLength - i] == field[i + 1][minLength - i - 1])) {
                count++
            } else { count = 1 }
            if (count == winLength) {
                gameOver = true
                winner = field[i][minLength - i]
            }
        }
    }

    public fun checkWin() {
        checkWinRow()
        checkWinColumn()
        checkWinLRDiagonal()
        checkWinRLDiagonal()
    }

    public fun resize(index: Int, flag: Int) {
        if(flag == 1) {
            var newFieldSizeHorizontal = fieldSizeHorizontal - 1
            while(index >= newFieldSizeHorizontal) { newFieldSizeHorizontal *= 2}
            var newField: Array<Array<Char>> = Array(newFieldSizeHorizontal,{Array(fieldSizeVertical, {' '}) })
            arrayCopy(newField)
            fieldSizeHorizontal = newFieldSizeHorizontal
            field = newField
            return
        }
        else {
            var newFieldSizeVertical = fieldSizeVertical - 1
            while(index >= newFieldSizeVertical)   { newFieldSizeVertical *=2 }
            var newField: Array<Array<Char>> = Array(fieldSizeHorizontal,{Array(newFieldSizeVertical, {' '}) })
            arrayCopy(newField)
            fieldSizeVertical = newFieldSizeVertical
            field = newField
            return
        }
        return
    }

    public fun shiftUp(shiftNumber: Int) { //if input = arr[x][-y]
        var reserveField: Array<Array<Char>> = Array(fieldSizeHorizontal,{Array(fieldSizeVertical + shiftNumber, {' '}) })

        for(i in 0..fieldSizeHorizontal - 1) {
            for ( i2 in 0..fieldSizeVertical - 1) {
                reserveField[i][i2 + shiftNumber] = field[i][i2]
            }
        }
        fieldSizeVertical += shiftNumber
        field = reserveField
    }

    public fun shiftLeft(shiftNumber: Int) { //if input = arr[-x][y]
        var reserveField: Array<Array<Char>> = Array(fieldSizeHorizontal + shiftNumber,{Array(fieldSizeVertical, {' '}) })

        for(i in 0..fieldSizeHorizontal - 1) {
            for ( i2 in 0..fieldSizeVertical - 1) {
                reserveField[i + shiftNumber][i2] = field[i][i2]
            }
        }
        fieldSizeHorizontal += shiftNumber
        field = reserveField
    }

    public fun arrayCopy(newField: Array<Array<Char>>) {
        for(i in 0..fieldSizeHorizontal - 1) {
            for ( i2 in 0..fieldSizeVertical - 1) {
                newField[i][i2] = field[i][i2]
            }
        }
        return
    }

    public fun readValue(coordX: Int, coordY:Int,  number: Int) {
        var coordX_ = coordX
        var coordY_ = coordY

        if (coordX > fieldSizeHorizontal - 1) { resize(coordX, 1)}
        if (coordY > fieldSizeVertical - 1)   { resize(coordY, 0)}

        if (coordY < 0) { shiftUp(Math.abs(coordY));   coordY_ = 0 }
        if (coordX < 0) { shiftLeft(Math.abs(coordX)); coordX_ = 0 }

        if (number % 2 == 0) { field[coordX_][coordY_] = 'X'} else {field[coordX_][coordY_] = 'O'}
    }
}