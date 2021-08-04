package com.jwd.service.impl;

import com.jwd.ecxeption.CalculatorValidatorException;
import com.jwd.entity.ParameterSet;
import com.jwd.service.Calculator;
import com.jwd.validator.CalculatorParameterValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorImpl implements Calculator {

    private final CalculatorParameterValidator validator = new CalculatorParameterValidator();

    @Override
    public BigDecimal sum(ParameterSet parameterSet) throws CalculatorValidatorException {
        validator.validateParametersIsNull(parameterSet);
        return parameterSet.getX().add(parameterSet.getY());
    }

    @Override
    public BigDecimal subtract(ParameterSet parameterSet) throws CalculatorValidatorException {
        validator.validateParametersIsNull(parameterSet);
        return parameterSet.getX().subtract(parameterSet.getY());
    }

    @Override
    public BigDecimal multiply(ParameterSet parameterSet) throws CalculatorValidatorException {
        validator.validateParametersIsNull(parameterSet);
        return parameterSet.getX().multiply(parameterSet.getY());
    }

    @Override
    public BigDecimal divide(ParameterSet parameterSet) throws CalculatorValidatorException {
        validator.validateDivisionParameters(parameterSet);
        return parameterSet.getX().divide(parameterSet.getY(), 2, RoundingMode.UP);
    }
}
