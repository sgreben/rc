package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.Rule;
import io.github.sgreben.rc.RuleSet;
import io.github.sgreben.rc.TypeEnvironment;

import java.util.LinkedList;
import java.util.List;

public class RuleSetBuilder {
    private final TypeEnvironment typeEnvironment;
    private final Context z3Context;
    private String name;
    private List<Rule> rules;

    public RuleSetBuilder(TypeEnvironment typeEnvironment, Context z3Context) {
        this.typeEnvironment = typeEnvironment;
        this.z3Context = z3Context;
        this.rules = new LinkedList<>();
    }

    public RuleSetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RuleSetBuilder withRule(Rule rule) {
        rules.add(rule);
        return this;
    }

    public RuleSet build() {
        return new RuleSet(z3Context, typeEnvironment,  name, rules);
    }
}
