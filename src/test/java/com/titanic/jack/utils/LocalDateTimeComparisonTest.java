package com.titanic.jack.utils;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeComparisonTest {

    @Test
    void isMoreThanThreeWeekDaysApart() {
        LocalDateTime createdAt1 = LocalDateTime.of(2023, 3, 21, 0, 0); // 화
        LocalDateTime createdAt2 = LocalDateTime.of(2023, 3, 23, 0, 0); // 목
        LocalDateTime now = LocalDateTime.of(2023, 3, 27, 0, 0);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(LocalDateTimeComparison.isMoreThanThreeWeekdaysApart(createdAt1, now)).isTrue();
        softAssertions.assertThat(LocalDateTimeComparison.isMoreThanThreeWeekdaysApart(createdAt2, now)).isFalse();
        softAssertions.assertAll();
    }
}