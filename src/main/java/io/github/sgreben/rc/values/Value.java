package io.github.sgreben.rc.values;

import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.expressions.Expression;

import java.util.Collections;
import java.util.Set;

public abstract class Value implements Expression {
    public abstract boolean equals(Object other);

    @Override
    public Set<Variable> freeVariables() {
        return Collections.emptySet();
    }

}
