package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.Module;
import io.github.sgreben.rc.RuleSet;
import io.github.sgreben.rc.TypeEnvironment;

import java.util.LinkedList;
import java.util.List;

public class ModuleBuilder {
    private final Context z3Context;
    private final TypeEnvironment typeEnvironment;
    private final List<RuleSet> ruleSets;
    private String name = null;

    public ModuleBuilder(TypeEnvironment typeEnvironment, Context z3Context) {
        this.z3Context = z3Context;
        this.typeEnvironment = typeEnvironment;
        this.ruleSets = new LinkedList<>();
    }

    public ModuleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ModuleBuilder withRuleSet(RuleSet ruleSet) {
        ruleSets.add(ruleSet);
        return this;
    }

    public Module build() {
        return new Module(z3Context, name, typeEnvironment, ruleSets);
    }
}
