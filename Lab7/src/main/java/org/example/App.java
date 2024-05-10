package org.example;

import org.example.service.GeneratorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String CONFIGURATION_PATH = "context.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> strings = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            System.out.println("Input String:");
            strings.add(scanner.nextLine());
        }
        ApplicationContext context = new ClassPathXmlApplicationContext(CONFIGURATION_PATH);

        GeneratorService service = (GeneratorService) context.getBean("generatorService");

        List<String> generated = service.generateStrings(strings);

        for (String s : generated) {
            System.out.println(s);
        }
    }

}
