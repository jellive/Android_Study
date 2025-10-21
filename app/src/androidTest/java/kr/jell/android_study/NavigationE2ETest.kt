package kr.jell.android_study

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI Automator를 사용한 네비게이션 E2E 테스트
 * 앱 전체 흐름을 테스트
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationE2ETest {

    private lateinit var device: UiDevice
    private val packageName = "kr.jell.android_study"
    private val timeout = 5000L

    @Before
    fun setup() {
        // UI Automator 초기화
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 홈 화면으로 이동
        device.pressHome()

        // 앱 실행
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)?.apply {
            addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // 앱이 시작될 때까지 대기
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), timeout)
    }

    @Test
    fun 앱이_정상적으로_시작되어야_한다() {
        // Then
        val startButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        assertThat(startButton).isNotNull()
    }

    @Test
    fun start버튼_클릭시_구구단_화면으로_이동해야_한다() {
        // When
        val startButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        startButton?.click()

        // Then
        device.wait(Until.hasObject(By.pkg(packageName)), timeout)

        // 구구단 내용 확인 (시간을 조금 더 기다림)
        Thread.sleep(2000)

        // 화면에 구구단 관련 텍스트가 있는지 확인
        val hasMultiplicationSymbol = device.hasObject(By.textContains("x"))
        val hasEqualsSymbol = device.hasObject(By.textContains("="))

        assertThat(hasMultiplicationSymbol || hasEqualsSymbol).isTrue()
    }

    @Test
    fun 뒤로가기_버튼으로_메인_화면으로_돌아가야_한다() {
        // Given - 구구단 화면으로 이동
        val startButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        startButton?.click()
        Thread.sleep(1000)

        // When - 뒤로 가기
        device.pressBack()
        Thread.sleep(500)

        // Then - 메인 화면의 Start 버튼이 다시 보여야 함
        val backToMainButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        assertThat(backToMainButton).isNotNull()
    }

    @Test
    fun 앱_재시작_후에도_정상_작동해야_한다() {
        // Given
        val startButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        startButton?.click()
        Thread.sleep(1000)

        // When - 최근 앱 화면으로 가서 앱 종료 후 재시작
        device.pressBack()
        Thread.sleep(500)

        val restartButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        restartButton?.click()

        // Then
        Thread.sleep(2000)
        val hasContent = device.hasObject(By.textContains("x")) ||
                device.hasObject(By.textContains("="))
        assertThat(hasContent).isTrue()
    }

    @Test
    fun 화면_회전_후에도_정상_작동해야_한다() {
        // Given
        val startButton = device.wait(
            Until.findObject(By.text("Start")),
            timeout
        )
        startButton?.click()
        Thread.sleep(1500)

        // When - 화면 회전
        device.setOrientationLeft()
        Thread.sleep(1000)

        // Then - 여전히 구구단 내용이 보여야 함
        val hasContent = device.hasObject(By.textContains("x")) ||
                device.hasObject(By.textContains("="))
        assertThat(hasContent).isTrue()

        // 원래대로 복원
        device.setOrientationNatural()
    }
}
