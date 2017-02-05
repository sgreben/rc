package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.Rule;
import io.github.sgreben.rc.expressions.BooleanExpression;

import java.util.LinkedList;
import java.util.List;

public class RuleBuilder {
    private final Context z3Context;
    private String name;
    private List<BooleanExpression> preconditions;
    private List<BooleanExpression> postconditions;

    public RuleBuilder(Context z3Context) {
        this.z3Context = z3Context;
        this.name = null;
        this.preconditions = new LinkedList<>();
        this.postconditions = new LinkedList<>();
    }

    public RuleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RuleBuilder withPrecondition(BooleanExpression precondition) {
        this.preconditions.add(precondition);
        return this;
    }

    public RuleBuilder withPostcondition(BooleanExpression postcondition) {
        postconditions.add(postcondition);
        return this;
    }

    public Rule build() {
        return new Rule(z3Context, name, preconditions, postconditions);
    }

}
