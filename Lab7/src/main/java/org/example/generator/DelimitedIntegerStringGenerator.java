package org.example.generator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DelimitedIntegerStringGenerator implements StringGenerator {

    private static final Random RANDOM = new Random();

    private String delimiter;
    private int endInclusive;

    public DelimitedIntegerStringGenerator(String delimiter, int endInclusive) {
        this.delimiter = delimiter;
        this.endInclusive = endInclusive;
    }

    @Override
    public List<String> generate(List<String> input_s) {
        if (endInclusive <= 0) {
            throw new IllegalArgumentException("Property endInclusive must be positive integer.");
        }
        /*if (count < 0) {
            throw new IllegalArgumentException("Length of generated string must be positive.");
        }
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < count; index++) {
            result.append(RANDOM.nextInt(endInclusive)).append(delimiter);
        }
        return result.deleteCharAt(count - 1).toString();*/
        Collections.sort(input_s);
        return input_s;
    }
}

