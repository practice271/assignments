package hw9

import org.junit.Test

class TestPetooh: TestRunner() {
    val r = PetoohRunner()

    override fun getRunner() = r

    @Test
    fun testPetooh() {
        testCode("KoKoKoKoKoKoKoKoKoKo Kud-Kudah KoKoKoKoKoKoKoKo kudah kO kud-Kudah Kukarek" +
                 "kudah KoKoKo Kud-Kudah kOkOkOkO kudah kO kud-Kudah Ko Kukarek" +
                 "kudah KoKoKoKo Kud-Kudah KoKoKoKo kudah kO kud-Kudah kO Kukarek" +
                 "kOkOkOkOkO Kukarek Kukarek" +
                 "kOkOkOkOkOkOkO Kukarek", "", "PETOOH")
    }

}