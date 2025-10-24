package kr.jell.android_study

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth.assertThat
import kr.jell.android_study.Gugudan.Gugudan
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * GugudanActivity E2E 테스트
 * 구구단 화면의 통합 테스트
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class GugudanActivityE2ETest {

    private lateinit var scenario: ActivityScenario<Gugudan>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(Gugudan::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun gugudanActivity가_정상적으로_시작되어야_한다() {
        // Then
        onView(withId(R.id.textView3))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 구구단_결과가_표시되어야_한다() {
        // Given - ViewModel이 초기화되고 데이터를 로드할 시간
        Thread.sleep(1000)

        // Then
        onView(withId(R.id.textView3))
            .check(matches(isDisplayed()))

        // 구구단 형식이 포함되어 있는지 확인
        scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textView3)
            val text = textView.text.toString()

            // "x" 기호가 포함되어 있어야 함 (구구단 형식)
            assertThat(text).contains("x")
            assertThat(text).contains("=")
        }
    }

    @Test
    fun 초기_구구단_2단이_표시되어야_한다() {
        // Given
        Thread.sleep(1000) // ViewModel 로딩 대기

        // Then
        scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textView3)
            val text = textView.text.toString()

            // 2단의 첫 번째 항목이 있어야 함
            assertThat(text).contains("2 x 1 = 2")
        }
    }

    @Test
    fun 구구단_결과가_올바른_형식이어야_한다() {
        // Given
        Thread.sleep(1000)

        // Then
        scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textView3)
            val text = textView.text.toString()

            // 여러 줄로 구성되어 있어야 함
            assertThat(text.lines()).isNotEmpty()

            // 최소 9개의 라인이 있어야 함 (1-9까지)
            assertThat(text.lines().size).isAtLeast(9)
        }
    }

    @Test
    fun 구구단_계산이_정확해야_한다() {
        // Given
        Thread.sleep(1000)

        // Then
        scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textView3)
            val text = textView.text.toString()

            // 2단 샘플 검증
            assertThat(text).contains("2 x 1 = 2")
            assertThat(text).contains("2 x 5 = 10")
            assertThat(text).contains("2 x 9 = 18")
        }
    }

    @Test
    fun activity가_올바른_생명주기를_가져야_한다() {
        // Then
        scenario.onActivity { activity ->
            assertThat(activity).isNotNull()
            assertThat(activity.isFinishing).isFalse()
            assertThat(activity.isDestroyed).isFalse()
        }
    }

    @Test
    fun viewBinding이_올바르게_작동해야_한다() {
        // Then
        scenario.onActivity { activity ->
            // TextView가 null이 아니어야 함
            val textView = activity.findViewById<android.widget.TextView>(R.id.textView3)
            assertThat(textView).isNotNull()
        }
    }
}
