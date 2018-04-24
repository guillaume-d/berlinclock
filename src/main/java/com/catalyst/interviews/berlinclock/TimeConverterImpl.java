package com.catalyst.interviews.berlinclock;

import java.util.Arrays;

public class TimeConverterImpl implements TimeConverter {

    public String convertTime(String time) {
        String[] time_parts = time.split(":");

        int hours = Integer.parseInt(time_parts[0]);
        int minutes = Integer.parseInt(time_parts[1]);
        int seconds = Integer.parseInt(time_parts[2]);

        StringBuilder sb = new StringBuilder();

        System.err.println(seconds);
        sb.append((seconds % 2 == 0 ? "Y" : "O") + "\n");

        System.err.println(hours);
        sb.append(base1digits('R', hours / 5, 4) + "\n");
        sb.append(base1digits('R', hours % 5, 4) + "\n");

        System.err.println(minutes);
        sb.append(red_if_on_at(base1digits('Y', minutes / 5, 11), 3, 6, 9) + "\n");
        sb.append(base1digits('Y', minutes % 5, 4));

        return sb.toString();
    }

    private String red_if_on_at(String unary_digits_text, int... ordinals) {
        char[] unary_digits = unary_digits_text.toCharArray();
        char[] new_unary_digits = Arrays.copyOf(unary_digits, unary_digits.length);
        for (int ordinal : ordinals) {
            int i = ordinal - 1;
            new_unary_digits[i] = unary_digits[i] != 'O' ?
                'R' :
                unary_digits[i];
        }
        return new String(new_unary_digits);
    }
    private String base1digits(char on, int n, int max) {
        char[] unary_digits = new char[max];
        Arrays.fill(unary_digits, 'O');
        for (int i = 0; i < n; i++) {
            unary_digits[i] = on;
        }
        return new String(unary_digits);
    }

}
/* vim: set tabstop=4:softtabstop=4:shiftwidth=4:noexpandtab */
