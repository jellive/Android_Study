package kr.jell.android_study

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 * Updated with Truth assertions and modern Kotlin practices
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertThat(2 + 2).isEqualTo(4)
    }

    @Test
    fun multiplication_isCorrect() {
        assertThat(3 * 4).isEqualTo(12)
    }

    @Test
    fun subtraction_isCorrect() {
        assertThat(10 - 3).isEqualTo(7)
    }

    @Test
    fun division_isCorrect() {
        assertThat(20 / 4).isEqualTo(5)
    }

    @Test
    fun string_concatenation_works() {
        val hello = "Hello"
        val world = "World"
        assertThat(hello + " " + world).isEqualTo("Hello World")
    }

    @Test
    fun list_operations_work() {
        val list = listOf(1, 2, 3, 4, 5)
        assertThat(list).hasSize(5)
        assertThat(list).contains(3)
        assertThat(list.sum()).isEqualTo(15)
    }
}
