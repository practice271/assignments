interface Expression {
    public fun interpret (context : String) : Boolean
}

public class TerminalExpression (data : String) : Expression {

    private var data : String

    init
    {
        this.data = data
    }

    override public fun interpret (context : String) : Boolean {

        if(context.contains(data)){
            return true;
        }
        return false;
    }
}

public class OrExpression (expr1 : Expression, expr2 : Expression) : Expression {

    private var expr1 : Expression
    private var expr2 : Expression

    init
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    override public fun interpret (context : String) : Boolean{
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

public class AndExpression (expr1 : Expression, expr2 : Expression) : Expression {

    private var expr1 : Expression
    private var expr2 : Expression

    init
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    override public fun interpret (context : String) : Boolean {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}


//Rule: Robert and John are male
public fun getMaleExpression() : Expression{
    var robert : Expression = TerminalExpression("Robert");
    var john : Expression = TerminalExpression("John");
    return OrExpression(robert, john);
}

//Rule: Julie is a married women
public fun getMarriedWomanExpression() : Expression{
    var julie : Expression = TerminalExpression("Julie");
    var married : Expression = TerminalExpression("Married");
    return AndExpression(julie, married);
}

public fun main (args : Array<String>) {
    var isMale : Expression = getMaleExpression();
    var isMarriedWoman : Expression = getMarriedWomanExpression();

    println("John is male? " + isMale.interpret("John"));
    println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
}