package kr.jell.android_study.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * MultiplicationTable 모델 Unit Test
 * TDD 방식으로 작성된 테스트
 */
class MultiplicationTableTest {

    @Test
    fun `곱셈 결과가 정확해야 한다`() {
        // Given
        val multiplier = 2
        val multiplicand = 3

        // When
        val table = MultiplicationTable(multiplier, multiplicand)

        // Then
        assertThat(table.result).isEqualTo(6)
    }

    @Test
    fun `toDisplayString은 올바른 형식을 반환해야 한다`() {
        // Given
        val table = MultiplicationTable(2, 3)

        // When
        val displayString = table.toDisplayString()

        // Then
        assertThat(displayString).isEqualTo("2 x 3 = 6")
    }

    @Test
    fun `generateTable은 1부터 9까지의 구구단을 생성해야 한다`() {
        // Given
        val multiplier = 5

        // When
        val tables = MultiplicationTable.generateTable(multiplier)

        // Then
        assertThat(tables).hasSize(9)
        assertThat(tables.first().multiplicand).isEqualTo(1)
        assertThat(tables.last().multiplicand).isEqualTo(9)
        assertThat(tables.first().result).isEqualTo(5)
        assertThat(tables.last().result).isEqualTo(45)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateTable은 2보다 작은 수에 대해 예외를 던져야 한다`() {
        // When & Then
        MultiplicationTable.generateTable(1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateTable은 9보다 큰 수에 대해 예외를 던져야 한다`() {
        // When & Then
        MultiplicationTable.generateTable(10)
    }

    @Test
    fun `generateAllTables는 2단부터 9단까지 생성해야 한다`() {
        // When
        val allTables = MultiplicationTable.generateAllTables()

        // Then
        assertThat(allTables).hasSize(8)
        assertThat(allTables.keys).containsExactly(2, 3, 4, 5, 6, 7, 8, 9)
        allTables.forEach { (multiplier, tables) ->
            assertThat(tables).hasSize(9)
            assertThat(tables.first().multiplier).isEqualTo(multiplier)
        }
    }

    @Test
    fun `곱셈 결과가 자동으로 계산되어야 한다`() {
        // Given & When
        val table = MultiplicationTable(7, 8)

        // Then
        assertThat(table.result).isEqualTo(56)
    }

    @Test
    fun `같은 입력값으로 생성된 객체는 동일해야 한다`() {
        // Given
        val table1 = MultiplicationTable(3, 4)
        val table2 = MultiplicationTable(3, 4)

        // Then
        assertThat(table1).isEqualTo(table2)
    }
}
