package kr.jell.android_study.Gugudan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.jell.android_study.databinding.ActivityGugudanBinding
import kr.jell.android_study.domain.model.MultiplicationTableState
import kr.jell.android_study.presentation.viewmodel.GugudanViewModel
import kr.jell.android_study.utils.collectWithLifecycle
import kr.jell.android_study.utils.toast

/**
 * 구구단 화면
 * MVVM 패턴, StateFlow, Coroutines를 사용한 최신 아키텍처
 */
class Gugudan : AppCompatActivity() {

    private lateinit var binding: ActivityGugudanBinding
    private val viewModel: GugudanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGugudanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
    }

    /**
     * StateFlow 관찰 설정
     */
    private fun setupObservers() {
        viewModel.state.collectWithLifecycle(this) { state ->
            handleState(state)
        }

        viewModel.selectedMultiplier.collectWithLifecycle(this) { multiplier ->
            updateTitle(multiplier)
        }
    }

    /**
     * 상태에 따른 UI 업데이트
     */
    private fun handleState(state: MultiplicationTableState) {
        when (state) {
            is MultiplicationTableState.Initial -> {
                // 초기 상태
            }

            is MultiplicationTableState.Loading -> {
                // 로딩 상태
                binding.textView3.text = "Loading..."
            }

            is MultiplicationTableState.Success -> {
                val result = state.tables.joinToString("\n") { it.toDisplayString() }
                binding.textView3.text = result
            }

            is MultiplicationTableState.Error -> {
                toast(state.message)
                binding.textView3.text = "Error: ${state.message}"
            }
        }
    }

    /**
     * 제목 업데이트
     */
    private fun updateTitle(multiplier: Int) {
        supportActionBar?.title = "구구단 - ${multiplier}단"
    }
}
