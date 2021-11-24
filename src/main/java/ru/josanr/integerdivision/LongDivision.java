package ru.josanr.integerdivision;

import ru.josanr.integerdivision.division.DivisionStage;

import java.util.List;

public interface LongDivision {
    int getQuotient();

    int getRemainder();

    int getDividend();

    int getDivisor();

    List<DivisionStage> getStages();
}
