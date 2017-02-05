package io.github.sgreben.rc.expressions;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.Expr;
import io.github.sgreben.rc.Variable;

import java.util.Set;

public class ArithmeticExpression implements Expression {
    private final ArithExpr expression;
    private final Set<Variable> variables;

    public ArithmeticExpression(ArithExpr expression, Set<Variable> variables) {
        this.expression = expression;
        this.variables = variables;
    }

    @Override
    public Set<Variable> freeVariables() {
        return variables;
    }

    @Override
    public Expr toZ3Expr() {
        return expression;
    }

    public ArithExpr toZ3ArithExpr() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticExpression that = (ArithmeticExpression) o;

        return expression.equals(that.expression);
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                "expression=" + expression +
                ", variables=" + variables +
                '}';
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
