package hw9

public class TestInterpreter: TestRunner() {
    val r = BrainInterpreter()

    override fun getRunner(): CodeRunner = r

}