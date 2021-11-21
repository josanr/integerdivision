package ru.josanr.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        var division = new LongDivision(19845, 16);
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

        var division = new LongDivision(487, 32);
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

        var division = new LongDivision(10240012, 8);
        assertEquals(expected, printer.print(division));
    }
}
