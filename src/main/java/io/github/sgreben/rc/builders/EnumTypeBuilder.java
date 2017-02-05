package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.TypeEnvironment;
import io.github.sgreben.rc.types.EnumType;

import java.util.LinkedList;
import java.util.List;

public class EnumTypeBuilder {
    private final TypeEnvironment typeEnvironment;
    private final Context z3Context;
    private List<String> values;
    private String name;

    public EnumTypeBuilder(TypeEnvironment typeEnvironment, Context z3Context) {
        this.typeEnvironment = typeEnvironment;
        this.z3Context = z3Context;
        this.values = new LinkedList<>();
    }

    public EnumTypeBuilder withValue(String value) {
        this.values.add(value);
        return this;
    }

    public EnumType build() {
        EnumType enumType = new EnumType(z3Context, name, values.toArray(new String[]{}));
        typeEnvironment.register(enumType);
        return enumType;
    }

    public EnumTypeBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
