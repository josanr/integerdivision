package ru.josanr.integerdivision;

import ru.josanr.integerdivision.division.DivisionStage;

public class DivisionPrinter {

    private static final int MINUS_SIGN_OFFSET = 1;

    public String print(LongDivision division) {
        var output = new StringBuilder();
        int leftOffset = 1;
        int dividend = division.getDividend();
        int divisor = division.getDivisor();
        int dividendDigitCount = calculateDigitCount(Math.abs(dividend));
        if (dividend < 0) {
            leftOffset = 2;
        }
        var stages = division.getStages();
        output.append("_").append(dividend)
            .append("|")
            .append(divisor)
            .append("\n");

        DivisionStage firstStage = stages.get(0);
        int firstStageSecondDigitCount = calculateDigitCount(Math.abs(firstStage.secondNumber()));
        int firstStageFirstDigitCount = calculateDigitCount(Math.abs(firstStage.firstNumber()));
        int divisorDigitCount = calculateDigitCount(Math.abs(divisor));

        output.append(" ".repeat(leftOffset))
            .append(" ".repeat(firstStageFirstDigitCount - firstStageSecondDigitCount))
            .append(firstStage.secondNumber())
            .append(" ".repeat(dividendDigitCount - firstStageSecondDigitCount))
            .append("|")
            .append("-".repeat(divisorDigitCount))
            .append("\n");

        output.append(" ".repeat(leftOffset))
            .append(" ".repeat(firstStageFirstDigitCount - firstStageSecondDigitCount))
            .append("-".repeat(firstStageSecondDigitCount))
            .append(" ".repeat(dividendDigitCount - firstStageSecondDigitCount))
            .append("|")
            .append(division.getQuotient())
            .append("\n")
        ;

        for (var index = 1; index < stages.size(); index++) {
            var stage = stages.get(index);
            var offsetFirst = stage.offset() - calculateDigitCount(stage.firstNumber()) + leftOffset;
            var offsetSecond = stage.offset() - calculateDigitCount(stage.secondNumber()) + leftOffset + MINUS_SIGN_OFFSET;
            output.append(" ".repeat(offsetFirst))
                .append("_")
                .append(stage.firstNumber()).append("\n")
                .append(" ".repeat(offsetSecond))
                .append(stage.secondNumber()).append("\n")
                .append(" ".repeat(offsetSecond))
                .append("-".repeat(calculateDigitCount(stage.firstNumber()))).append("\n");
        }

        int rightOffset = 0;
        if (division.getRemainder() < 0) {
            rightOffset = 1;
        }
        int repeatCount = dividendDigitCount - calculateDigitCount(Math.abs(division.getRemainder())) - rightOffset;
        output.append(" ".repeat(leftOffset))
            .append(" ".repeat(repeatCount))
            .append(division.getRemainder())
            .append("\n");


        return output.toString();
    }

    private int calculateDigitCount(int number) {

        if (number == 0) {
            return 1;
        }
        return (int) Math.log10(number) + 1;

    }
}
