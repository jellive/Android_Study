package kr.jell.android_study

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kr.jell.android_study.Gugudan.Gugudan
import kr.jell.android_study.databinding.ActivityMainBinding
import kr.jell.android_study.utils.startActivity

/**
 * 메인 화면
 * 최신 Kotlin 문법과 Extension Functions 활용
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    /**
     * 클릭 리스너 설정
     */
    private fun setupClickListeners() {
        binding.startBtn.setOnClickListener {
            Log.d(TAG, "Start Button Clicked - Navigating to Gugudan")
            navigateToGugudan()
        }
    }

    /**
     * 구구단 화면으로 이동
     * Inline reified extension function 사용
     */
    private fun navigateToGugudan() {
        startActivity<Gugudan>()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
