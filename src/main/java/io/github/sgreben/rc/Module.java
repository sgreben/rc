package io.github.sgreben.rc;

import com.microsoft.z3.Context;

import java.util.Iterator;
import java.util.List;

public class Module implements Iterable<RuleSet> {
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

    public RuleSet ruleSet(String name) {
        for (RuleSet ruleSet : ruleSets) {
            if (ruleSet.getName().equals(name)) {
                return ruleSet;
            }
        }
        return null;
    }

    @Override
    public Iterator<RuleSet> iterator() {
        return ruleSets.iterator();
    }
}
