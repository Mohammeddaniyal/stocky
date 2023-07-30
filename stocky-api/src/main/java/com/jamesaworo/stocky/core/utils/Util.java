/*
 * @Author: james.junior
 * @Date: 7/3/23 18:10
 *
 * @Project: stocky-api
 */

package com.jamesaworo.stocky.core.utils;

import com.jamesaworo.stocky.configuration.converter.LocalDateStringConverter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class Util {

    public static LocalDate parseToLocalDate(String date) {
        LocalDateStringConverter converter = new LocalDateStringConverter();
        return converter.convert(date == null ? LocalDate.now().toString() : date);
    }

    public static String randomAlphanumeric(Integer count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String receiptToken(Integer count) {
        String prefix = randomString(2);
        return String.format("%s%s", prefix, randomNumeric(count));
    }

    public static String randomString(Integer count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String randomNumeric(Integer count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static String formatDate(LocalDate date) {
        return isNotEmpty(date) ? date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) : "";
    }

    public static String formatTime(LocalTime time) {
        return isNotEmpty(time) ? time.format(DateTimeFormatter.ofPattern("KK:mm:ss a", Locale.ENGLISH)) : "";
    }

    public static String formatAmount(Double amount) {
        return String.format("%,.2f", amount);
    }


}
