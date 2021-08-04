package com.jwd.console;

import com.jwd.exception.CalculatorConsoleApplicationException;
import com.jwd.exception.CalculatorValidatorException;
import com.jwd.entity.ParameterSet;
import com.jwd.service.Calculator;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class CalculatorConsoleApplication {

    public static final String WELCOME_MESSAGE = "Press:" +
            "\n 0 - Exit" +
            "\n 1 - Sum" +
            "\n 2 - Diff" +
            "\n 3 - Mult" +
            "\n 4 - Div";
    public static final int EXIT = 0, SUM = 1, DIFFERENCE = 2, MULTIPLICATION = 3, DIVISION = 4, RESTART_VALUE = 999;
    public static final String DELIMITER = "\n==============================================\n";

    private final Calculator calculator;
    private final Scanner scanner;
    {
        // это блок инициализации, забыл вам про него рассказать - напомните
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // поменяем формат ввода double чисел на 1.3
    }
    private Closeable[] closeables;

    public CalculatorConsoleApplication(final Calculator calculator) {
        this.calculator = calculator;
        this.closeables = new Closeable[]{scanner};
    }

    public void start() {
        processMenu();
        cleanUpCloseables();
    }

    private void processMenu() {
        // никто не поправил! Название Булевой переменной было runFlag - что противоречит Naming Convention
        boolean isRunning = true;
        while (isRunning) {
            printConsole(WELCOME_MESSAGE);
            printConsole(DELIMITER);
            int consoleChoice = getConsoleChoice();
            switch (consoleChoice) {
                case EXIT:
                    isRunning = false;
                    printConsole("App closes.");
                    break;
                case SUM:
                    findSum();
                    break;
                case DIFFERENCE:
                    findDifference();
                    break;
                case MULTIPLICATION:
                    findMultiplication();
                    break;
                case DIVISION:
                    findDivision();
                    break;
                default:
                    printConsole("Invalid choice. Restarting app." + DELIMITER);
            }
        }
    }

    private int getConsoleChoice() {
        int choice;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            cleanScannerNextEnteredValue();
            choice = RESTART_VALUE;
        }
        return choice;
    }

    private void cleanScannerNextEnteredValue() {
        // даже если Int/Double не был введен, scanner хранит что-то -
        // нужно избавиться от этого, чтобы он продолжил работать корректно
        // для этого заберем следующее значение и т.о. очистим сканер.
        final String next = scanner.next();
        printConsole("Entered next=[" + next + "].");
    }

    private void findSum() {
        try {
            printConsole("Sum is chosen.");
            final ParameterSet parameterSet = prepareParameterSet(true);
            printConsole("Result is [" + calculator.sum(parameterSet) + "]." + DELIMITER);
            // ловим/гасим ошибку, выводим сообщение, которое она хванит.
        } catch (CalculatorConsoleApplicationException | CalculatorValidatorException e) {
            printCaughtException(e);
        }
    }

    private void findDifference() {
        try {
            printConsole("Difference is chosen.");
            final ParameterSet parameterSet = prepareParameterSet(true);
            printConsole("Result is [" + calculator.subtract(parameterSet) + "]." + DELIMITER);
            // ловим/гасим ошибку, выводим сообщение, которое она хванит.
        } catch (CalculatorConsoleApplicationException | CalculatorValidatorException e) {
            printCaughtException(e);
        }
    }

    private void findMultiplication() {
        try {
            printConsole("Multiplication is chosen.");
            final ParameterSet parameterSet = prepareParameterSet(true);
            printConsole("Result is [" + calculator.multiply(parameterSet) + "]." + DELIMITER);
            // ловим/гасим ошибку, выводим сообщение, которое она хванит.
        } catch (CalculatorConsoleApplicationException | CalculatorValidatorException e) {
            printCaughtException(e);
        }
    }

    private void findDivision() {
        try {
            printConsole("Division is chosen.");
            final ParameterSet parameterSet = prepareParameterSet(true);
            printConsole("Result is [" + calculator.divide(parameterSet) + "]." + DELIMITER);
            // ловим/гасим ошибку, выводим сообщение, которое она хванит.
        } catch (CalculatorConsoleApplicationException | CalculatorValidatorException e) {
            printCaughtException(e);
        }
    }

    private ParameterSet prepareParameterSet(final boolean hasSecondParameter) throws CalculatorConsoleApplicationException {
        printConsole("Enter a number: (e.g.: 5; 4.3)");
        BigDecimal parameterX = getDoubleParameter();
        BigDecimal parameterY = toBigDecimal(0);
        if (hasSecondParameter) {
            printConsole("Enter a number: (e.g.: 5; 4.3)");
            parameterY = getDoubleParameter();
        }
        return new ParameterSet(parameterX, parameterY);
    }

    private BigDecimal toBigDecimal(final double value) {
        return BigDecimal.valueOf(value);
    }

    private BigDecimal getDoubleParameter() throws CalculatorConsoleApplicationException {
        double parameter;
        if (scanner.hasNextDouble()) {
            parameter = scanner.nextDouble();
        } else {
            cleanScannerNextEnteredValue();
            // передаем сообщение, которое будет выведено в после того, как мы отловим и погасим ошибку
            throw new CalculatorConsoleApplicationException("Exception: entered parameter is not of type Double.");
        }
        return toBigDecimal(parameter);
    }

    private void printConsole(final String message) {
        System.out.println(message);
    }

    private void printCaughtException(Exception e) {
        // здесь e.getMessage() возвращает сообщение, которое мы оставили, когда выбрасывали свою ошибку
        printConsole("Exception: Parameters passed to Calculator is invalid. Exception message is " + e.getMessage());
    }

    // поскольку scanner работает с подключением к консоли - правильно закрывать такое
    // подключение перед окончанием работы объекта нашего класса
    private void cleanUpCloseables() {
        for (final Closeable closeable : closeables) {
            try {
                closeable.close();
                System.out.println("Resource is closed, " + closeable.getClass());

            } catch (final IOException e) {
                System.out.println("Something went wrong during closing " + closeable.getClass());
                e.printStackTrace();
            }
        }
    }
}
