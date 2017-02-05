package io.github.sgreben.rc.types;

import com.microsoft.z3.*;
import io.github.sgreben.rc.values.EnumValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumType implements Type {
    private final EnumSort sort;
    private final String name;
    private final String[] values;
    private final Map<String, Expr> valueExpression;
    private final Map<String, Integer> valueIndex;
    private final Map<String, FuncDecl> valueDecl;

    public EnumType(Context context, String name, String[] values) {
        this.name = name;
        this.values = values;
        sort = context.mkEnumSort(name, values);
        valueExpression = new HashMap<>();
        valueIndex = new HashMap<>();
        valueDecl = new HashMap<>();
        int i = 0;
        for (String value : values) {
            valueExpression.put(value, sort.getConst(i));
            valueDecl.put(value, sort.getConstDecl(i));
            valueIndex.put(value, i);
            ++i;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnumType enumType = (EnumType) o;

        return name.equals(enumType.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public EnumValue getValue(String value) {
        return new EnumValue(this, value, valueDecl.get(value), valueExpression.get(value));
    }

    public List<String> values() {
        return Arrays.asList(values);
    }

    @Override
    public String toString() {
        return "EnumType{" +
                "name='" + name + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }

    @Override
    public Sort toZ3Sort() {
        return sort;
    }
}
