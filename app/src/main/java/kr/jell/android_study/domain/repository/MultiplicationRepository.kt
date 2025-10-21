package kr.jell.android_study.domain.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.jell.android_study.domain.model.MultiplicationTable

/**
 * 구구단 데이터 Repository 인터페이스
 */
interface MultiplicationRepository {
    /**
     * 특정 단의 구구단을 Flow로 반환
     */
    fun getMultiplicationTable(multiplier: Int): Flow<List<MultiplicationTable>>

    /**
     * 전체 구구단을 Flow로 반환
     */
    fun getAllMultiplicationTables(): Flow<Map<Int, List<MultiplicationTable>>>
}

/**
 * 구구단 Repository 구현체
 * 실제 앱에서는 데이터베이스나 네트워크에서 가져올 수 있음
 */
class MultiplicationRepositoryImpl : MultiplicationRepository {

    override fun getMultiplicationTable(multiplier: Int): Flow<List<MultiplicationTable>> = flow {
        // 네트워크 호출이나 DB 조회를 시뮬레이션
        delay(300)

        if (multiplier !in 2..9) {
            throw IllegalArgumentException("Multiplier must be between 2 and 9")
        }

        emit(MultiplicationTable.generateTable(multiplier))
    }

    override fun getAllMultiplicationTables(): Flow<Map<Int, List<MultiplicationTable>>> = flow {
        // 네트워크 호출이나 DB 조회를 시뮬레이션
        delay(500)
        emit(MultiplicationTable.generateAllTables())
    }
}
