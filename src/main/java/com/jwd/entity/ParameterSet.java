package com.jwd.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ParameterSet {
    private final BigDecimal x;
    private final BigDecimal y;

    public ParameterSet(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterSet that = (ParameterSet) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "ParameterSet{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
