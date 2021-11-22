package ru.josanr.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.josanr.integerdivision.division.DivisionStage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DivisionPrinterTest {

    private DivisionPrinter printer;

    @BeforeEach
    void setUp() {
        this.printer = new DivisionPrinter();
    }

    @Test
    void print_shouldReturnExpectedString_For19845DividedBy16() {
        String expected = """
            _19845|16
             16   |--
             --   |1240
             _38
              32
              --
              _64
               64
               --
                 5
            """;

        var division = mock(LongDivision.class);
        when(division.getDividend()).thenReturn(19845);
        when(division.getDivisor()).thenReturn(16);
        when(division.getQuotient()).thenReturn(1240);
        when(division.getRemainder()).thenReturn(5);
        when(division.getStages()).thenReturn(List.of(
            new DivisionStage(19, 16, 1),
            new DivisionStage(38, 32, 2),
            new DivisionStage(64, 64, 3)
        ));
        assertEquals(expected, printer.print(division));
    }

    @Test
    void print_shouldReturnExpectedString_For487DividedBy32() {
        String expected = """
            _487|32
             32 |--
             -- |15
            _167
             160
             ---
               7
            """;

        var division = mock(LongDivision.class);
        when(division.getDividend()).thenReturn(487);
        when(division.getDivisor()).thenReturn(32);
        when(division.getQuotient()).thenReturn(15);
        when(division.getRemainder()).thenReturn(7);
        when(division.getStages()).thenReturn(List.of(
            new DivisionStage(48, 32, 1),
            new DivisionStage(167, 160, 2)
        ));
        assertEquals(expected, printer.print(division));
    }

    @Test
    void print_shouldAlignCorrectly_SecondNumberShorterThanFirst() {
        String expected = """
            _10240012|8
              8       |-
              -       |1280001
             _22
              16
              --
              _64
               64
               --
                  _12
                    8
                    --
                    4
            """;

        var division = mock(LongDivision.class);
        when(division.getDividend()).thenReturn(10240012);
        when(division.getDivisor()).thenReturn(8);
        when(division.getQuotient()).thenReturn(1280001);
        when(division.getRemainder()).thenReturn(4);
        when(division.getStages()).thenReturn(List.of(
            new DivisionStage(10, 8, 1),
            new DivisionStage(22, 16, 2),
            new DivisionStage(64, 64, 3),
            new DivisionStage(12, 8, 7)
        ));
        assertEquals(expected, printer.print(division));
    }
}
