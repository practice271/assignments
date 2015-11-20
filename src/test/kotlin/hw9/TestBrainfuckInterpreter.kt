package hw9

public class TestBrainfuckInterpreter : TestBrainfuckRunner() {
    val r = BrainInterpreter()

    override fun getRunner(): CodeRunner = r

}