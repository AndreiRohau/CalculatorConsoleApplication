package com.jwd.validator;

import com.jwd.ecxeption.CalculatorValidatorException;
import com.jwd.entity.ParameterSet;

import java.math.BigDecimal;

public class CalculatorParameterValidator {

    public void validateParametersIsNull(ParameterSet parameterSet) throws CalculatorValidatorException {
        if (parameterSet == null) {
            throw new CalculatorValidatorException("Calculator Validator Exception: ParameterSet == null.");
        }
    }

    public void validateDivisionParameters(ParameterSet parameterSet) throws CalculatorValidatorException {
        if (parameterSet == null || parameterSet.getY().equals(BigDecimal.valueOf(0))) {
            throw new CalculatorValidatorException("Calculator Validator Exception: throws exception in method validateDivisionParameters");
        }
    }
}
