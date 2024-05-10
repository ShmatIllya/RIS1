package org.example.generator;

import java.util.List;

public interface StringGenerator {

    String EMPTY_STRING = "";

    /**
     * Generates string with random characters.
     *
     * @param input_s result string length
     * @return generated string
     */
    List<String> generate(List<String> input_s);
}
