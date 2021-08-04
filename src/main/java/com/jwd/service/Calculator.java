package com.jwd.service;

import com.jwd.ecxeption.CalculatorValidatorException;
import com.jwd.entity.ParameterSet;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal sum(final ParameterSet parameterSet) throws CalculatorValidatorException;
    BigDecimal subtract(final ParameterSet parameterSet) throws CalculatorValidatorException;
    BigDecimal multiply(final ParameterSet parameterSet) throws CalculatorValidatorException;
    BigDecimal divide(final ParameterSet parameterSet) throws CalculatorValidatorException;
}
