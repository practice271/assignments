package hw9

public class TestGenerator: TestRunner() {
    val r = BrainRunner()

    override fun getRunner(): CodeRunner = r
}