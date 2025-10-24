package kr.jell.android_study

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * Updated with Truth assertions and modern Kotlin practices
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat(appContext.packageName).isEqualTo("kr.jell.android_study")
    }

    @Test
    fun packageName이_올바른지_확인() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat(appContext.packageName).isNotNull()
        assertThat(appContext.packageName).isNotEmpty()
    }

    @Test
    fun context가_null이_아님을_확인() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat(appContext).isNotNull()
    }
}
