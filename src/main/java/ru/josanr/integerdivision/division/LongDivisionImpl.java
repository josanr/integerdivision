package ru.josanr.integerdivision.division;

import ru.josanr.integerdivision.LongDivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionImpl implements LongDivision {

    private final int dividend;
    private final int divisor;
    private final int quotient;
    private final int remainder;
    private final ArrayList<DivisionStage> stageList;

    public LongDivisionImpl(int dividend, int divisor) {

        this.dividend = dividend;
        this.divisor = divisor;

        if (this.divisor == 0) {
            throw new IllegalArgumentException("Divisor can't be 0");
        }

        if (Math.abs(this.dividend) < Math.abs(this.divisor)) {
            throw new IllegalArgumentException("Dividend can't be less than divisor");
        }

        this.quotient = this.dividend / this.divisor;
        this.remainder = this.dividend % this.divisor;
        stageList = new ArrayList<>();
        divide();
    }

    @Override
    public int getQuotient() {
        return quotient;
    }

    @Override
    public int getRemainder() {
        return remainder;
    }

    @Override
    public int getDividend() {
        return dividend;
    }

    @Override
    public int getDivisor() {
        return divisor;
    }

    @Override
    public List<DivisionStage> getStages() {
        return stageList;
    }

    private void divide() {
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        StringBuilder remainderString = new StringBuilder();
        int dividedPart;
        int partResult;
        int mod;
        int positiveDivisor = Math.abs(this.divisor);
        for (int index = 0; index < digits.length; index++) {
            remainderString.append(digits[index]);
            dividedPart = Integer.parseInt(remainderString.toString());

            if (dividedPart >= positiveDivisor) {
                mod = dividedPart % positiveDivisor;
                partResult = dividedPart / positiveDivisor * positiveDivisor;
                stageList.add(new DivisionStage(dividedPart, partResult, index));
                remainderString.replace(0, remainderString.length(), Integer.toString(mod));
            }
        }
    }
}
