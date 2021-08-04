package com.jwd;

import com.jwd.console.CalculatorConsoleApplication;
import com.jwd.service.Calculator;
import com.jwd.service.impl.CalculatorImpl;

public class Main {
    public static void main(String[] args) {
        final Calculator calculator = new CalculatorImpl();
        final CalculatorConsoleApplication app = new CalculatorConsoleApplication(calculator);
        app.start();
    }
}
