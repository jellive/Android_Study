package kr.jell.android_study.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kr.jell.android_study.domain.model.MultiplicationEvent
import kr.jell.android_study.domain.model.MultiplicationTableState
import kr.jell.android_study.domain.repository.MultiplicationRepository
import kr.jell.android_study.domain.repository.MultiplicationRepositoryImpl

/**
 * 구구단 ViewModel
 * StateFlow와 Coroutines를 사용한 최신 패턴
 */
class GugudanViewModel(
    private val repository: MultiplicationRepository = MultiplicationRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow<MultiplicationTableState>(MultiplicationTableState.Initial)
    val state: StateFlow<MultiplicationTableState> = _state.asStateFlow()

    private val _selectedMultiplier = MutableStateFlow(2)
    val selectedMultiplier: StateFlow<Int> = _selectedMultiplier.asStateFlow()

    /**
     * 이벤트 처리
     */
    fun onEvent(event: MultiplicationEvent) {
        when (event) {
            is MultiplicationEvent.SelectMultiplier -> loadMultiplicationTable(event.multiplier)
            is MultiplicationEvent.LoadAllTables -> loadAllTables()
            is MultiplicationEvent.Reset -> reset()
        }
    }

    /**
     * 특정 단의 구구단 로드
     */
    private fun loadMultiplicationTable(multiplier: Int) {
        viewModelScope.launch {
            _state.value = MultiplicationTableState.Loading
            _selectedMultiplier.value = multiplier

            repository.getMultiplicationTable(multiplier)
                .catch { e ->
                    _state.value = MultiplicationTableState.Error(
                        e.message ?: "Unknown error occurred"
                    )
                }
                .collect { tables ->
                    _state.value = MultiplicationTableState.Success(tables)
                }
        }
    }

    /**
     * 전체 구구단 로드
     */
    private fun loadAllTables() {
        viewModelScope.launch {
            _state.value = MultiplicationTableState.Loading

            repository.getAllMultiplicationTables()
                .catch { e ->
                    _state.value = MultiplicationTableState.Error(
                        e.message ?: "Unknown error occurred"
                    )
                }
                .collect { allTables ->
                    // 첫 번째 테이블(2단)을 보여줌
                    _state.value = MultiplicationTableState.Success(
                        allTables[2] ?: emptyList()
                    )
                }
        }
    }

    /**
     * 상태 초기화
     */
    private fun reset() {
        _state.value = MultiplicationTableState.Initial
        _selectedMultiplier.value = 2
    }

    init {
        // 초기 로드
        loadMultiplicationTable(2)
    }
}
