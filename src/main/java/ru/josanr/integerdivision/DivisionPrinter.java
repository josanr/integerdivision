package ru.josanr.integerdivision;

public class DivisionPrinter {

    private static final int START_OFFSET = 1;
    private static final int MINUS_SIGN_OFFSET = 1;

    public String print(LongDivisionInterface division) {
        var output = new StringBuilder();

        int dividend = division.getDividend();
        int divisor = division.getDivisor();
        int dividendDigitCount = calculateDigitCount(dividend);
        var stages = division.getStages();
        output.append("_").append(dividend)
            .append("|")
            .append(divisor)
            .append("\n");

        DivisionStage firstStage = stages.get(0);
        int firstStageSecondDigitCount = calculateDigitCount(firstStage.secondNumber());
        int firstStageFirstDigitCount = calculateDigitCount(firstStage.firstNumber());
        int divisorDigitCount = calculateDigitCount(divisor);

        output.append(" ")
            .append(" ".repeat(firstStageFirstDigitCount - firstStageSecondDigitCount))
            .append(firstStage.secondNumber())
            .append(" ".repeat(dividendDigitCount - firstStageSecondDigitCount))
            .append("|")
            .append("-".repeat(divisorDigitCount))
            .append("\n");

        output.append(" ")
            .append(" ".repeat(firstStageFirstDigitCount - firstStageSecondDigitCount))
            .append("-".repeat(firstStageSecondDigitCount))
            .append(" ".repeat(dividendDigitCount - firstStageSecondDigitCount))
            .append("|")
            .append(division.getQuotient())
            .append("\n")
        ;

        for(var index = 1; index < stages.size(); index++) {
            var stage = stages.get(index);
            var offsetFirst = stage.offset() - calculateDigitCount(stage.firstNumber()) + START_OFFSET;
            var offsetSecond = stage.offset() - calculateDigitCount(stage.secondNumber())  + START_OFFSET + MINUS_SIGN_OFFSET;
            output.append(" ".repeat(offsetFirst))
                .append("_")
                .append(stage.firstNumber()).append("\n")
                .append(" ".repeat(offsetSecond))
                .append(stage.secondNumber()).append("\n")
                .append(" ".repeat(offsetSecond))
                .append("-".repeat(calculateDigitCount(stage.firstNumber()))).append("\n");
        }

        output.append(" ")
            .append(" ".repeat(dividendDigitCount - calculateDigitCount(division.getRemainder())))
            .append(division.getRemainder())
            .append("\n");



        return output.toString();
    }

    private int calculateDigitCount(int number) {

        if(number == 0) {
            return 1;
        }
        return (int) Math.log10(number) + 1;

    }
}
