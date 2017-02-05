package io.github.sgreben.rc;

import com.microsoft.z3.Context;

import java.util.List;

public class Module {
    private final Context z3Context;
    private final String name;
    private final TypeEnvironment typeEnvironment;
    private final List<RuleSet> ruleSets;

    public Module(Context z3Context, String name, TypeEnvironment typeEnvironment, List<RuleSet> ruleSets) {
        this.z3Context = z3Context;
        this.name = name;
        this.typeEnvironment = typeEnvironment;
        this.ruleSets = ruleSets;
    }
}
