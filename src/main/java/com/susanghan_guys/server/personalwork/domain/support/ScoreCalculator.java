package com.susanghan_guys.server.personalwork.domain.support;

import com.susanghan_guys.server.personalwork.domain.Evaluation;

import java.util.List;

public class ScoreCalculator {

    public static int calculateTotalScore(List<Evaluation> evaluations) {
        double total = evaluations.stream()
                .mapToDouble(Evaluation::getScore)
                .map(score -> score * 5)
                .sum();

        return (int) Math.round((total / 250.0) * 100.0);
    }
}
