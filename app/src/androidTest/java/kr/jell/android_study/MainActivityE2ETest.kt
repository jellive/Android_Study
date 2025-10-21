package kr.jell.android_study

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivity E2E 테스트
 * Espresso를 사용한 UI 통합 테스트
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityE2ETest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun mainActivity가_정상적으로_시작되어야_한다() {
        // Then
        onView(withId(R.id.startBtn))
            .check(matches(isDisplayed()))
    }

    @Test
    fun startButton이_올바른_텍스트를_보여야_한다() {
        // Then
        onView(withId(R.id.startBtn))
            .check(matches(withText("Start")))
    }

    @Test
    fun helloWorld_텍스트가_표시되어야_한다() {
        // Then
        onView(withId(R.id.textView2))
            .check(matches(isDisplayed()))
            .check(matches(withText("Hello World!")))
    }

    @Test
    fun fromJell_텍스트가_표시되어야_한다() {
        // Then
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
            .check(matches(withText("From Jell")))
    }

    @Test
    fun startButton_클릭시_GugudanActivity로_이동해야_한다() {
        // When
        onView(withId(R.id.startBtn))
            .perform(click())

        // Then
        // 구구단 화면의 텍스트뷰가 보여야 함
        Thread.sleep(1000) // 화면 전환 대기
        onView(withId(R.id.textView3))
            .check(matches(isDisplayed()))
    }

    @Test
    fun activityScenario_상태가_올바른지_확인() {
        // Then
        scenario.onActivity { activity ->
            assertThat(activity).isNotNull()
            assertThat(activity.isFinishing).isFalse()
        }
    }
}
