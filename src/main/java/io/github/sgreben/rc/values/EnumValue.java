package io.github.sgreben.rc.values;

import com.microsoft.z3.Expr;
import com.microsoft.z3.FuncDecl;
import io.github.sgreben.rc.types.EnumType;

public class EnumValue extends Value {
    private final EnumType enumType;
    private final String name;
    private final FuncDecl funcDecl;
    private final Expr expression;

    public String getName() {
        return name;
    }

    public EnumValue(EnumType enumType, String name, FuncDecl funcDecl, Expr expression) {
        this.enumType = enumType;
        this.name = name;
        this.funcDecl = funcDecl;
        this.expression = expression;
    }

    public FuncDecl getFuncDecl() {
        return funcDecl;
    }

    @Override
    public Expr toZ3Expr() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnumValue enumValue = (EnumValue) o;

        return enumType.equals(enumValue.enumType) && name.equals(enumValue.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int result = enumType.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
