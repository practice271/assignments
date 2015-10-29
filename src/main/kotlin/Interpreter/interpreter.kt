package Interpreter

import java.util.*

public abstract class Expression {
    public abstract fun interpret(tokens: Map<String, Expression>): Int
}

public class Number(private val number: Int) : Expression() {
    override fun interpret(tokens: Map<String, Expression>): Int {
        return number
    }
}

public class Variable(private val variable: String) : Expression() {
    override fun interpret(tokens: Map<String, Expression>): Int {
        return tokens.get(variable)?.interpret(tokens) ?: variable.toInt()
    }
}

public class Plus(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(tokens: Map<String, Expression>): Int {
        return leftOperand.interpret(tokens) + rightOperand.interpret(tokens)
    }
}

public class Minus(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(tokens: Map<String, Expression>): Int {
        return leftOperand.interpret(tokens) - rightOperand.interpret(tokens)
    }
}

public class Multi(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(tokens: Map<String, Expression>): Int {
        return leftOperand.interpret(tokens) * rightOperand.interpret(tokens)
    }
}

public class Calculator(expression: String): Expression() {
    private val syntaxTree: Expression
    init{
        val expressionStack = Stack<Expression>()
        for (token in expression.split(" ")) {
            when (token) {
                "+" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Plus(left, right)
                    expressionStack.push(subExpression)}
                "-" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Minus(left, right)
                    expressionStack.push(subExpression)}
                "*" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Multi(left, right)
                    expressionStack.push(subExpression)}
                else -> expressionStack.push(Variable(token))
            }
        }
        syntaxTree = expressionStack.pop()
    }
    override public fun interpret(context: Map<String, Expression>): Int {
        return syntaxTree.interpret(context)}
}

fun main(args: Array<String>) {
    val expression = "a b c * 6 - +"
    val sentence = Calculator(expression)
    var tokens = HashMap<String,Expression>()
    tokens.put("a", Number(1))
    tokens.put("b", Number(2))
    tokens.put("c", Number(3))
    val result = sentence.interpret(tokens)
    println(result)
}

