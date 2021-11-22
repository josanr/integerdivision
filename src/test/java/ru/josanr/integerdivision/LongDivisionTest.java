package ru.josanr.integerdivision;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.josanr.integerdivision.division.DivisionStage;
import ru.josanr.integerdivision.division.LongDivisionImpl;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"java:S2699"})
class LongDivisionTest {

    private static Stream<Arguments> provideDivisionParameters() {
        return Stream.of(
            Arguments.of(10, 5, 2, 0),
            Arguments.of(35, 5, 7, 0),
            Arguments.of(120, 60, 2, 0),
            Arguments.of(16, 2, 8, 0),
            Arguments.of(15, 2, 7, 1),
            Arguments.of(99, 10, 9, 9),
            Arguments.of(99, 9, 11, 0),
            Arguments.of(-99, -9, 11, 0),
            Arguments.of(16, -2, 8, 0),
            Arguments.of(-35, 5, 7, 0)
        );
    }

    private static Stream<Arguments> provideDivisionParamsWithStages() {
        return Stream.of(
            Arguments.of(487, 32, List.of(
                new DivisionStage(48, 32, 1),
                new DivisionStage(167, 160, 2)
            )),
            Arguments.of(19452, 65, List.of(
                new DivisionStage(194, 130, 2),
                new DivisionStage(645, 585, 3),
                new DivisionStage(602, 585, 4)
            )),
            Arguments.of(2048, 8, List.of(
                new DivisionStage(20, 16, 1),
                new DivisionStage(44, 40, 2),
                new DivisionStage(48, 48, 3)
            )),
            Arguments.of(10240012, 8, List.of(
                new DivisionStage(10, 8, 1),
                new DivisionStage(22, 16, 2),
                new DivisionStage(64, 64, 3),
                new DivisionStage(12, 8, 7)
            ))

        );
    }

    @Test
    void construct_shouldThrowException_dividerIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new LongDivisionImpl(10, 0));
    }

    @Test
    void construct_shouldThrowException_dividerIsBiggerThanDividend() {
        assertThrows(IllegalArgumentException.class, () -> new LongDivisionImpl(10, 1000));
    }

    @ParameterizedTest
    @MethodSource("provideDivisionParameters")
    void getQuotient_shouldReturnDivisionResult(int dividend, int divisor, int expectedQuotient) {
        var divider = new LongDivisionImpl(dividend, divisor);
        assertEquals(expectedQuotient, divider.getQuotient());
    }

    @ParameterizedTest
    @MethodSource("provideDivisionParameters")
    void getRemainder_shouldReturnDivisionRest(int dividend, int divisor, int expectedQuotient, int expectedRest) {
        var divider = new LongDivisionImpl(dividend, divisor);
        assertEquals(expectedRest, divider.getRemainder());
    }

    @ParameterizedTest
    @MethodSource("provideDivisionParamsWithStages")
    void getStages_shouldReturnDivisionStagesForPrint(int dividend, int divisor, List<DivisionStage> expectedStages) {
        var divider = new LongDivisionImpl(dividend, divisor);
        List<DivisionStage> stages = divider.getStages();
        assertEquals(expectedStages, stages);
    }
}
