package io.github.sgreben.rc.expressions;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Expr;
import io.github.sgreben.rc.Variable;

import java.util.Set;

public class BooleanExpression implements Expression {
    private final BoolExpr expression;
    private final Set<Variable> variables;

    public BooleanExpression(BoolExpr expression, Set<Variable> variables) {
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

    public BoolExpr toZ3BoolExpr() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanExpression that = (BooleanExpression) o;

        return expression.equals(that.expression);
    }

    @Override
    public String toString() {
        return "BooleanExpression{" +
                "expression=" + expression +
                ", variables=" + variables +
                '}';
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
