package ru.josanr.integerdivision;

import java.util.List;

public interface LongDivisionInterface {
    int getQuotient();

    int getRemainder();

    int getDividend();

    int getDivisor();

    List<DivisionStage> getStages();
}
