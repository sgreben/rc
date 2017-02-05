package io.github.sgreben.rc.expressions;

import com.microsoft.z3.Expr;
import io.github.sgreben.rc.Variable;

import java.util.Set;

public interface Expression {
    Set<Variable> freeVariables();

    Expr toZ3Expr();

    boolean equals(Object o);

    int hashCode();
}
