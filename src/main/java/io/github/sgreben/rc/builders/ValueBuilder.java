package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.TypeEnvironment;
import io.github.sgreben.rc.values.BoolValue;
import io.github.sgreben.rc.values.EnumValue;
import io.github.sgreben.rc.values.IntegerValue;
import io.github.sgreben.rc.values.RealValue;

public class ValueBuilder {
    private final TypeEnvironment typeEnvironment;
    private final Context z3Context;

    public ValueBuilder(TypeEnvironment typeEnvironment, Context z3Context) {
        this.typeEnvironment = typeEnvironment;
        this.z3Context = z3Context;
    }

    public IntegerValue integer(int value) {
        return new IntegerValue(z3Context, value);
    }

    public EnumValue enumeration(String name) {
        return typeEnvironment.enumValue(name);
    }

    public BoolValue bool(boolean value) {
        return new BoolValue(z3Context, value);
    }

    public RealValue real(double value) {
        return new RealValue(z3Context, value);
    }
}
