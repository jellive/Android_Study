package kr.jell.android_study.domain.model

/**
 * 구구단 데이터 모델
 * @param multiplier 곱하는 수 (예: 2단의 2)
 * @param multiplicand 곱해지는 수 (1~9)
 * @param result 결과값
 */
data class MultiplicationTable(
    val multiplier: Int,
    val multiplicand: Int,
    val result: Int = multiplier * multiplicand
) {
    /**
     * "2 x 3 = 6" 형식의 문자열 반환
     */
    fun toDisplayString(): String = "$multiplier x $multiplicand = $result"

    companion object {
        /**
         * 특정 단의 구구단 생성 (1~9까지)
         */
        fun generateTable(multiplier: Int): List<MultiplicationTable> {
            require(multiplier in 2..9) { "Multiplier must be between 2 and 9" }
            return (1..9).map { multiplicand ->
                MultiplicationTable(multiplier, multiplicand)
            }
        }

        /**
         * 전체 구구단 생성 (2~9단)
         */
        fun generateAllTables(): Map<Int, List<MultiplicationTable>> {
            return (2..9).associateWith { multiplier ->
                generateTable(multiplier)
            }
        }
    }
}

/**
 * 구구단 UI 상태를 나타내는 Sealed Class
 */
sealed class MultiplicationTableState {
    data object Initial : MultiplicationTableState()
    data object Loading : MultiplicationTableState()
    data class Success(val tables: List<MultiplicationTable>) : MultiplicationTableState()
    data class Error(val message: String) : MultiplicationTableState()
}

/**
 * 구구단 관련 이벤트
 */
sealed class MultiplicationEvent {
    data class SelectMultiplier(val multiplier: Int) : MultiplicationEvent()
    data object LoadAllTables : MultiplicationEvent()
    data object Reset : MultiplicationEvent()
}
