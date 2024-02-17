package org.jimmydev.view;

import org.jimmydev.controller.Controller;
import org.jimmydev.dto.impl.complex.RequestComplex;
import org.jimmydev.dto.impl.complex.ResponseComplex;
import org.jimmydev.entity.ComplexExpression;
import org.jimmydev.util.RequestType;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ComplexExpressionResolverView {
    static Logger LOGGER;

    static {
        File logFolder = new File("log");
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }

        try {
            LogManager.getLogManager()
                    .readConfiguration(ComplexExpression.class.getClassLoader()
                            .getResourceAsStream("logger.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER = Logger.getLogger(ComplexExpressionResolverView.class.getName());

    }

    Controller controller = Controller.getInstance();
    ResponseComplex response;

    public void start() {
        LOGGER.log(INFO, "Run program");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Enter expression for example 3+4i-15+3i or -3+4i+(-15+3i) or type "exit."
                    Supported operations: + - * /
                    """);
            String input = scanner.nextLine();

            LOGGER.log(INFO, String.format("User enter an expression %s", input));

            response = controller.request(new RequestComplex(input, RequestType.COMPLEX_EXPRESSION));

            switch (response.getState()) {
                case STOP -> {
                    LOGGER.log(INFO, "User stop the Application");

                    return;
                }
                case OK -> {
                    System.out.printf("Result: %s\n\n", response.getComplexNumber());
                    LOGGER.log(INFO, String.format("Result of the expression is %s", response.getComplexNumber()));
                }
                case FAIL -> {
                    System.out.println("Wrong format of the expression!\n");
                    LOGGER.log(INFO, "Wrong format of the expression!");
                }
            }

        }
    }
}
