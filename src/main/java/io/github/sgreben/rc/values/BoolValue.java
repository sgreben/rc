package io.github.sgreben.rc.values;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public class BoolValue extends Value {
    private final Context z3Context;
    private final boolean value;

    public BoolValue(Context context, boolean value) {
        this.z3Context = context;
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoolValue that = (BoolValue) o;

        return value == that.value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public int hashCode() {
        return value ? 0 : 1;
    }

    @Override
    public Expr toZ3Expr() {
        return z3Context.mkBool(value);
    }
}
