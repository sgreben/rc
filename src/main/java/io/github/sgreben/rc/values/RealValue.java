package io.github.sgreben.rc.values;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public class RealValue extends Value {
    private final Context z3Context;
    private final double value;

    public RealValue(Context context, double value) {
        this.z3Context = context;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealValue that = (RealValue) o;

        return value == that.value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public Expr toZ3Expr() {
        return z3Context.mkReal(Double.toString(value));
    }
}
