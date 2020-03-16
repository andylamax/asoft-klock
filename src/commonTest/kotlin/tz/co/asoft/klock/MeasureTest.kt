package tz.co.asoft.klock

import kotlin.test.*

class MeasureTest {
    @Test
    fun test() {
        val result = measureTimeWithResult {
            val start = DateTime.now()
            do {
                val current = DateTime.now()

            } while (current - start < 40.milliseconds)
            "hello"
        }
        assertEquals("hello", result.result)
        assertTrue { result.time >= 20.milliseconds && result.time <= 1.seconds }
    }
}