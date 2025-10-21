package kr.jell.android_study.domain.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * MultiplicationRepository Unit Test
 * Coroutines Test를 사용한 비동기 테스트
 */
class MultiplicationRepositoryTest {

    private lateinit var repository: MultiplicationRepository

    @Before
    fun setup() {
        repository = MultiplicationRepositoryImpl()
    }

    @Test
    fun `getMultiplicationTable은 올바른 구구단을 반환해야 한다`() = runTest {
        // Given
        val multiplier = 3

        // When
        val result = repository.getMultiplicationTable(multiplier).first()

        // Then
        assertThat(result).hasSize(9)
        assertThat(result.first().multiplier).isEqualTo(3)
        assertThat(result.first().result).isEqualTo(3)
        assertThat(result.last().result).isEqualTo(27)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `getMultiplicationTable은 잘못된 입력에 대해 예외를 던져야 한다`() = runTest {
        // Given
        val invalidMultiplier = 10

        // When & Then
        repository.getMultiplicationTable(invalidMultiplier).first()
    }

    @Test
    fun `getAllMultiplicationTables는 모든 구구단을 반환해야 한다`() = runTest {
        // When
        val result = repository.getAllMultiplicationTables().first()

        // Then
        assertThat(result).hasSize(8)
        assertThat(result.keys).containsExactly(2, 3, 4, 5, 6, 7, 8, 9)

        result.forEach { (multiplier, tables) ->
            assertThat(tables).hasSize(9)
            assertThat(tables.all { it.multiplier == multiplier }).isTrue()
        }
    }

    @Test
    fun `2단의 첫 번째 값은 2이어야 한다`() = runTest {
        // When
        val result = repository.getMultiplicationTable(2).first()

        // Then
        assertThat(result.first().toDisplayString()).isEqualTo("2 x 1 = 2")
    }

    @Test
    fun `9단의 마지막 값은 81이어야 한다`() = runTest {
        // When
        val result = repository.getMultiplicationTable(9).first()

        // Then
        assertThat(result.last().toDisplayString()).isEqualTo("9 x 9 = 81")
    }
}
