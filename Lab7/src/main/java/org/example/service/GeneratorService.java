package org.example.service;

import org.example.generator.StringGenerator;

import java.util.ArrayList;
import java.util.List;

public class GeneratorService {

    private StringGenerator stringGenerator;

    private int stringsCount;
    private int length;

    // Default constructor is needed if dependency injection is going to be performed via setters
    public GeneratorService() {
    }

    public GeneratorService(StringGenerator stringGenerator, int stringsCount, int length) {
        this.stringGenerator = stringGenerator;
        this.stringsCount = stringsCount;
        this.length = length;
    }

    public List<String> generateStrings(List<String> input_s) {
        input_s = stringGenerator.generate(input_s);
        return input_s;
    }

    public StringGenerator getStringGenerator() {
        return stringGenerator;
    }

    public int getStringsCount() {
        return stringsCount;
    }

    public void setStringGenerator(StringGenerator stringGenerator) {
        this.stringGenerator = stringGenerator;
    }

    public void setStringsCount(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

