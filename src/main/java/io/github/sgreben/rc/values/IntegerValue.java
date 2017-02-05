package io.github.sgreben.rc.values;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import io.github.sgreben.rc.Variable;

import java.util.Collections;
import java.util.Set;

public class IntegerValue extends Value {
    private final Context z3Context;
    private final int value;

    public int getValue() {
        return value;
    }

    public IntegerValue(Context context, int value) {
        this.z3Context = context;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerValue that = (IntegerValue) o;

        return value == that.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public Expr toZ3Expr() {
        return z3Context.mkInt(value);
    }
}
