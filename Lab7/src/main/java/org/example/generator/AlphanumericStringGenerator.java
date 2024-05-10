package org.example.generator;

import java.util.List;
import java.util.Random;

public class AlphanumericStringGenerator implements StringGenerator {

    private static final Random RANDOM = new Random();

    private String source;

    public AlphanumericStringGenerator(String source) {
        this.source = source;
    }

    @Override
    public List<String> generate(List<String> input_s) {
       /* if (source == null || source.isEmpty()) {
            return EMPTY_STRING;
        }
        if (count < 0) {
            throw new IllegalArgumentException("Length of generated string must be positive.");
        }
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < count; index++) {
            int sourceIndex = RANDOM.nextInt(source.length());
            result.append(source.charAt(sourceIndex));
        }*/
        return input_s;
    }
}

