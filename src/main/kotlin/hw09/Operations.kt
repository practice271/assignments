package hw09

/* Simple operations
   made by Guzel Garifullina
*/

public enum class Commands{
    SHIFT,
    ADD,
    ZERO,
    OUT,
    IN,
    WHILE,
    END
}
public class Operations(private val type : Commands) {
    private var amt = 1
    constructor(type : Commands, amount : Int) : this (type){
        amt = amount
    }
    public fun getType() : Commands {
        return type
    }
    public fun getAmt() : Int {
        return amt
    }
    public fun addAmt(value : Int) {
        amt += value
    }
    override public fun toString() : String {
        val str = (when (type){
            Commands.SHIFT -> ">="
            Commands.ADD -> "+="
            Commands.ZERO -> "0="
            Commands.OUT -> ".="
            Commands.IN -> ",="
            Commands.END -> "]="
            Commands.WHILE -> "while="
        })
        return "$str $amt\n"
    }
}