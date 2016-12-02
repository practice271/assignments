package hw9

public class TestBrainfuckGenerator : TestBrainfuckRunner() {
    val r = BrainRunner()

    override fun getRunner(): CodeRunner = r
}