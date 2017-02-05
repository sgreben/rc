package io.github.sgreben.rc;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.FuncDecl;
import com.microsoft.z3.Symbol;
import io.github.sgreben.rc.expressions.Expression;
import io.github.sgreben.rc.types.Type;

import java.util.Collections;
import java.util.Set;

public class Variable implements Expression {
    private final String name;
    private final Type type;
    private final FuncDecl constDecl;
    private final Symbol symbol;
    private final Context context;

    public Variable(Context context, String name, Type type) {
        this.context = context;
        this.symbol = context.mkSymbol(name);
        this.constDecl = context.mkConstDecl(symbol, type.toZ3Sort());
        this.name = name;
        this.type = type;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public FuncDecl getConstDecl() {
        return constDecl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;

        if (!name.equals(variable.name)) return false;
        if (!type.equals(variable.type)) return false;
        if (!constDecl.equals(variable.constDecl)) return false;
        return symbol.equals(variable.symbol);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public Set<Variable> freeVariables() {
        return Collections.singleton(this);
    }

    public Expr toZ3Expr() {
        return context.mkConst(constDecl);
    }
}
